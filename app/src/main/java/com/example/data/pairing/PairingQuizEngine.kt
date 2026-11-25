package com.example.data.pairing

import com.example.data.catalog.PairingCatalog
import com.example.model.Beverage
import com.example.model.BeverageCategory
import com.example.model.Cigar
import com.example.model.PairingQuizResult
import com.example.model.PairingRatingSummary
import com.example.model.QuizMatchPick
import com.example.model.SmokeTimeOfDay
import com.example.model.StrengthLevel
import kotlin.math.abs
import kotlin.math.roundToInt

enum class QuizFlavorMood(val label: String, val emoji: String, val subtitle: String) {
    COCOA_ESPRESSO("Dark Cocoa & Espresso", "🍫", "Velvety mocha, dark roast & bittersweet nibs"),
    CREAM_CARAMEL("Caramel, Cream & Vanilla", "🍯", "Silky brioche, sweet cream & honey butter"),
    CEDAR_NUTS("Toasted Cedar & Nuts", "🪵", "Warm Spanish cedar, almond & graham"),
    SPICE_EARTH("Pepper, Leather & Earth", "🌶️", "Black pepper, warm leather & Nicaraguan soil"),
    CITRUS_HONEY("Bright Citrus & Spice", "🍊", "Zesty orange peel, floral honey & baking spice"),
}

enum class QuizDrinkPreference(val label: String, val emoji: String, val subtitle: String) {
    WHISKEY("Whiskey, Bourbon & Scotch", "🥃", "Oak, caramel, peat & rye spice"),
    RUM_COGNAC("Rum, Cognac & Cocktails", "🍹", "Aged molasses, brandy fruit & lounge classics"),
    WINE_BEER("Wine, Port & Craft Beer", "🍷", "Tawny port, sherry, stouts & Belgian quads"),
    COFFEE_CACAO("Coffee, Espresso & Cacao", "☕", "Zero-proof roast, cold brew & drinking chocolate"),
    TEA_SODA("Artisanal Tea & Craft Sodas", "🍵", "Zero-proof oolong, ginger beer & botanical elixirs"),
    ANY("Surprise Me (Any Pour)", "✨", "Best overall sommelier match across all pours"),
}

enum class QuizSessionLength(val label: String, val emoji: String, val subtitle: String, val minMin: Int, val maxMin: Int) {
    QUICK("Quick Smoke", "⚡", "25–45 min · Crisp & focused", 20, 48),
    CLASSIC("Classic Session", "⏳", "45–70 min · Robusto or Toro pace", 45, 72),
    LEISURELY("Leisurely Lounge", "👑", "70+ min · Churchill or Torpedo", 68, 140),
}

data class PairingQuizAnswers(
    val timeOfDay: SmokeTimeOfDay = SmokeTimeOfDay.EVENING,
    val preferredStrength: StrengthLevel = StrengthLevel.MEDIUM_FULL,
    val flavorMood: QuizFlavorMood = QuizFlavorMood.COCOA_ESPRESSO,
    val drinkPreference: QuizDrinkPreference = QuizDrinkPreference.WHISKEY,
    val sessionLength: QuizSessionLength = QuizSessionLength.CLASSIC,
)

object PairingQuizEngine {

    fun evaluateQuiz(
        answers: PairingQuizAnswers,
        ratingsByPairingId: Map<String, PairingRatingSummary> = emptyMap(),
        limit: Int = 5,
    ): PairingQuizResult {
        val eligibleDrinks = PairingCatalog.beverages.filter { bev ->
            matchesDrinkPreference(bev, answers.drinkPreference)
        }.ifEmpty { PairingCatalog.beverages }

        // Step 1: Score every cigar against quiz preferences
        val topCigars = PairingCatalog.cigars
            .map { cigar ->
                val timeRec = SmokeTimeAdvisor.recommendFor(cigar)
                val cigarFit = scoreCigarForQuiz(cigar, timeRec.primaryTime, timeRec.secondaryTime, answers)
                Triple(cigar, timeRec, cigarFit)
            }
            .sortedByDescending { it.third }
            .take(40)

        // Step 2: Pair top candidate cigars with their top 3 best-fit beverages from chosen drink lane
        val candidatePicks = mutableListOf<QuizMatchPick>()
        for ((cigar, timeRec, cigarFit) in topCigars) {
            val cigarBeveragePicks = eligibleDrinks.map { bev ->
                val summary = ratingsByPairingId[com.example.model.buildPairingId(cigar.id, bev.id)]
                val suggestion = PairingCatalog.evaluatePairing(cigar, bev, summary)
                val bevMoodScore = flavorMoodScore(bev.flavorAxes, answers.flavorMood)
                val composite = (
                    cigarFit * 0.48 +
                        (suggestion.matchScore / 100.0) * 0.40 +
                        bevMoodScore * 0.12
                    ).coerceIn(0.55, 0.99)
                val fitPercent = (composite * 100.0).roundToInt().coerceIn(72, 99)
                QuizMatchPick(
                    suggestion = suggestion,
                    quizFitScore = fitPercent,
                    fitReason = buildFitReason(cigar, bev, answers, timeRec.primaryTime),
                    timeRecommendation = timeRec,
                ) to composite
            }
                .sortedByDescending { it.second }
                .take(3)
                .map { it.first }

            candidatePicks.addAll(cigarBeveragePicks)
        }

        // Step 3: Diversify top results across cigar brands and beverages
        val sortedPicks = candidatePicks.sortedByDescending { it.quizFitScore }
        val finalPicks = mutableListOf<QuizMatchPick>()
        val usedBrands = mutableSetOf<String>()
        val usedBeverages = mutableSetOf<String>()

        // Pass 1: distinct brand AND distinct beverage
        for (pick in sortedPicks) {
            if (finalPicks.size >= limit) break
            if (pick.suggestion.cigar.brand !in usedBrands && pick.suggestion.beverage.id !in usedBeverages) {
                finalPicks.add(pick)
                usedBrands.add(pick.suggestion.cigar.brand)
                usedBeverages.add(pick.suggestion.beverage.id)
            }
        }
        // Pass 2: distinct cigar brand (even if beverage repeats)
        if (finalPicks.size < limit) {
            for (pick in sortedPicks) {
                if (finalPicks.size >= limit) break
                if (pick.suggestion.cigar.brand !in usedBrands &&
                    finalPicks.none { it.suggestion.pairingId == pick.suggestion.pairingId }
                ) {
                    finalPicks.add(pick)
                    usedBrands.add(pick.suggestion.cigar.brand)
                }
            }
        }
        // Pass 3: any remaining distinct pairing
        if (finalPicks.size < limit) {
            for (pick in sortedPicks) {
                if (finalPicks.size >= limit) break
                if (finalPicks.none { it.suggestion.pairingId == pick.suggestion.pairingId }) {
                    finalPicks.add(pick)
                }
            }
        }

        val (emoji, title, summary) = buildArchetype(answers)
        return PairingQuizResult(
            archetypeEmoji = emoji,
            archetypeTitle = title,
            archetypeSummary = summary,
            recommendedTime = answers.timeOfDay,
            topPicks = finalPicks,
        )
    }

    private fun matchesDrinkPreference(bev: Beverage, pref: QuizDrinkPreference): Boolean = when (pref) {
        QuizDrinkPreference.WHISKEY -> bev.category == BeverageCategory.WHISKEY
        QuizDrinkPreference.RUM_COGNAC -> bev.category in setOf(
            BeverageCategory.RUM,
            BeverageCategory.BRANDY,
            BeverageCategory.COCKTAILS,
            BeverageCategory.AGAVE,
        )
        QuizDrinkPreference.WINE_BEER -> bev.category in setOf(
            BeverageCategory.WINE,
            BeverageCategory.BEER,
        )
        QuizDrinkPreference.COFFEE_CACAO -> bev.category in setOf(
            BeverageCategory.COFFEE,
            BeverageCategory.CACAO,
        )
        QuizDrinkPreference.TEA_SODA -> bev.category in setOf(
            BeverageCategory.TEA,
            BeverageCategory.CRAFT_SODA,
            BeverageCategory.ZERO_PROOF_ELIXIRS,
        )
        QuizDrinkPreference.ANY -> true
    }

    private fun scoreCigarForQuiz(
        cigar: Cigar,
        primaryTime: SmokeTimeOfDay,
        secondaryTime: SmokeTimeOfDay?,
        answers: PairingQuizAnswers,
    ): Double {
        val timeScore = when {
            primaryTime == answers.timeOfDay -> 1.0
            secondaryTime == answers.timeOfDay -> 0.78
            abs(primaryTime.ordinal - answers.timeOfDay.ordinal) == 1 -> 0.65
            else -> 0.35
        }
        val strengthDiff = abs(cigar.strength.ordinal - answers.preferredStrength.ordinal)
        val strengthScore = when (strengthDiff) {
            0 -> 1.0
            1 -> 0.72
            2 -> 0.38
            else -> 0.15
        }
        val flavorScore = flavorMoodScore(cigar.flavorAxes, answers.flavorMood)
        val durationScore = when {
            cigar.smokeTimeMinutes in answers.sessionLength.minMin..answers.sessionLength.maxMin -> 1.0
            abs(cigar.smokeTimeMinutes - answers.sessionLength.minMin) <= 15 ||
                abs(cigar.smokeTimeMinutes - answers.sessionLength.maxMin) <= 15 -> 0.76
            else -> 0.45
        }
        return timeScore * 0.30 + strengthScore * 0.30 + flavorScore * 0.26 + durationScore * 0.14
    }

    private fun flavorMoodScore(axes: com.example.model.FlavorAxes, mood: QuizFlavorMood): Double = when (mood) {
        QuizFlavorMood.COCOA_ESPRESSO -> (axes.cocoaRoast * 0.65 + axes.caramelSweet * 0.20 + axes.earthSmoke * 0.15).toDouble()
        QuizFlavorMood.CREAM_CARAMEL -> (axes.creamVanilla * 0.55 + axes.caramelSweet * 0.35 + axes.nutMalt * 0.10).toDouble()
        QuizFlavorMood.CEDAR_NUTS -> (axes.woodCedar * 0.52 + axes.nutMalt * 0.38 + axes.creamVanilla * 0.10).toDouble()
        QuizFlavorMood.SPICE_EARTH -> (axes.spicePepper * 0.52 + axes.earthSmoke * 0.38 + axes.woodCedar * 0.10).toDouble()
        QuizFlavorMood.CITRUS_HONEY -> (axes.fruitZest * 0.50 + axes.caramelSweet * 0.30 + axes.spicePepper * 0.20).toDouble()
    }.coerceIn(0.0, 1.0)

    private fun buildFitReason(
        cigar: Cigar,
        beverage: Beverage,
        answers: PairingQuizAnswers,
        primaryTime: SmokeTimeOfDay,
    ): String {
        val moodHint = answers.flavorMood.label.lowercase()
        return "${primaryTime.emoji} Tailored for ${answers.timeOfDay.label.lowercase()}: " +
            "${cigar.strength.label} ${cigar.wrapperShade.label} (~${cigar.smokeTimeMinutes} min) " +
            "delivering $moodHint alongside ${beverage.category.emoji} ${beverage.name}."
    }

    private fun buildArchetype(answers: PairingQuizAnswers): Triple<String, String, String> = when {
        answers.timeOfDay == SmokeTimeOfDay.MORNING &&
            (answers.drinkPreference == QuizDrinkPreference.COFFEE_CACAO || answers.drinkPreference == QuizDrinkPreference.TEA_SODA) ->
            Triple(
                "☀️",
                "Dawn Espresso Connoisseur",
                "You prize silky morning wrappers, creamy cedar balance, and aromatic zero-proof pours that wake up the palate cleanly.",
            )
        answers.timeOfDay == SmokeTimeOfDay.LATE_NIGHT && answers.preferredStrength.ordinal >= StrengthLevel.MEDIUM_FULL.ordinal ->
            Triple(
                "✨",
                "Midnight Reserve Aficionado",
                "You gravitate toward slow-burning dark wrappers, layered espresso-cocoa depth, and contemplative nightcap pours.",
            )
        answers.drinkPreference == QuizDrinkPreference.WHISKEY ->
            Triple(
                "🥃",
                "Barrel & Cedar Traditionalist",
                "You love classic lounge harmony where charred oak, vanilla caramel, and warm tobacco spice elevate each third.",
            )
        answers.drinkPreference == QuizDrinkPreference.RUM_COGNAC ->
            Triple(
                "🍹",
                "Havana Club & Lounge Explorer",
                "You seek lush tropical sweetness, silky cognac fruit, and diplomatic-grade cigars built for celebration.",
            )
        answers.drinkPreference == QuizDrinkPreference.WINE_BEER ->
            Triple(
                "🍷",
                "Cellar & Sommelier Specialist",
                "You pair cigars like fine vintages—balancing dark berry, tawny nuttiness, and roasted malt against aged tobacco.",
            )
        answers.drinkPreference == QuizDrinkPreference.COFFEE_CACAO || answers.drinkPreference == QuizDrinkPreference.TEA_SODA ->
            Triple(
                "☕",
                "Zero-Proof Tasting Purist",
                "You keep every nuance of the leaf crystal clear with artisan coffee, cacao, botanical tea, or sparkling palate resets.",
            )
        else ->
            Triple(
                "🎯",
                "Grand Cru Cigar Sommelier",
                "Your palate thrives on balanced multi-third progression, expressive wrapper character, and effortless drink synergy.",
            )
    }
}
