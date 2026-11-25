package com.example.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.WrapperClaro
import com.example.ui.theme.WrapperColorado
import com.example.ui.theme.WrapperColoradoClaro
import com.example.ui.theme.WrapperMaduro
import com.example.ui.theme.WrapperOscuro

data class FlavorAxes(
    val woodCedar: Float,
    val cocoaRoast: Float,
    val spicePepper: Float,
    val caramelSweet: Float,
    val earthSmoke: Float,
    val creamVanilla: Float,
    val nutMalt: Float,
    val fruitZest: Float,
) {
    fun asLabeledList(): List<Pair<String, Float>> = listOf(
        "Cedar" to woodCedar,
        "Cocoa" to cocoaRoast,
        "Spice" to spicePepper,
        "Caramel" to caramelSweet,
        "Earth" to earthSmoke,
        "Cream" to creamVanilla,
        "Nut/Malt" to nutMalt,
        "Fruit" to fruitZest,
    )

    fun topAxes(count: Int = 3): List<String> =
        asLabeledList()
            .sortedByDescending { it.second }
            .take(count)
            .map { it.first }
}

enum class StrengthLevel(val label: String, val dots: Int, val emoji: String) {
    MILD("Mild", 2, "🌿"),
    MEDIUM("Medium", 3, "🪵"),
    MEDIUM_FULL("Medium-Full", 4, "🌶️"),
    FULL("Full", 5, "🌋"),
}

enum class WrapperShade(val label: String, val swatchColor: Color) {
    CLARO("Claro", WrapperClaro),
    COLORADO_CLARO("Colorado Claro", WrapperColoradoClaro),
    COLORADO("Colorado", WrapperColorado),
    MADURO("Maduro", WrapperMaduro),
    OSCURO("Oscuro", WrapperOscuro),
}

enum class BeverageCategory(val label: String, val isAlcoholic: Boolean, val emoji: String) {
    WHISKEY("Whiskey & Scotch", true, "🥃"),
    RUM("Rum & Cachaça", true, "🍹"),
    BRANDY("Cognac & Brandy", true, "🥂"),
    COCKTAILS("Classic Cocktails", true, "🍸"),
    WINE("Wine, Port & Sherry", true, "🍷"),
    BEER("Craft Beer & Cider", true, "🍺"),
    AGAVE("Tequila & Mezcal", true, "🌵"),
    COFFEE("Coffee & Espresso", false, "☕"),
    TEA("Artisanal Tea", false, "🍵"),
    CRAFT_SODA("Craft Sodas", false, "🫧"),
    CACAO("Cacao & Warm", false, "🍫"),
    ZERO_PROOF_ELIXIRS("Zero-Proof Cocktails", false, "🌿"),
}

enum class BeverageFilter(val label: String, val emoji: String) {
    ALL("All", "✨"),
    ALCOHOLIC("Alcoholic", "🥃"),
    NON_ALCOHOLIC("Zero-Proof", "☕"),
}

enum class BodyLevel(val label: String, val dots: Int) {
    LIGHT("Light", 2),
    MEDIUM("Medium", 3),
    FULL("Full", 5),
}

enum class SmokeTimeOfDay(
    val label: String,
    val shortLabel: String,
    val emoji: String,
    val windowRange: String,
) {
    MORNING("Morning Smoke", "Morning", "☀️", "7 AM – 11 AM"),
    MIDDAY("Midday & Golf", "Midday", "🌤️", "11 AM – 3 PM"),
    AFTERNOON("Afternoon Lounge", "Afternoon", "🌇", "3 PM – 6 PM"),
    EVENING("After Dinner", "Evening", "🌙", "6 PM – 10 PM"),
    LATE_NIGHT("Late Night Reserve", "Late Night", "✨", "10 PM – Late"),
}

data class SmokeTimeRecommendation(
    val primaryTime: SmokeTimeOfDay,
    val secondaryTime: SmokeTimeOfDay? = null,
    val windowLabel: String,
    val rationale: String,
    val idealDrinkHint: String,
)

enum class CelebrityArchetype(val label: String, val emoji: String) {
    STATESMAN("Statesman & Heritage", "🎩"),
    CINEMA("Cinema & TV Icon", "🎬"),
    ATHLETE("Championship Legend", "🏆"),
    MUSIC("Music & Stage Star", "🎷"),
    GOURMET("Culinary & Culture", "🍽️"),
}

enum class PortraitGender { MASCULINE, FEMININE }

enum class SkinTone(val baseHex: Long, val shadowHex: Long) {
    FAIR(0xFFFBE0C8L, 0xFFE5BEA0L),
    LIGHT(0xFFF3CFAEL, 0xFFD9AE89L),
    OLIVE(0xFFE0B489L, 0xFFC29064L),
    TAN(0xFFC99465L, 0xFFA87347L),
    BROWN(0xFF9C6640L, 0xFF7B4C2BL),
    DEEP(0xFF6B4128L, 0xFF4D2C19L),
}

enum class HairStyle {
    BALD,
    BUZZ,
    SHORT_PART,
    SLICK_BACK,
    CURLY,
    SPIKY,
    LONG_WAVY,
    LOCS_BRAIDS,
    RECEDING_SILVER,
    UPDO_GLAM,
}

enum class HairColor(val hex: Long) {
    BLACK(0xFF1A1717L),
    DARK_BROWN(0xFF3A2518L),
    BROWN(0xFF6A452CL),
    AUBURN(0xFF8C3B24L),
    BLONDE(0xFFD9B262L),
    BLEACHED(0xFFF4E49CL),
    GRAY(0xFF9DA3A8L),
    WHITE(0xFFEAECEFL),
}

enum class FacialHair {
    NONE,
    STUBBLE,
    MUSTACHE,
    WALRUS_STACHE,
    GOATEE,
    FULL_BEARD,
}

enum class EyeColor(val hex: Long) {
    DARK(0xFF2B1B14L),
    BLUE(0xFF2B7AC4L),
    GREEN(0xFF2E7D52L),
    HAZEL(0xFF7A5B2BL),
}

enum class Headwear {
    NONE,
    FEDORA,
    BOWLER,
    FLAT_CAP,
    BASEBALL_CAP,
    COWBOY_HAT,
    HEADWRAP,
}

enum class GlassesStyle {
    NONE,
    CLASSIC_ROUND,
    SQUARE_FRAMES,
    SUNGLASSES,
    AVIATOR,
    CAT_EYE,
}

enum class OutfitStyle {
    TUXEDO,
    SUIT_TIE,
    JERSEY_SPORT,
    CHEF_COAT,
    ROCK_JACKET,
    CASUAL_POLO,
}

data class CartoonTraits(
    val gender: PortraitGender = PortraitGender.MASCULINE,
    val skinTone: SkinTone = SkinTone.LIGHT,
    val hairStyle: HairStyle = HairStyle.SHORT_PART,
    val hairColor: HairColor = HairColor.DARK_BROWN,
    val facialHair: FacialHair = FacialHair.NONE,
    val eyeColor: EyeColor = EyeColor.DARK,
    val headwear: Headwear = Headwear.NONE,
    val glasses: GlassesStyle = GlassesStyle.NONE,
    val outfit: OutfitStyle = OutfitStyle.SUIT_TIE,
)

data class CelebrityProfile(
    val id: String,
    val name: String,
    val country: String,
    val flagEmoji: String,
    val isInternational: Boolean,
    val archetype: CelebrityArchetype,
    val roleTag: String,
    val note: String,
    val favoriteBrand: String,
    val hasGlasses: Boolean = false,
    val hasHat: Boolean = false,
    val hasBeardOrStache: Boolean = false,
    val accentHex: Long = 0xFFD9A326L,
    val traits: CartoonTraits = CartoonTraits(),
)

data class Cigar(
    val id: String,
    val name: String,
    val brand: String,
    val origin: String,
    val wrapper: String,
    val wrapperShade: WrapperShade,
    val vitola: String,
    val dimensions: String,
    val strength: StrengthLevel,
    val smokeTimeMinutes: Int,
    val flavorAxes: FlavorAxes,
    val tastingNotes: List<String>,
    val thirdProgression: Triple<String, String, String>,
    val summary: String,
)

data class Beverage(
    val id: String,
    val name: String,
    val subtitle: String,
    val category: BeverageCategory,
    val abvLabel: String,
    val servingTemp: String,
    val glassware: String,
    val body: BodyLevel,
    val flavorAxes: FlavorAxes,
    val tastingNotes: List<String>,
    val palateBreakdown: Triple<String, String, String>,
    val summary: String,
) {
    val isAlcoholic: Boolean get() = category.isAlcoholic
}

enum class PairingStrategy(
    val label: String,
    val emoji: String,
    val shortDescription: String,
) {
    BALANCED(
        label = "All Styles",
        emoji = "⚖️",
        shortDescription = "Balanced sommelier scoring across harmony, body weight, wrapper affinity, and pacing.",
    ),
    COMPLEMENTARY(
        label = "Complementary",
        emoji = "🤝",
        shortDescription = "Flavor mirrors matching shared cocoa, cedar, honey, or smoky oak peaks.",
    ),
    CONTRASTING(
        label = "Contrasting",
        emoji = "⚡",
        shortDescription = "Sweetness, cream, or bright spice countering pepper, earth, and Maduro intensity.",
    ),
    PALATE_CLEANSER(
        label = "Palate Reset",
        emoji = "🫧",
        shortDescription = "Crisp carbonation, citrus, ginger, or brisk tea to lift oils between thirds.",
    ),
}

data class PairingBreakdown(
    val flavorHarmony: Int,
    val bodyBalance: Int,
    val wrapperAffinity: Int,
    val sessionPacing: Int,
    val counterpointNotes: List<String>,
    val bestThirdWindow: String,
    val sommelierRule: String,
)

data class CuratedPairing(
    val cigarId: String,
    val beverageId: String,
    val matchScore: Int,
    val pairingStyle: String,
    val whyItWorks: String,
    val servingTip: String,
) {
    val pairingId: String get() = buildPairingId(cigarId, beverageId)
}

data class PairingSuggestion(
    val pairingId: String,
    val cigar: Cigar,
    val beverage: Beverage,
    val matchScore: Int,
    val pairingStyle: String,
    val whyItWorks: String,
    val servingTip: String,
    val isCurated: Boolean,
    val bridgeNotes: List<String>,
    val breakdown: PairingBreakdown? = null,
    val averageRating: Double? = null,
    val reviewCount: Int = 0,
)

data class PairingRatingSummary(
    val pairingId: String,
    val averageRating: Double,
    val reviewCount: Int,
)

data class PairingReview(
    val id: Long = 0L,
    val pairingId: String,
    val cigarId: String,
    val beverageId: String,
    val rating: Int,
    val reviewText: String,
    val reviewerName: String,
    val isUserOwn: Boolean,
    val createdAt: Long,
)

data class CigarCompanyHistory(
    val brand: String,
    val foundedYear: String,
    val founder: String,
    val currentOwner: String,
    val headquartersAndFactory: String,
    val heritageStory: String,
)

data class CigarReleaseHistory(
    val cigarId: String,
    val launchYear: String,
    val blenderAndFactory: String,
    val productionLocation: String,
    val storyAndLegacy: String,
)

data class AudioTranscription(
    val id: Long = 0L,
    val title: String,
    val sourceLabel: String,
    val durationSeconds: Int,
    val styleLabel: String,
    val transcriptText: String,
    val createdAt: Long,
)

data class CigarAccolade(
    val cigarAficionadoRating: Int? = null,
    val ratingYear: String? = null,
    val ratingSourceLabel: String = "Cigar Aficionado",
    val ratingHighlight: String? = null,
    val cigarOfTheYearYear: String? = null,
    val celebrityNotes: List<String> = emptyList(),
    val hasInternationalCelebrity: Boolean = false,
) {
    val isCigarOfTheYear: Boolean get() = !cigarOfTheYearYear.isNullOrBlank()
    val hasCelebrityNotes: Boolean get() = celebrityNotes.isNotEmpty()
}

data class SavedPairing(
    val id: Long = 0L,
    val pairingId: String,
    val cigarId: String,
    val beverageId: String,
    val cigarName: String,
    val beverageName: String,
    val matchScore: Int,
    val pairingStyle: String,
    val strategyName: String,
    val personalNotes: String,
    val occasionTag: String,
    val isFavorite: Boolean,
    val timesEnjoyed: Int,
    val isCuratedSeed: Boolean,
    val createdAt: Long,
    val updatedAt: Long,
)

data class CigarSubstituteMatch(
    val substitute: Cigar,
    val similarityScore: Int,
    val availabilityBadge: String,
    val matchReason: String,
    val sharedNotes: List<String>,
    val pairingScoreWithBeverage: Int? = null,
    val isWidelyAvailable: Boolean = true,
)

data class QuizMatchPick(
    val suggestion: PairingSuggestion,
    val quizFitScore: Int,
    val fitReason: String,
    val timeRecommendation: SmokeTimeRecommendation,
)

data class PairingQuizResult(
    val archetypeEmoji: String,
    val archetypeTitle: String,
    val archetypeSummary: String,
    val recommendedTime: SmokeTimeOfDay,
    val topPicks: List<QuizMatchPick>,
)

enum class FunFactTopic(val label: String, val emoji: String) {
    CIGAR("Cigar Trivia", "🔥"),
    DRINK("Drink Trivia", "🥃"),
    PAIRING("Pairing Lore", "✨"),
}

data class FunFact(
    val id: String,
    val topic: FunFactTopic,
    val emoji: String,
    val headline: String,
    val body: String,
    val tag: String,
)

data class CiSuperstore(
    val id: String,
    val name: String,
    val city: String,
    val state: String,
    val address: String,
    val highlight: String,
    val latitude: Double,
    val longitude: Double,
    val mapX: Float, // 0f..1f normalized across continental US map canvas
    val mapY: Float, // 0f..1f normalized across continental US map canvas
    val isFlagship: Boolean = false,
)

data class SocialChannelLink(
    val id: String,
    val label: String,
    val handle: String,
    val emoji: String,
    val url: String,
)

data class EmailAlertSubscription(
    val email: String = "",
    val topics: Set<String> = emptySet(),
    val preferredStoreId: String = "",
) {
    val isSubscribed: Boolean get() = email.isNotBlank()
}

fun buildPairingId(cigarId: String, beverageId: String): String = "${cigarId}__${beverageId}"



