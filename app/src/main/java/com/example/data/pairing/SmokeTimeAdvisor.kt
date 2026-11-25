package com.example.data.pairing

import com.example.data.catalog.CigarAccoladesCatalog
import com.example.data.catalog.PairingCatalog
import com.example.model.Cigar
import com.example.model.SmokeTimeOfDay
import com.example.model.SmokeTimeRecommendation
import com.example.model.StrengthLevel
import com.example.model.WrapperShade
import java.util.Calendar

object SmokeTimeAdvisor {

    fun timeOfDayForHour(hour24: Int): SmokeTimeOfDay {
        val h = ((hour24 % 24) + 24) % 24
        return when (h) {
            in 5..10 -> SmokeTimeOfDay.MORNING
            in 11..14 -> SmokeTimeOfDay.MIDDAY
            in 15..17 -> SmokeTimeOfDay.AFTERNOON
            in 18..21 -> SmokeTimeOfDay.EVENING
            else -> SmokeTimeOfDay.LATE_NIGHT
        }
    }

    fun currentTimeOfDay(): SmokeTimeOfDay {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return timeOfDayForHour(hour)
    }

    fun recommendFor(cigar: Cigar): SmokeTimeRecommendation {
        val text = "${cigar.name} ${cigar.brand} ${cigar.wrapper} ${cigar.summary}".lowercase()

        // Explicit thematic overrides first
        val primary: SmokeTimeOfDay = when {
            text.contains("late hour") ||
                text.contains("midnight") ||
                text.contains("nocturno") ||
                text.contains("double ligero") ||
                text.contains("triple maduro") ||
                text.contains("behike") ||
                text.contains("opusx") ||
                text.contains("family reserve") ||
                text.contains("1926") ||
                text.contains("andalusian bull") -> SmokeTimeOfDay.LATE_NIGHT

            text.contains("café") ||
                text.contains("cafe") ||
                text.contains("breakfast") ||
                text.contains("morning") ||
                text.contains("champagne") ||
                text.contains("brûlée") ||
                text.contains("brulee") ||
                text.contains("isla del sol") ||
                text.contains("tabak especial") ||
                text.contains("java") -> SmokeTimeOfDay.MORNING

            cigar.strength == StrengthLevel.MILD &&
                (cigar.wrapperShade == WrapperShade.CLARO || cigar.wrapperShade == WrapperShade.COLORADO_CLARO) -> {
                if (cigar.flavorAxes.creamVanilla >= 0.55f || cigar.smokeTimeMinutes <= 55) {
                    SmokeTimeOfDay.MORNING
                } else {
                    SmokeTimeOfDay.MIDDAY
                }
            }

            cigar.strength == StrengthLevel.MILD -> SmokeTimeOfDay.MIDDAY

            cigar.strength == StrengthLevel.MEDIUM -> {
                when {
                    cigar.wrapperShade == WrapperShade.CLARO -> SmokeTimeOfDay.MORNING
                    cigar.smokeTimeMinutes <= 50 || cigar.flavorAxes.fruitZest >= 0.45f -> SmokeTimeOfDay.MIDDAY
                    cigar.wrapperShade == WrapperShade.MADURO || cigar.flavorAxes.cocoaRoast >= 0.65f -> SmokeTimeOfDay.AFTERNOON
                    else -> SmokeTimeOfDay.MIDDAY
                }
            }

            cigar.strength == StrengthLevel.MEDIUM_FULL -> {
                when {
                    cigar.wrapperShade == WrapperShade.OSCURO || cigar.smokeTimeMinutes >= 75 -> SmokeTimeOfDay.EVENING
                    cigar.wrapperShade == WrapperShade.MADURO -> SmokeTimeOfDay.EVENING
                    cigar.flavorAxes.caramelSweet >= 0.60f && cigar.smokeTimeMinutes <= 60 -> SmokeTimeOfDay.AFTERNOON
                    else -> SmokeTimeOfDay.AFTERNOON
                }
            }

            else -> { // FULL strength
                if (cigar.wrapperShade == WrapperShade.OSCURO ||
                    cigar.flavorAxes.earthSmoke >= 0.72f ||
                    cigar.flavorAxes.cocoaRoast >= 0.78f ||
                    cigar.smokeTimeMinutes >= 70
                ) {
                    SmokeTimeOfDay.LATE_NIGHT
                } else {
                    SmokeTimeOfDay.EVENING
                }
            }
        }

        val secondary: SmokeTimeOfDay? = when (primary) {
            SmokeTimeOfDay.MORNING -> SmokeTimeOfDay.MIDDAY
            SmokeTimeOfDay.MIDDAY -> if (cigar.strength == StrengthLevel.MILD) SmokeTimeOfDay.MORNING else SmokeTimeOfDay.AFTERNOON
            SmokeTimeOfDay.AFTERNOON -> SmokeTimeOfDay.EVENING
            SmokeTimeOfDay.EVENING -> if (cigar.strength == StrengthLevel.FULL) SmokeTimeOfDay.LATE_NIGHT else SmokeTimeOfDay.AFTERNOON
            SmokeTimeOfDay.LATE_NIGHT -> SmokeTimeOfDay.EVENING
        }

        val topNotes = cigar.flavorAxes.topAxes(2).joinToString(" & ").lowercase()
        val rationale = when (primary) {
            SmokeTimeOfDay.MORNING ->
                "Silky ${cigar.strength.label.lowercase()} body in a ${cigar.wrapperShade.label} wrapper with $topNotes notes won't overwhelm a fresh morning palate (~${cigar.smokeTimeMinutes} min)."
            SmokeTimeOfDay.MIDDAY ->
                "Balanced ${cigar.strength.label.lowercase()} complexity and $topNotes character shine between meals or on the back nine (~${cigar.smokeTimeMinutes} min)."
            SmokeTimeOfDay.AFTERNOON ->
                "Warm $topNotes richness and ${cigar.strength.label.lowercase()} poise make an ideal golden-hour transition before dinner (~${cigar.smokeTimeMinutes} min)."
            SmokeTimeOfDay.EVENING ->
                "Hearty ${cigar.strength.label.lowercase()} ${cigar.wrapperShade.label} depth with $topNotes layers rewards a full stomach after dinner (~${cigar.smokeTimeMinutes} min)."
            SmokeTimeOfDay.LATE_NIGHT ->
                "Dense, slow-burning ${cigar.strength.label.lowercase()} complexity packed with $topNotes is built for unhurried nightcap contemplation (~${cigar.smokeTimeMinutes} min)."
        }

        val drinkHint = when (primary) {
            SmokeTimeOfDay.MORNING -> "☕ Best alongside espresso, Cuban cafecito, cappuccino, or roasted oolong."
            SmokeTimeOfDay.MIDDAY -> "🍵 Shines with iced latte, crisp lager, ginger beer, or light Speyside."
            SmokeTimeOfDay.AFTERNOON -> "🥃 Ideal with aged rum, bourbon Old Fashioned, amber ale, or chai."
            SmokeTimeOfDay.EVENING -> "🍷 Built for single-barrel bourbon, tawny port, XO cognac, or imperial stout."
            SmokeTimeOfDay.LATE_NIGHT -> "✨ Reserve for peated Islay Scotch, cask-strength rye, vintage port, or dark cacao."
        }

        return SmokeTimeRecommendation(
            primaryTime = primary,
            secondaryTime = secondary,
            windowLabel = primary.windowRange,
            rationale = rationale,
            idealDrinkHint = drinkHint,
        )
    }

    fun matchesTimeFilter(cigar: Cigar, filter: SmokeTimeOfDay?): Boolean {
        if (filter == null) return true
        val rec = recommendFor(cigar)
        return rec.primaryTime == filter
    }

    fun topCigarsForTime(time: SmokeTimeOfDay, limit: Int = 8): List<Cigar> {
        val ranked = PairingCatalog.cigars
            .filter { recommendFor(it).primaryTime == time }
            .sortedWith(
                compareByDescending<Cigar> { CigarAccoladesCatalog.forCigar(it).isCigarOfTheYear }
                    .thenByDescending { CigarAccoladesCatalog.forCigar(it).cigarAficionadoRating ?: 0 }
                    .thenByDescending { CigarAccoladesCatalog.forCigar(it).hasCelebrityNotes }
            )
        val picked = mutableListOf<Cigar>()
        val seenBrands = mutableSetOf<String>()
        for (cigar in ranked) {
            if (picked.size >= limit) break
            if (seenBrands.add(cigar.brand.lowercase())) {
                picked.add(cigar)
            }
        }
        return picked
    }
}
