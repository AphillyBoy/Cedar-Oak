package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedPairingDao {

    @Query("SELECT * FROM saved_pairings ORDER BY isFavorite DESC, updatedAt DESC")
    fun observeAllSavedPairings(): Flow<List<SavedPairingEntity>>

    @Query("SELECT * FROM saved_pairings WHERE isFavorite = 1 ORDER BY updatedAt DESC")
    fun observeFavoritePairings(): Flow<List<SavedPairingEntity>>

    @Query("SELECT * FROM saved_pairings WHERE pairingId = :pairingId LIMIT 1")
    fun observeSavedPairingById(pairingId: String): Flow<SavedPairingEntity?>

    @Query("SELECT pairingId FROM saved_pairings")
    fun observeSavedPairingIds(): Flow<List<String>>

    @Query("SELECT COUNT(*) FROM saved_pairings")
    fun observeSavedCount(): Flow<Int>

    @Query("SELECT * FROM saved_pairings WHERE pairingId = :pairingId LIMIT 1")
    suspend fun getByPairingId(pairingId: String): SavedPairingEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertSavedPairing(entity: SavedPairingEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllIfAbsent(entities: List<SavedPairingEntity>)

    @Query("UPDATE saved_pairings SET isFavorite = :isFavorite, updatedAt = :updatedAt WHERE pairingId = :pairingId")
    suspend fun updateFavorite(pairingId: String, isFavorite: Boolean, updatedAt: Long)

    @Query("UPDATE saved_pairings SET timesEnjoyed = timesEnjoyed + 1, updatedAt = :updatedAt WHERE pairingId = :pairingId")
    suspend fun incrementTimesEnjoyed(pairingId: String, updatedAt: Long)

    @Query(
        """
        UPDATE saved_pairings
        SET personalNotes = :notes, occasionTag = :occasionTag, updatedAt = :updatedAt
        WHERE pairingId = :pairingId
        """
    )
    suspend fun updateNotesAndOccasion(
        pairingId: String,
        notes: String,
        occasionTag: String,
        updatedAt: Long,
    )

    @Query("DELETE FROM saved_pairings WHERE pairingId = :pairingId")
    suspend fun deleteByPairingId(pairingId: String)

    @Query("SELECT COUNT(*) FROM saved_pairings")
    suspend fun getSavedCount(): Int
}
