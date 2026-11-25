package com.example

import com.example.data.catalog.CelebrityCartoonTraitsMap
import com.example.data.catalog.CiSuperstoreDirectory
import com.example.data.catalog.CigarAccoladesCatalog
import com.example.data.catalog.CigarCelebrityDirectory
import com.example.data.catalog.FunFactsCatalog
import com.example.data.catalog.PairingCatalog
import com.example.data.history.CigarHistoryCatalog
import com.example.data.pairing.CigarSubstituteEngine
import com.example.data.pairing.PairingQuizAnswers
import com.example.data.pairing.PairingQuizEngine
import com.example.data.pairing.QuizDrinkPreference
import com.example.data.pairing.QuizFlavorMood
import com.example.data.pairing.QuizSessionLength
import com.example.data.pairing.SmokeTimeAdvisor
import com.example.data.pairing.SubstituteFilter
import com.example.model.EyeColor
import com.example.model.HairColor
import com.example.model.HairStyle
import com.example.model.Headwear
import com.example.model.PairingRatingSummary
import com.example.model.PairingStrategy
import com.example.model.PortraitGender
import com.example.model.SkinTone
import com.example.model.SmokeTimeOfDay
import com.example.model.StrengthLevel
import com.example.model.buildPairingId
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun catalogHasExpandedUniqueCigarsAndBeverages() {
        assertTrue("Expected >= 770 cigars, got ${PairingCatalog.cigars.size}", PairingCatalog.cigars.size >= 770)
        assertTrue("Expected >= 155 beverages, got ${PairingCatalog.beverages.size}", PairingCatalog.beverages.size >= 155)
        val alcoholicCount = PairingCatalog.beverages.count { it.isAlcoholic }
        assertTrue("Expected >= 130 alcoholic options, got $alcoholicCount", alcoholicCount >= 130)
        assertEquals(PairingCatalog.cigars.size, PairingCatalog.cigars.map { it.id }.toSet().size)
        assertEquals(PairingCatalog.beverages.size, PairingCatalog.beverages.map { it.id }.toSet().size)
    }

    @Test
    fun stgCigarsInternationalPortfolioIsComprehensivelyCovered() {
        val brandCounts = PairingCatalog.cigars.groupingBy { it.brand }.eachCount()
        assertTrue("Expected >= 30 CAO cigars, got ${brandCounts["CAO"] ?: 0}", (brandCounts["CAO"] ?: 0) >= 30)
        assertTrue("Expected >= 20 Cohiba cigars, got ${brandCounts["Cohiba"] ?: 0}", (brandCounts["Cohiba"] ?: 0) >= 20)
        assertTrue("Expected >= 30 Alec Bradley cigars, got ${brandCounts["Alec Bradley"] ?: 0}", (brandCounts["Alec Bradley"] ?: 0) >= 30)
        assertTrue("Expected >= 25 Macanudo cigars, got ${brandCounts["Macanudo"] ?: 0}", (brandCounts["Macanudo"] ?: 0) >= 25)
    }

    @Test
    fun catalogIncludesAllRequestedCigarBrands() {
        val brandCounts = PairingCatalog.cigars.groupingBy { it.brand }.eachCount()
        val expectedBrands = listOf(
            "CAO",
            "Cohiba",
            "Macanudo",
            "Alec Bradley",
            "Diesel",
            "Man O' War",
            "Ave Maria",
            "Nica Libre",
            "ACID",
            "AJ Fernandez",
            "Archetype",
            "Caldwell",
            "Casa Magna",
            "Casa Turrent",
            "Casdagli",
            "Cavalier Genève",
            "Chillin' Moose",
            "CI Legends",
            "Cuban Rounds",
            "Cuesta-Rey",
            "Curivari",
            "Dapper",
            "Davidoff",
            "Deadwood",
            "Diplomáticos",
            "E.P. Carrillo",
            "Fonseca",
            "Foundation",
            "Gispert",
            "Gran Habano",
            "Graycliff",
            "Gurkha",
            "Illusione",
            "Isla del Sol",
            "Joya de Nicaragua",
            "Juan López",
            "Kristoff",
            "La Aroma de Cuba",
            "La Aurora",
            "La Flor Dominicana",
            "La Palina",
            "La Perla Habana",
            "Mark Twain",
            "My Father",
            "Nica Rustica",
            "Nub",
            "Obsidian",
            "Oliva",
            "Padrón",
            "Patina",
            "Perdomo",
            "Plasencia",
            "Por Larrañaga",
            "Quintero",
            "Ramón Allones",
            "Rocky Patel",
            "RoMa Craft",
            "San Cristóbal",
            "Southern Draw",
            "Tabak Especial",
            "Tatuaje",
            "Te-Amo",
            "VegaFina",
            "Villa Zamorano",
            "Warped",
            "Zino",
        )
        expectedBrands.forEach { brand ->
            assertTrue(
                "Expected at least 2 cigars for brand $brand, found ${brandCounts[brand] ?: 0}",
                (brandCounts[brand] ?: 0) >= 2
            )
        }
    }

    @Test
    fun everyCigarHasAlcoholicAndNonAlcoholicPairings() {
        PairingCatalog.cigars.forEach { cigar ->
            val suggestions = PairingCatalog.pairingsForCigar(cigar.id, emptyMap())
            assertTrue("${cigar.name} needs >= 4 alcoholic pairings", suggestions.count { it.beverage.isAlcoholic } >= 4)
            assertTrue("${cigar.name} needs >= 4 zero-proof pairings", suggestions.count { !it.beverage.isAlcoholic } >= 4)
            suggestions.forEach { suggestion ->
                val breakdown = suggestion.breakdown
                assertNotNull("Expected PairingBreakdown for ${suggestion.pairingId}", breakdown)
                breakdown!!
                assertTrue(breakdown.flavorHarmony in 0..100)
                assertTrue(breakdown.bodyBalance in 0..100)
                assertTrue(breakdown.wrapperAffinity in 0..100)
                assertTrue(breakdown.sessionPacing in 0..100)
                assertTrue(breakdown.counterpointNotes.isNotEmpty())
                assertTrue(breakdown.bestThirdWindow.isNotBlank())
                assertTrue(breakdown.sommelierRule.isNotBlank())
            }
        }
    }

    @Test
    fun everyBeverageHasCigarPairings() {
        PairingCatalog.beverages.forEach { bev ->
            val suggestions = PairingCatalog.pairingsForBeverage(bev.id, emptyMap())
            assertTrue("${bev.name} needs >= 5 cigar pairings", suggestions.size >= 5)
        }
    }

    @Test
    fun pairingEngineGuardsAgainstOverpoweringMildClaroCigars() {
        val mildClaro = PairingCatalog.cigarById("davidoff_sig2")!!
        val oolong = PairingCatalog.beverageById("roasted_oolong")!!
        val islay = PairingCatalog.beverageById("islay_peated")!!

        val gentleMatch = PairingCatalog.evaluatePairing(mildClaro, oolong)
        val heavyMatch = PairingCatalog.evaluatePairing(mildClaro, islay)

        assertTrue(
            "Mild Claro should score higher body balance with Roasted Oolong (${gentleMatch.breakdown!!.bodyBalance}) than Islay (${heavyMatch.breakdown!!.bodyBalance})",
            gentleMatch.breakdown!!.bodyBalance > heavyMatch.breakdown!!.bodyBalance
        )
        assertTrue(
            "Overpower alert expected in sommelierRule",
            heavyMatch.breakdown!!.sommelierRule.contains("Overpower", ignoreCase = true)
        )
    }

    @Test
    fun pairingEngineRewardsCaskAgedAffinityAndSupportsStrategies() {
        val sherryDiesel = PairingCatalog.cigarById("diesel_whiskey_row_sherry")!!
        val speyside = PairingCatalog.beverageById("speyside_sherry")!!
        val gingerBeer = PairingCatalog.beverageById("fiery_ginger_beer")!!
        val cacao = PairingCatalog.beverageById("drinking_cacao")!!

        val caskSuggestion = PairingCatalog.evaluatePairing(sherryDiesel, speyside)
        assertTrue(caskSuggestion.breakdown!!.wrapperAffinity >= 88)

        val cleanserGinger = PairingCatalog.evaluatePairing(
            sherryDiesel,
            gingerBeer,
            strategy = PairingStrategy.PALATE_CLEANSER,
        )
        val cleanserCacao = PairingCatalog.evaluatePairing(
            sherryDiesel,
            cacao,
            strategy = PairingStrategy.PALATE_CLEANSER,
        )
        assertTrue(
            "Palate Reset strategy should score ginger beer (${cleanserGinger.matchScore}) above heavy drinking cacao (${cleanserCacao.matchScore})",
            cleanserGinger.matchScore > cleanserCacao.matchScore
        )
    }

    @Test
    fun everyCigarAndBrandHasCompleteHistoryLesson() {
        val uniqueBrands = PairingCatalog.cigars.map { it.brand }.toSet()
        uniqueBrands.forEach { brand ->
            val company = CigarHistoryCatalog.companyForBrand(brand)
            assertNotNull("Missing company history for brand: $brand", company)
            company!!
            assertTrue("$brand missing foundedYear", company.foundedYear.isNotBlank())
            assertTrue("$brand missing founder", company.founder.isNotBlank())
            assertTrue("$brand missing currentOwner", company.currentOwner.isNotBlank())
            assertTrue("$brand missing headquartersAndFactory", company.headquartersAndFactory.isNotBlank())
            assertTrue("$brand missing heritageStory", company.heritageStory.isNotBlank())
        }

        PairingCatalog.cigars.forEach { cigar ->
            val release = CigarHistoryCatalog.releaseForCigar(cigar.id)
            assertNotNull("Missing release history for cigar: ${cigar.id}", release)
            release!!
            assertTrue("${cigar.id} missing launchYear", release.launchYear.isNotBlank())
            assertTrue("${cigar.id} missing blenderAndFactory", release.blenderAndFactory.isNotBlank())
            assertTrue("${cigar.id} missing productionLocation", release.productionLocation.isNotBlank())
            assertTrue("${cigar.id} missing storyAndLegacy", release.storyAndLegacy.isNotBlank())
        }
    }

    @Test
    fun evaluatePairingIncludesAverageRatingWhenPresent() {
        val cigar = PairingCatalog.cigars.first()
        val bev = PairingCatalog.beverages.last()
        val pid = buildPairingId(cigar.id, bev.id)
        val summary = PairingRatingSummary(pairingId = pid, averageRating = 4.6, reviewCount = 5)
        val suggestion = PairingCatalog.evaluatePairing(cigar, bev, summary)

        assertTrue(suggestion.matchScore in 50..99)
        assertTrue(suggestion.averageRating == 4.6)
        assertTrue(suggestion.reviewCount == 5)
    }

    @Test
    fun everyCigarHasCigarAficionadoAccoladeAndWinnersAreFlagged() {
        PairingCatalog.cigars.forEach { cigar ->
            val accolade = CigarAccoladesCatalog.forCigar(cigar)
            assertTrue("${cigar.id} missing ratingSourceLabel", accolade.ratingSourceLabel.isNotBlank())
            if (accolade.cigarAficionadoRating != null) {
                assertTrue(
                    "Unexpected CA score ${accolade.cigarAficionadoRating} for ${cigar.id}",
                    accolade.cigarAficionadoRating in 80..100
                )
            }
        }

        val expectedWinners = listOf(
            "my_father_the_judge",
            "fuente_opusx",
            "padron_1964_natural",
            "ep_carrillo_pledge",
            "aging_room_quattro",
            "ep_carrillo_encore",
            "lfd_andalusian_bull",
            "my_father_le_bijou",
            "oliva_melanio",
            "montecristo_no2",
            "my_father_antillas",
            "alec_prensado",
            "cohiba_behike_52",
            "padron_family_reserve_45",
            "ci_casa_magna_colorado",
            "padron_1926_no9",
        )
        expectedWinners.forEach { id ->
            val cigar = PairingCatalog.cigarById(id)
            assertNotNull("Expected COTY cigar in catalog: $id", cigar)
            val accolade = CigarAccoladesCatalog.forCigar(cigar!!)
            assertTrue("Expected $id to be flagged as Cigar of the Year", accolade.isCigarOfTheYear)
            assertNotNull("Expected published CA score for $id", accolade.cigarAficionadoRating)
        }

        val celebrityIds = listOf(
            "ryj_churchill_cuban",
            "partagas_d4",
            "fuente_opusx",
            "cohiba_comador_toro",
            "espinosa_knuckle_sandwich_habano",
            "montecristo_no2",
            "padron_1926_no9",
        )
        celebrityIds.forEach { id ->
            val cigar = PairingCatalog.cigarById(id)
            assertNotNull("Expected celebrity cigar in catalog: $id", cigar)
            val accolade = CigarAccoladesCatalog.forCigar(cigar!!)
            assertTrue("Expected celebrity notes for $id", accolade.hasCelebrityNotes)
        }
    }

    @Test
    fun internationalCelebritiesAreCoveredAcrossGlobalIcons() {
        val internationalCigars = PairingCatalog.cigars.filter {
            CigarAccoladesCatalog.forCigar(it).hasInternationalCelebrity
        }
        assertTrue(
            "Expected broad international celebrity coverage (>= 120 cigars), got ${internationalCigars.size}",
            internationalCigars.size >= 120
        )

        val spotlightIds = listOf(
            "cohiba_behike_52",
            "cohiba_siglo6",
            "montecristo_no2",
            "partagas_d4",
            "ryj_churchill_cuban",
            "davidoff_sig2",
            "padron_1926_no9",
            "fuente_opusx",
        )
        spotlightIds.forEach { id ->
            val cigar = PairingCatalog.cigarById(id)
            assertNotNull("Expected spotlight cigar $id", cigar)
            val accolade = CigarAccoladesCatalog.forCigar(cigar!!)
            assertTrue("Expected celebrity notes for $id", accolade.hasCelebrityNotes)
            assertTrue("Expected international celebrity flag for $id", accolade.hasInternationalCelebrity)
        }
    }

    @Test
    fun cigarSubstituteEngineFindsPracticalPointOfPurchaseMatches() {
        val cubanBehike = PairingCatalog.cigarById("cohiba_behike_52")!!
        val cognac = PairingCatalog.beverageById("xo_cognac")!!

        val newWorldSubs = CigarSubstituteEngine.findSubstitutes(
            target = cubanBehike,
            pairedBeverage = cognac,
            filter = SubstituteFilter.NON_CUBAN,
            limit = 6,
        )
        assertEquals(6, newWorldSubs.size)
        newWorldSubs.forEach { match ->
            assertFalse("NON_CUBAN filter returned Cuban cigar: ${match.substitute.id}", CigarSubstituteEngine.isCuban(match.substitute))
            assertTrue("Expected similarity in 55..99, got ${match.similarityScore}", match.similarityScore in 55..99)
            assertNotNull("Expected beverage re-pairing score", match.pairingScoreWithBeverage)
            assertTrue(match.matchReason.isNotBlank())
            assertTrue(match.availabilityBadge.isNotBlank())
        }
        // Brand diversity check when no shelf query is entered
        assertEquals(newWorldSubs.size, newWorldSubs.map { it.substitute.brand }.toSet().size)

        // Exact strength filter check
        val padron = PairingCatalog.cigarById("padron_1964")!!
        val sameStrengthSubs = CigarSubstituteEngine.findSubstitutes(
            target = padron,
            filter = SubstituteFilter.SAME_STRENGTH,
            limit = 5,
        )
        assertEquals(5, sameStrengthSubs.size)
        assertTrue(sameStrengthSubs.all { it.substitute.strength == padron.strength })

        // Widely stocked shelf query check
        val shopShelfMatches = CigarSubstituteEngine.findSubstitutes(
            target = padron,
            filter = SubstituteFilter.WIDELY_STOCKED,
            shelfQuery = "Perdomo",
            limit = 4,
        )
        assertTrue("Expected Perdomo shop-shelf substitutes", shopShelfMatches.isNotEmpty())
        assertTrue(shopShelfMatches.all { it.substitute.brand.contains("Perdomo", ignoreCase = true) && it.isWidelyAvailable })

        // Tobacconist Ask Tip check
        val tip = CigarSubstituteEngine.buildTobacconistAskTip(cubanBehike, cognac)
        assertTrue(tip.contains(cubanBehike.name))
        assertTrue(tip.contains(cognac.name))
        assertTrue(tip.contains("Nicaraguan or Dominican", ignoreCase = true))
    }

    @Test
    fun everyCigarHasSuggestedTimeOfDayRecommendation() {
        val countsByTime = mutableMapOf<SmokeTimeOfDay, Int>()
        PairingCatalog.cigars.forEach { cigar ->
            val rec = SmokeTimeAdvisor.recommendFor(cigar)
            assertTrue(rec.windowLabel.isNotBlank())
            assertTrue(rec.rationale.isNotBlank())
            assertTrue(rec.idealDrinkHint.isNotBlank())
            countsByTime[rec.primaryTime] = (countsByTime[rec.primaryTime] ?: 0) + 1
        }
        // All 5 time-of-day windows should be represented across the catalog
        SmokeTimeOfDay.entries.forEach { slot ->
            assertTrue("Expected cigars for ${slot.label}", (countsByTime[slot] ?: 0) >= 20)
        }
        val morningCigar = PairingCatalog.cigarById("macanudo_cafe")!!
        assertEquals(SmokeTimeOfDay.MORNING, SmokeTimeAdvisor.recommendFor(morningCigar).primaryTime)

        val lateNightCigar = PairingCatalog.cigarById("davidoff_late_hour")!!
        assertEquals(SmokeTimeOfDay.LATE_NIGHT, SmokeTimeAdvisor.recommendFor(lateNightCigar).primaryTime)
    }

    @Test
    fun pairingQuizEngineProducesDiverseTailoredResults() {
        val morningCoffeeQuiz = PairingQuizEngine.evaluateQuiz(
            PairingQuizAnswers(
                timeOfDay = SmokeTimeOfDay.MORNING,
                preferredStrength = StrengthLevel.MILD,
                flavorMood = QuizFlavorMood.CREAM_CARAMEL,
                drinkPreference = QuizDrinkPreference.COFFEE_CACAO,
                sessionLength = QuizSessionLength.QUICK,
            )
        )
        assertEquals(5, morningCoffeeQuiz.topPicks.size)
        assertTrue(morningCoffeeQuiz.archetypeTitle.isNotBlank())
        assertTrue(morningCoffeeQuiz.topPicks.all { !it.suggestion.beverage.isAlcoholic })
        assertEquals(5, morningCoffeeQuiz.topPicks.map { it.suggestion.cigar.brand }.toSet().size)

        val lateNightWhiskeyQuiz = PairingQuizEngine.evaluateQuiz(
            PairingQuizAnswers(
                timeOfDay = SmokeTimeOfDay.LATE_NIGHT,
                preferredStrength = StrengthLevel.FULL,
                flavorMood = QuizFlavorMood.COCOA_ESPRESSO,
                drinkPreference = QuizDrinkPreference.WHISKEY,
                sessionLength = QuizSessionLength.LEISURELY,
            )
        )
        assertEquals(5, lateNightWhiskeyQuiz.topPicks.size)
        assertTrue(lateNightWhiskeyQuiz.topPicks.all { it.suggestion.beverage.isAlcoholic })
        assertTrue(lateNightWhiskeyQuiz.topPicks.all { it.quizFitScore in 72..99 })
    }

    @Test
    fun smokeTimeAdvisorDetectsLiveHoursAndCuratesDiverseTopPicks() {
        assertEquals(SmokeTimeOfDay.MORNING, SmokeTimeAdvisor.timeOfDayForHour(8))
        assertEquals(SmokeTimeOfDay.MIDDAY, SmokeTimeAdvisor.timeOfDayForHour(12))
        assertEquals(SmokeTimeOfDay.AFTERNOON, SmokeTimeAdvisor.timeOfDayForHour(16))
        assertEquals(SmokeTimeOfDay.EVENING, SmokeTimeAdvisor.timeOfDayForHour(20))
        assertEquals(SmokeTimeOfDay.LATE_NIGHT, SmokeTimeAdvisor.timeOfDayForHour(23))
        assertEquals(SmokeTimeOfDay.LATE_NIGHT, SmokeTimeAdvisor.timeOfDayForHour(1))

        SmokeTimeOfDay.entries.forEach { slot ->
            val picks = SmokeTimeAdvisor.topCigarsForTime(slot, limit = 8)
            assertEquals("Expected 8 picks for ${slot.label}", 8, picks.size)
            assertEquals("Expected 8 distinct brands for ${slot.label}", 8, picks.map { it.brand }.toSet().size)
            assertTrue(picks.all { SmokeTimeAdvisor.recommendFor(it).primaryTime == slot })
        }
    }

    @Test
    fun celebrityDirectoryHasRichLocalAndWorldwideCartoonProfiles() {
        val profiles = CigarCelebrityDirectory.allCelebrityProfiles
        assertTrue("Expected >= 100 celebrity profiles, got ${profiles.size}", profiles.size >= 100)
        assertEquals("Celebrity profile IDs must be unique", profiles.size, profiles.map { it.id }.toSet().size)
        val internationalCount = profiles.count { it.isInternational }
        val localCount = profiles.count { !it.isInternational }
        assertTrue("Expected >= 60 international celebrity profiles, got $internationalCount", internationalCount >= 60)
        assertTrue("Expected >= 35 local US celebrity profiles, got $localCount", localCount >= 35)
        profiles.forEach { p ->
            assertTrue("${p.name} missing flagEmoji", p.flagEmoji.isNotBlank())
            assertTrue("${p.name} missing note", p.note.isNotBlank())
        }

        val spotlight = CigarCelebrityDirectory.featuredSpotlightProfiles()
        assertTrue("Expected >= 12 featured spotlight celebrities, got ${spotlight.size}", spotlight.size >= 12)

        val cohiba = PairingCatalog.cigarById("cohiba_behike_52")!!
        val accolade = CigarAccoladesCatalog.forCigar(cohiba)
        val matched = CigarCelebrityDirectory.profilesForNotes(accolade.celebrityNotes, cohiba.brand)
        assertTrue("Expected >= 5 matched celebrity cartoon profiles for Cohiba Behike", matched.size >= 5)
    }

    @Test
    fun everyCelebrityHasExplicitAccurateCartoonTraitsMapping() {
        val profiles = CigarCelebrityDirectory.allCelebrityProfiles
        profiles.forEach { profile ->
            assertTrue(
                "Missing explicit CartoonTraits mapping for ${profile.id} (${profile.name})",
                CelebrityCartoonTraitsMap.hasExplicitMapping(profile.id)
            )
        }

        val byId = profiles.associateBy { it.id }

        // Spot-check key celebrities for faithful visual trait matching
        val sinatraTraits = CelebrityCartoonTraitsMap.traitsFor(byId["frank_sinatra"]!!)
        assertEquals(SkinTone.LIGHT, sinatraTraits.skinTone)
        assertEquals(EyeColor.BLUE, sinatraTraits.eyeColor)
        assertEquals(Headwear.FEDORA, sinatraTraits.headwear)

        val jordanTraits = CelebrityCartoonTraitsMap.traitsFor(byId["michael_jordan"]!!)
        assertEquals(SkinTone.DEEP, jordanTraits.skinTone)
        assertEquals(HairStyle.BALD, jordanTraits.hairStyle)

        val rihannaTraits = CelebrityCartoonTraitsMap.traitsFor(byId["rihanna"]!!)
        assertEquals(PortraitGender.FEMININE, rihannaTraits.gender)
        assertEquals(SkinTone.BROWN, rihannaTraits.skinTone)

        val lorenTraits = CelebrityCartoonTraitsMap.traitsFor(byId["sophia_loren"]!!)
        assertEquals(PortraitGender.FEMININE, lorenTraits.gender)
        assertEquals(SkinTone.OLIVE, lorenTraits.skinTone)

        val fieriTraits = CelebrityCartoonTraitsMap.traitsFor(byId["guy_fieri"]!!)
        assertEquals(HairStyle.SPIKY, fieriTraits.hairStyle)
        assertEquals(HairColor.BLEACHED, fieriTraits.hairColor)

        val churchillTraits = CelebrityCartoonTraitsMap.traitsFor(byId["winston_churchill"]!!)
        assertEquals(SkinTone.FAIR, churchillTraits.skinTone)
        assertEquals(Headwear.BOWLER, churchillTraits.headwear)
    }

    @Test
    fun funFactsCatalogCoversEveryCigarAndBeverage() {
        assertTrue("Expected >= 30 lounge fun facts, got ${FunFactsCatalog.allFunFacts.size}", FunFactsCatalog.allFunFacts.size >= 30)
        PairingCatalog.cigars.forEach { cigar ->
            val fact = FunFactsCatalog.funFactForCigar(cigar)
            assertTrue(fact.headline.isNotBlank())
            assertTrue(fact.body.isNotBlank())
        }
        PairingCatalog.beverages.forEach { bev ->
            val fact = FunFactsCatalog.funFactForBeverage(bev)
            assertTrue(fact.headline.isNotBlank())
            assertTrue(fact.body.isNotBlank())
        }
    }

    @Test
    fun ciSuperstoreDirectoryHasAllLocationsLinksAndMapPins() {
        assertTrue("Expected >= 15 CI Superstores, got ${CiSuperstoreDirectory.superstores.size}", CiSuperstoreDirectory.superstores.size >= 15)
        assertEquals(CiSuperstoreDirectory.superstores.size, CiSuperstoreDirectory.superstores.map { it.id }.toSet().size)
        assertTrue(CiSuperstoreDirectory.OFFICIAL_WEBSITE_URL.startsWith("https://"))
        assertTrue(CiSuperstoreDirectory.ONLINE_SHOPPING_GOOGLE_REVIEW_URL.startsWith("https://"))
        assertTrue(CiSuperstoreDirectory.socialChannels.size >= 4)
        CiSuperstoreDirectory.socialChannels.forEach { channel ->
            assertTrue("Invalid social URL for ${channel.label}", channel.url.startsWith("https://"))
        }
        CiSuperstoreDirectory.superstores.forEach { store ->
            assertTrue(store.address.isNotBlank())
            assertTrue("mapX out of bounds for ${store.id}", store.mapX in 0.05f..0.95f)
            assertTrue("mapY out of bounds for ${store.id}", store.mapY in 0.05f..0.95f)
        }
    }
}
