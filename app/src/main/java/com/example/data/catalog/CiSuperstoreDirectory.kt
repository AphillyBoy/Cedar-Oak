package com.example.data.catalog

import android.net.Uri
import com.example.model.CiSuperstore
import com.example.model.SocialChannelLink

object CiSuperstoreDirectory {

    const val OFFICIAL_WEBSITE_URL = "https://www.cigarsinternational.com/"
    const val OFFICIAL_STORE_LOCATOR_URL = "https://www.cigarsinternational.com/retail/"
    const val OFFICIAL_EMAIL_SIGNUP_URL = "https://www.cigarsinternational.com/email-signup/"
    const val ALL_STORES_GOOGLE_MAPS_URL =
        "https://www.google.com/maps/search/?api=1&query=Cigars+International+Superstore"
    const val ONLINE_SHOPPING_GOOGLE_REVIEW_URL =
        "https://www.google.com/search?q=Cigars+International+online+shopping+Google+reviews"
    const val ONLINE_GOOGLE_CUSTOMER_RATINGS_URL =
        "https://www.google.com/shopping/ratings/account/metrics?q=cigarsinternational.com&c=US&v=19"

    val socialChannels: List<SocialChannelLink> = listOf(
        SocialChannelLink(
            id = "instagram",
            label = "Instagram",
            handle = "@cigars.international",
            emoji = "📸",
            url = "https://www.instagram.com/cigars.international/",
        ),
        SocialChannelLink(
            id = "facebook",
            label = "Facebook",
            handle = "Cigars International",
            emoji = "👍",
            url = "https://www.facebook.com/CigarsInternational/",
        ),
        SocialChannelLink(
            id = "x_twitter",
            label = "X (Twitter)",
            handle = "@CigarsIntl",
            emoji = "𝕏",
            url = "https://x.com/CigarsIntl",
        ),
        SocialChannelLink(
            id = "youtube",
            label = "YouTube",
            handle = "Cigars International TV",
            emoji = "▶️",
            url = "https://www.youtube.com/user/CigarsInternational",
        ),
    )

    /**
     * All 15 Cigars International Superstores & Retail Lounges across PA, TX, FL, TN & KY
     * with real street addresses, coordinates, and normalized map pins.
     */
    val superstores: List<CiSuperstore> = listOf(
        // --- Pennsylvania (4) ---
        CiSuperstore(
            id = "ci_hamburg_pa",
            name = "CI Superstore — Hamburg (Flagship)",
            city = "Hamburg",
            state = "PA",
            address = "1 Costa Dr, Hamburg, PA 19526",
            highlight = "America's largest freestanding cigar superstore (22,000+ sq ft), full bar & giant walk-in humidor near Cabela's.",
            latitude = 40.556,
            longitude = -75.982,
            mapX = 0.80f,
            mapY = 0.34f,
            isFlagship = true,
        ),
        CiSuperstore(
            id = "ci_bethlehem_pa",
            name = "CI Superstore — Bethlehem",
            city = "Bethlehem",
            state = "PA",
            address = "4078 Nazareth Pike, Bethlehem, PA 18020",
            highlight = "The original Cigars International Superstore with massive humidor, lounge seating & full bar.",
            latitude = 40.665,
            longitude = -75.344,
            mapX = 0.84f,
            mapY = 0.32f,
            isFlagship = true,
        ),
        CiSuperstore(
            id = "ci_downtown_bethlehem_pa",
            name = "CI Downtown Bethlehem",
            city = "Bethlehem",
            state = "PA",
            address = "535 Main St, Bethlehem, PA 18018",
            highlight = "Historic Main Street boutique shop & lounge in the heart of downtown Bethlehem.",
            latitude = 40.621,
            longitude = -75.381,
            mapX = 0.86f,
            mapY = 0.36f,
        ),
        CiSuperstore(
            id = "ci_bridgeville_pa",
            name = "CI Superstore — Bridgeville (Pittsburgh)",
            city = "Bridgeville",
            state = "PA",
            address = "701 Holland St, Bridgeville, PA 15017",
            highlight = "Western Pennsylvania superstore & full-service lounge serving greater Pittsburgh.",
            latitude = 40.356,
            longitude = -80.110,
            mapX = 0.72f,
            mapY = 0.36f,
        ),

        // --- Texas (5) ---
        CiSuperstore(
            id = "ci_the_colony_tx",
            name = "CI Superstore — The Colony (Grandscape)",
            city = "The Colony",
            state = "TX",
            address = "3800 Plano Pkwy, The Colony, TX 75056",
            highlight = "Expansive DFW Grandscape destination with indoor/outdoor patio lounge, craft cocktails & live events.",
            latitude = 33.071,
            longitude = -96.862,
            mapX = 0.36f,
            mapY = 0.65f,
            isFlagship = true,
        ),
        CiSuperstore(
            id = "ci_fort_worth_tx",
            name = "CI Superstore — Fort Worth",
            city = "Fort Worth",
            state = "TX",
            address = "12853 Cabela Dr, Fort Worth, TX 76177",
            highlight = "North Texas Alliance superstore featuring a huge walk-in humidor, full bar & patio.",
            latitude = 32.961,
            longitude = -97.311,
            mapX = 0.31f,
            mapY = 0.67f,
        ),
        CiSuperstore(
            id = "ci_san_antonio_tx",
            name = "CI Superstore — San Antonio",
            city = "San Antonio",
            state = "TX",
            address = "5630 Landmark Pkwy, San Antonio, TX 78249",
            highlight = "The Landmark lounge & superstore with spacious leather seating, bar & Texas patio.",
            latitude = 29.591,
            longitude = -98.599,
            mapX = 0.29f,
            mapY = 0.78f,
        ),
        CiSuperstore(
            id = "ci_katy_tx",
            name = "CI Superstore — Katy (Houston West)",
            city = "Katy",
            state = "TX",
            address = "25407 Bell Patna Dr, Katy, TX 77492",
            highlight = "Greater Houston superstore with massive cigar selection, lounge TVs & full cocktail bar.",
            latitude = 29.786,
            longitude = -95.812,
            mapX = 0.36f,
            mapY = 0.77f,
        ),
        CiSuperstore(
            id = "ci_conroe_tx",
            name = "CI Superstore — Conroe (Houston North)",
            city = "Conroe",
            state = "TX",
            address = "16580 I-45 S, Conroe, TX 77384",
            highlight = "North Houston / The Woodlands superstore with expansive humidor & full-service lounge.",
            latitude = 30.226,
            longitude = -95.456,
            mapX = 0.40f,
            mapY = 0.73f,
        ),

        // --- Florida (4) ---
        CiSuperstore(
            id = "ci_tampa_fl",
            name = "CI Superstore — Tampa",
            city = "Tampa",
            state = "FL",
            address = "10660 Palm River Rd, Tampa, FL 33619",
            highlight = "Cigar City flagship superstore with massive humidor, bar & outdoor lounge terrace.",
            latitude = 27.926,
            longitude = -82.326,
            mapX = 0.74f,
            mapY = 0.84f,
            isFlagship = true,
        ),
        CiSuperstore(
            id = "ci_lutz_fl",
            name = "CI Superstore — Lutz (Tampa Bay North)",
            city = "Lutz",
            state = "FL",
            address = "2691 Creek Grass Way, Lutz, FL 33559",
            highlight = "North Tampa Bay destination lounge with full bar, walk-in humidor & patio seating.",
            latitude = 28.191,
            longitude = -82.393,
            mapX = 0.72f,
            mapY = 0.79f,
        ),
        CiSuperstore(
            id = "ci_orlando_fl",
            name = "CI Superstore — Orlando (Millenia)",
            city = "Orlando",
            state = "FL",
            address = "4158 Conroy Rd, Orlando, FL 32839",
            highlight = "Central Florida superstore near Millenia featuring curated cocktails & huge humidor.",
            latitude = 28.487,
            longitude = -81.430,
            mapX = 0.79f,
            mapY = 0.80f,
        ),
        CiSuperstore(
            id = "ci_jacksonville_fl",
            name = "CI Superstore — Jacksonville",
            city = "Jacksonville",
            state = "FL",
            address = "4808 Town Center Pkwy, Jacksonville, FL 32246",
            highlight = "The Strand at Town Center lounge with walk-in humidor, craft drinks & event space.",
            latitude = 30.256,
            longitude = -81.527,
            mapX = 0.77f,
            mapY = 0.72f,
        ),

        // --- Tennessee (1) ---
        CiSuperstore(
            id = "ci_east_ridge_tn",
            name = "CI Superstore — East Ridge (Chattanooga)",
            city = "East Ridge",
            state = "TN",
            address = "495 Camp Jordan Pkwy, East Ridge, TN 37412",
            highlight = "Chattanooga area destination lounge near Camp Jordan with full bar & patio.",
            latitude = 34.996,
            longitude = -85.207,
            mapX = 0.65f,
            mapY = 0.56f,
        ),

        // --- Kentucky (1) ---
        CiSuperstore(
            id = "ci_newport_ky",
            name = "CI Superstore — Newport (On the Levee)",
            city = "Newport",
            state = "KY",
            address = "One Levee Way, Suite P-106, Newport, KY 41071",
            highlight = "Riverfront lounge overlooking Cincinnati skyline with bourbon bar & full humidor.",
            latitude = 39.094,
            longitude = -84.496,
            mapX = 0.65f,
            mapY = 0.43f,
        ),
    )

    val availableStates: List<String> = listOf("PA", "TX", "FL", "TN", "KY")

    val alertTopics: List<String> = listOf(
        "🎉 Store Events & Tastings",
        "🔥 Weekly Cigar Specials",
        "🏆 Rare & Limited Releases",
        "🥃 Pairing Nights & Lounges",
    )

    fun googleMapsDirectionsUrl(store: CiSuperstore): String {
        val query = Uri.encode("Cigars International ${store.address}")
        return "https://www.google.com/maps/search/?api=1&query=$query"
    }

    fun googleStoreReviewUrl(store: CiSuperstore): String {
        val query = Uri.encode("Cigars International Superstore ${store.city} ${store.state} Google reviews")
        return "https://www.google.com/search?q=$query"
    }
}
