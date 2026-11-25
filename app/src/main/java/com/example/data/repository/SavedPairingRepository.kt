package com.example.data.repository

import com.example.data.catalog.PairingCatalog
import com.example.data.local.SavedPairingDao
import com.example.data.local.SavedPairingEntity
import com.example.model.PairingStrategy
import com.example.model.SavedPairing
import com.example.model.buildPairingId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class SavedPairingRepository(
    private val dao: SavedPairingDao,
    private val prefs: UserPreferencesRepository? = null,
) {
    private val seedMutex = Mutex()

    companion object {
        val OCCASION_TAGS = listOf(
            "Lounge Reserve",
            "After Dinner",
            "Celebration",
            "Morning Coffee",
            "Quick Smoke",
            "Weekend Ritual",
        )
    }

    fun observeAllSavedPairings(): Flow<List<SavedPairing>> =
        dao.observeAllSavedPairings().map { rows -> rows.map { it.toDomain() } }

    fun observeFavoritePairings(): Flow<List<SavedPairing>> =
        dao.observeFavoritePairings().map { rows -> rows.map { it.toDomain() } }

    fun observeSavedPairing(pairingId: String): Flow<SavedPairing?> =
        dao.observeSavedPairingById(pairingId).map { it?.toDomain() }

    fun observeSavedPairingIds(): Flow<Set<String>> =
        dao.observeSavedPairingIds().map { it.toSet() }

    fun observeSavedCount(): Flow<Int> = dao.observeSavedCount()

    suspend fun ensureSeeded() {
        seedMutex.withLock {
            val alreadySeeded = prefs?.hasSeededSavedPairings() ?: false
            if (alreadySeeded) return
            if (dao.getSavedCount() == 0) {
                val now = System.currentTimeMillis()
                val day = 86_400_000L
                val flagshipSeeds = listOf(
                    Triple("padron_1964", "bourbon_single_barrel", "After Dinner"),
                    Triple("cohiba_siglo6", "xo_cognac", "Celebration"),
                    Triple("montecristo_no2", "cuban_cafecito", "Morning Coffee"),
                    Triple("davidoff_late_hour", "islay_peated", "Lounge Reserve"),
                    Triple("oliva_melanio", "tawny_port_20", "After Dinner"),
                    Triple("sobremesa_brulee", "smoked_maple_cold_brew", "Morning Coffee"),
                    Triple("fuente_opusx", "belgian_quadrupel", "Celebration"),
                    Triple("liga_privada_9", "imperial_coffee_stout", "Weekend Ritual"),
                )
                val entities = flagshipSeeds.mapIndexedNotNull { idx, (cId, bId, occasion) ->
                    val cigar = PairingCatalog.cigarById(cId) ?: return@mapIndexedNotNull null
                    val bev = PairingCatalog.beverageById(bId) ?: return@mapIndexedNotNull null
                    val evaluated = PairingCatalog.evaluatePairing(cigar, bev)
                    SavedPairingEntity(
                        pairingId = buildPairingId(cId, bId),
                        cigarId = cId,
                        beverageId = bId,
                        cigarName = "${cigar.brand} ${cigar.name}",
                        beverageName = bev.name,
                        matchScore = evaluated.matchScore,
                        pairingStyle = evaluated.pairingStyle,
                        strategyName = PairingStrategy.BALANCED.label,
                        personalNotes = evaluated.servingTip,
                        occasionTag = occasion,
                        isFavorite = idx < 3,
                        timesEnjoyed = if (idx < 3) 2 else 1,
                        isCuratedSeed = true,
                        createdAt = now - (idx + 1) * day,
                        updatedAt = now - idx * (day / 2),
                    )
                }
                dao.insertAllIfAbsent(entities)
            }
            prefs?.markSavedPairingsSeeded()
        }
    }

    suspend fun saveOrUpdatePairing(
        cigarId: String,
        beverageId: String,
        personalNotes: String = "",
        occasionTag: String = "Lounge Reserve",
        isFavorite: Boolean? = null,
        strategy: PairingStrategy = PairingStrategy.BALANCED,
    ): Long {
        val cigar = PairingCatalog.cigarById(cigarId) ?: return -1L
        val beverage = PairingCatalog.beverageById(beverageId) ?: return -1L
        val pairingId = buildPairingId(cigarId, beverageId)
        val existing = dao.getByPairingId(pairingId)
        val evaluated = PairingCatalog.evaluatePairing(cigar, beverage, strategy = strategy)
        val now = System.currentTimeMillis()
        val resolvedNotes = personalNotes.trim().ifBlank {
            existing?.personalNotes?.ifBlank { evaluated.servingTip } ?: evaluated.servingTip
        }
        val entity = SavedPairingEntity(
            id = existing?.id ?: 0L,
            pairingId = pairingId,
            cigarId = cigarId,
            beverageId = beverageId,
            cigarName = "${cigar.brand} ${cigar.name}",
            beverageName = beverage.name,
            matchScore = evaluated.matchScore,
            pairingStyle = evaluated.pairingStyle,
            strategyName = strategy.label,
            personalNotes = resolvedNotes,
            occasionTag = occasionTag.ifBlank { existing?.occasionTag ?: "Lounge Reserve" },
            isFavorite = isFavorite ?: (existing?.isFavorite ?: false),
            timesEnjoyed = existing?.timesEnjoyed ?: 1,
            isCuratedSeed = existing?.isCuratedSeed ?: false,
            createdAt = existing?.createdAt ?: now,
            updatedAt = now,
        )
        return dao.upsertSavedPairing(entity)
    }

    suspend fun toggleSavePairing(
        cigarId: String,
        beverageId: String,
        strategy: PairingStrategy = PairingStrategy.BALANCED,
    ): Boolean {
        val pairingId = buildPairingId(cigarId, beverageId)
        val existing = dao.getByPairingId(pairingId)
        return if (existing != null) {
            dao.deleteByPairingId(pairingId)
            false
        } else {
            saveOrUpdatePairing(
                cigarId = cigarId,
                beverageId = beverageId,
                strategy = strategy,
            )
            true
        }
    }

    suspend fun toggleFavorite(pairingId: String) {
        val existing = dao.getByPairingId(pairingId) ?: return
        dao.updateFavorite(
            pairingId = pairingId,
            isFavorite = !existing.isFavorite,
            updatedAt = System.currentTimeMillis(),
        )
    }

    suspend fun logSessionEnjoyed(pairingId: String) {
        dao.incrementTimesEnjoyed(
            pairingId = pairingId,
            updatedAt = System.currentTimeMillis(),
        )
    }

    suspend fun updateNotesAndOccasion(
        pairingId: String,
        notes: String,
        occasionTag: String,
    ) {
        dao.updateNotesAndOccasion(
            pairingId = pairingId,
            notes = notes.trim(),
            occasionTag = occasionTag.ifBlank { "Lounge Reserve" },
            updatedAt = System.currentTimeMillis(),
        )
    }

    suspend fun deleteSavedPairing(pairingId: String) {
        dao.deleteByPairingId(pairingId)
    }

    private fun SavedPairingEntity.toDomain(): SavedPairing = SavedPairing(
        id = id,
        pairingId = pairingId,
        cigarId = cigarId,
        beverageId = beverageId,
        cigarName = cigarName,
        beverageName = beverageName,
        matchScore = matchScore,
        pairingStyle = pairingStyle,
        strategyName = strategyName,
        personalNotes = personalNotes,
        occasionTag = occasionTag,
        isFavorite = isFavorite,
        timesEnjoyed = timesEnjoyed,
        isCuratedSeed = isCuratedSeed,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}
