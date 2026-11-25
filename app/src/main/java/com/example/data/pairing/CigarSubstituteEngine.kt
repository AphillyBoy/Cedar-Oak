package com.example.data.pairing

import com.example.data.catalog.PairingCatalog
import com.example.model.Beverage
import com.example.model.Cigar
import com.example.model.CigarSubstituteMatch
import com.example.model.FlavorAxes
import com.example.model.StrengthLevel
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.math.sqrt

enum class SubstituteFilter(val label: String) {
    ALL("All Matches"),
    WIDELY_STOCKED("Widely Stocked"),
    NON_CUBAN("New World Only"),
    SAME_STRENGTH("Exact Strength"),
}

object CigarSubstituteEngine {

    private val widelyStockedShopBrands: Set<String> = setOf(
        "Arturo Fuente",
        "Padrón",
        "Oliva",
        "My Father",
        "Perdomo",
        "Rocky Patel",
        "Drew Estate",
        "Ashton",
        "Macanudo",
        "Montecristo",
        "Romeo y Julieta",
        "Alec Bradley",
        "CAO",
        "Davidoff",
        "La Aroma de Cuba",
        "San Cristóbal",
        "E.P. Carrillo",
        "AJ Fernandez",
        "Punch",
        "Partagás",
        "La Flor Dominicana",
        "Joya de Nicaragua",
        "Plasencia",
        "Camacho",
        "La Gloria Cubana",
        "Nub",
        "Foundation",
        "Tatuaje",
        "Diesel",
        "Man O' War",
        "Ave Maria",
    )

    private val rareAllocationKeywords = listOf(
        "behike",
        "opusx",
        "añejo",
        "anejo",
        "andalusian bull",
        "family reserve",
        "limited",
        "fundadores",
    )

    fun isCuban(cigar: Cigar): Boolean =
        cigar.origin.contains("Cuba", ignoreCase = true)

    fun isWidelyAvailableInShops(cigar: Cigar): Boolean {
        if (isCuban(cigar)) return false
        val lower = cigar.name.lowercase()
        if (rareAllocationKeywords.any { lower.contains(it) }) return false
        return cigar.brand in widelyStockedShopBrands
    }

    fun findSubstitutes(
        target: Cigar,
        catalog: List<Cigar> = PairingCatalog.cigars,
        pairedBeverage: Beverage? = null,
        filter: SubstituteFilter = SubstituteFilter.ALL,
        shelfQuery: String = "",
        limit: Int = 8,
    ): List<CigarSubstituteMatch> {
        val targetIsCuban = isCuban(target)
        val targetIsRare = !isWidelyAvailableInShops(target)
        val targetWrapperFamily = wrapperFamily(target.wrapper)
        val q = shelfQuery.trim().lowercase()

        val candidates = catalog
            .asSequence()
            .filter { it.id != target.id }
            .filter { candidate ->
                when (filter) {
                    SubstituteFilter.ALL -> true
                    SubstituteFilter.WIDELY_STOCKED -> isWidelyAvailableInShops(candidate)
                    SubstituteFilter.NON_CUBAN -> !isCuban(candidate)
                    SubstituteFilter.SAME_STRENGTH -> candidate.strength == target.strength
                }
            }
            .filter { candidate ->
                q.isEmpty() ||
                    candidate.name.lowercase().contains(q) ||
                    candidate.brand.lowercase().contains(q) ||
                    candidate.wrapper.lowercase().contains(q) ||
                    candidate.origin.lowercase().contains(q)
            }
            .map { candidate ->
                scoreSubstitute(
                    target = target,
                    candidate = candidate,
                    targetIsCuban = targetIsCuban,
                    targetIsRare = targetIsRare,
                    targetWrapperFamily = targetWrapperFamily,
                    pairedBeverage = pairedBeverage,
                )
            }
            .sortedWith(
                compareByDescending<CigarSubstituteMatch> { match ->
                    if (q.isEmpty()) 0
                    else {
                        val brandLower = match.substitute.brand.lowercase()
                        val nameLower = match.substitute.name.lowercase()
                        when {
                            brandLower.startsWith(q) -> 3
                            brandLower.split(" ", "-", "'").any { it.startsWith(q) } ||
                                nameLower.split(" ", "-", "'").any { it.startsWith(q) } -> 2
                            else -> 1
                        }
                    }
                }
                    .thenByDescending { it.similarityScore }
                    .thenByDescending { it.isWidelyAvailable }
                    .thenByDescending { it.pairingScoreWithBeverage ?: 0 }
            )
            .toList()

        // When no specific shelf query is typed, diversify by brand so the smoker gets
        // practical alternatives across multiple manufacturers at the humidor counter.
        if (q.isNotEmpty()) {
            return candidates.take(limit)
        }

        val diversified = mutableListOf<CigarSubstituteMatch>()
        val usedBrands = mutableSetOf<String>()
        for (match in candidates) {
            if (diversified.size >= limit) break
            // Allow at most 1 same-brand alternative (as a house fallback) and 1 per other brand
            if (match.substitute.brand !in usedBrands) {
                diversified.add(match)
                usedBrands.add(match.substitute.brand)
            }
        }
        if (diversified.size < limit) {
            for (match in candidates) {
                if (diversified.size >= limit) break
                if (diversified.none { it.substitute.id == match.substitute.id }) {
                    diversified.add(match)
                }
            }
        }
        return diversified
    }

    fun buildTobacconistAskTip(target: Cigar, pairedBeverage: Beverage? = null): String {
        val topFlavors = target.flavorAxes.topAxes(3).joinToString(", ")
        val wrapperFam = wrapperFamilyLabel(target.wrapper)
        val originHint = if (isCuban(target)) {
            "Nicaraguan or Dominican Cuban-seed alternative"
        } else {
            "${target.origin} or neighboring Central American blend"
        }
        val drinkClause = pairedBeverage?.let {
            " to keep the pairing balanced with ${it.name}"
        } ?: ""

        return "If ${target.brand} ${target.name} is sold out, ask your tobacconist for a " +
            "${target.strength.label.lowercase()}-strength $originHint in a $wrapperFam wrapper " +
            "highlighting $topFlavors (~${target.smokeTimeMinutes} min smoke)$drinkClause."
    }

    private fun scoreSubstitute(
        target: Cigar,
        candidate: Cigar,
        targetIsCuban: Boolean,
        targetIsRare: Boolean,
        targetWrapperFamily: String,
        pairedBeverage: Beverage?,
    ): CigarSubstituteMatch {
        val flavorSim = flavorSimilarity(target.flavorAxes, candidate.flavorAxes) // 0..1
        val strengthDiff = abs(target.strength.ordinal - candidate.strength.ordinal)
        val strengthScore = when (strengthDiff) {
            0 -> 1.0
            1 -> 0.72
            2 -> 0.35
            else -> 0.12
        }

        val shadeDiff = abs(target.wrapperShade.ordinal - candidate.wrapperShade.ordinal)
        val candidateWrapperFamily = wrapperFamily(candidate.wrapper)
        val wrapperScore = when {
            candidateWrapperFamily == targetWrapperFamily && shadeDiff == 0 -> 1.0
            shadeDiff == 0 -> 0.85
            candidateWrapperFamily == targetWrapperFamily -> 0.82
            shadeDiff == 1 -> 0.62
            else -> 0.30
        }

        val timeDiff = abs(target.smokeTimeMinutes - candidate.smokeTimeMinutes)
        val vitolaScore = when {
            timeDiff <= 10 -> 1.0
            timeDiff <= 20 -> 0.82
            timeDiff <= 35 -> 0.60
            else -> 0.40
        }

        val widelyAvailable = isWidelyAvailableInShops(candidate)
        val availabilityBonus = when {
            (targetIsCuban || targetIsRare) && widelyAvailable -> 0.08
            widelyAvailable -> 0.03
            else -> 0.0
        }

        val rawScore = (
            flavorSim * 0.50 +
                strengthScore * 0.22 +
                wrapperScore * 0.18 +
                vitolaScore * 0.10 +
                availabilityBonus
            ).coerceIn(0.45, 0.99)

        val similarityPercent = (rawScore * 100.0).roundToInt().coerceIn(55, 99)

        val sharedNotes = findSharedNotes(target, candidate)
        val badge = when {
            targetIsCuban && widelyAvailable -> "New World Shop Staple"
            targetIsRare && widelyAvailable -> "Widely Stocked Backup"
            candidate.brand == target.brand -> "Same House Alternative"
            candidateWrapperFamily == targetWrapperFamily && shadeDiff == 0 -> "Same Wrapper DNA"
            widelyAvailable -> "Widely Stocked"
            else -> "Boutique Match"
        }

        val reasonParts = mutableListOf<String>()
        if (strengthDiff == 0) {
            reasonParts.add("identical ${candidate.strength.label} strength")
        } else {
            reasonParts.add("close ${candidate.strength.label} strength")
        }
        if (candidateWrapperFamily == targetWrapperFamily) {
            reasonParts.add("matching ${wrapperFamilyLabel(candidate.wrapper)} leaf")
        } else if (shadeDiff == 0) {
            reasonParts.add("same ${candidate.wrapperShade.label} wrapper shade")
        }
        val notesClause = if (sharedNotes.isNotEmpty()) {
            "with shared ${sharedNotes.take(2).joinToString(" & ").lowercase()} notes"
        } else {
            "echoing ${candidate.flavorAxes.topAxes(2).joinToString(" & ").lowercase()} peaks"
        }

        val matchReason = "Delivers ${reasonParts.joinToString(", ")} $notesClause."

        val beverageMatchScore = pairedBeverage?.let { bev ->
            PairingCatalog.evaluatePairing(candidate, bev).matchScore
        }

        return CigarSubstituteMatch(
            substitute = candidate,
            similarityScore = similarityPercent,
            availabilityBadge = badge,
            matchReason = matchReason,
            sharedNotes = sharedNotes.ifEmpty { candidate.flavorAxes.topAxes(3) },
            pairingScoreWithBeverage = beverageMatchScore,
            isWidelyAvailable = widelyAvailable,
        )
    }

    private fun flavorSimilarity(a: FlavorAxes, b: FlavorAxes): Double {
        val av = a.asLabeledList().map { it.second.toDouble() }
        val bv = b.asLabeledList().map { it.second.toDouble() }
        var dot = 0.0
        var magA = 0.0
        var magB = 0.0
        var absDiff = 0.0
        for (i in av.indices) {
            dot += av[i] * bv[i]
            magA += av[i] * av[i]
            magB += bv[i] * bv[i]
            absDiff += abs(av[i] - bv[i])
        }
        val cosine = if (magA == 0.0 || magB == 0.0) 0.5 else dot / (sqrt(magA) * sqrt(magB))
        val l1Sim = (1.0 - (absDiff / av.size)).coerceIn(0.0, 1.0)
        return (cosine * 0.55 + l1Sim * 0.45).coerceIn(0.0, 1.0)
    }

    private fun findSharedNotes(a: Cigar, b: Cigar): List<String> {
        val aTokens = a.tastingNotes.flatMap { it.lowercase().split(" ", "-", "/") }.filter { it.length > 3 }.toSet()
        val exactOverlap = b.tastingNotes.filter { note ->
            val tokens = note.lowercase().split(" ", "-", "/")
            tokens.any { it in aTokens }
        }
        if (exactOverlap.isNotEmpty()) return exactOverlap.take(3)
        val aAxes = a.flavorAxes.topAxes(4).toSet()
        return b.flavorAxes.topAxes(4).filter { it in aAxes }.take(3)
    }

    private fun wrapperFamily(wrapper: String): String {
        val w = wrapper.lowercase()
        return when {
            w.contains("broadleaf") -> "broadleaf"
            w.contains("san andr") -> "san_andres"
            w.contains("cameroon") -> "cameroon"
            w.contains("sumatra") -> "sumatra"
            w.contains("corojo") -> "corojo"
            w.contains("criollo") -> "criollo"
            w.contains("connecticut") || w.contains("shade") -> "connecticut"
            w.contains("habano") || w.contains("cuban") -> "habano"
            w.contains("maduro") || w.contains("oscuro") -> "maduro"
            else -> "classic"
        }
    }

    private fun wrapperFamilyLabel(wrapper: String): String = when (wrapperFamily(wrapper)) {
        "broadleaf" -> "Connecticut Broadleaf"
        "san_andres" -> "Mexican San Andrés"
        "cameroon" -> "African Cameroon"
        "sumatra" -> "Ecuadorian Sumatra"
        "corojo" -> "Corojo"
        "criollo" -> "Criollo"
        "connecticut" -> "Connecticut Shade"
        "habano" -> "Habano"
        "maduro" -> "Maduro"
        else -> wrapper
    }
}
