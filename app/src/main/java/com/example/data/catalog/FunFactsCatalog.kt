package com.example.data.catalog

import com.example.model.Beverage
import com.example.model.BeverageCategory
import com.example.model.Cigar
import com.example.model.FunFact
import com.example.model.FunFactTopic
import com.example.model.WrapperShade

object FunFactsCatalog {

    val allFunFacts: List<FunFact> = listOf(
        // --- Cigar Fun Facts ---
        FunFact(
            id = "fact_jfk_upmann",
            topic = FunFactTopic.CIGAR,
            emoji = "🏛️",
            headline = "JFK's Midnight 1,200-Cigar Stockpile",
            body = "The night before signing the February 1962 Cuban trade embargo, President John F. Kennedy asked Press Secretary Pierre Salinger to hunt down 1,200 Cuban H. Upmann Petit Upmanns across Washington, D.C. — and didn't sign the order until they were safely in his desk!",
            tag = "H. Upmann · History",
        ),
        FunFact(
            id = "fact_200_hands",
            topic = FunFactTopic.CIGAR,
            emoji = "👐",
            headline = "200+ Pairs of Hands Per Cigar",
            body = "From planting microscopic tobacco seeds (300,000 seeds fit in a single ounce!) to harvesting, barn curing, fermentation, sorting, rolling, and boxing, a single handmade premium cigar passes through more than 200 pairs of skilled human hands.",
            tag = "Craftsmanship",
        ),
        FunFact(
            id = "fact_avo_sinatra",
            topic = FunFactTopic.CIGAR,
            emoji = "🎹",
            headline = "The Cigar Blender Who Wrote 'Strangers in the Night'",
            body = "Before founding AVO Cigars in the Dominican Republic, jazz pianist Avo Uvezian composed the famous melody that became Frank Sinatra's #1 hit 'Strangers in the Night' — and handed out his first hand-rolled cigars to guests at his piano bar in Puerto Rico.",
            tag = "AVO · Music Lore",
        ),
        FunFact(
            id = "fact_toscano_rainstorm",
            topic = FunFactTopic.CIGAR,
            emoji = "⛈️",
            headline = "Italy's Iconic Toscano Was Born in a Rainstorm",
            body = "In August 1815 in Florence, a sudden summer downpour soaked a shipment of Kentucky tobacco left drying in a courtyard. Rather than throw it away, the factory fermented and rolled the wet leaves — accidentally inventing the world-famous fire-cured Toscano cheroot!",
            tag = "Toscano · Happy Accident",
        ),
        FunFact(
            id = "fact_maduro_process",
            topic = FunFactTopic.CIGAR,
            emoji = "🍫",
            headline = "Maduro Isn't a Tobacco Seed — It's Caramelized Sugar",
            body = "'Maduro' means 'ripe' in Spanish. Those dark, oily wrappers get their espresso and dark-chocolate sweetness from thick upper-priming leaves fermented longer at higher temperatures, naturally caramelizing starches inside the leaf without any added flavoring.",
            tag = "Wrapper Science",
        ),
        FunFact(
            id = "fact_opusx_impossible",
            topic = FunFactTopic.CIGAR,
            emoji = "🌱",
            headline = "The 'Impossible' Dominican Wrapper",
            body = "For decades, tobacco experts insisted Dominican soil could never grow a rich, sun-grown wrapper leaf. Carlito Fuente defied everyone at Chateau de la Fuente in the early 1990s, creating Fuente Fuente OpusX — the first Dominican puro in history.",
            tag = "Arturo Fuente · OpusX",
        ),
        FunFact(
            id = "fact_churchill_vitola",
            topic = FunFactTopic.CIGAR,
            emoji = "🎩",
            headline = "How the 7\" × 47 Churchill Got Its Name",
            body = "Sir Winston Churchill smoked an estimated 250,000 cigars in his lifetime! After his 1946 visit to the Romeo y Julieta factory in Havana, the house renamed its classic Julieta No. 2 (7 inches × 47 ring gauge) the 'Churchill' in his honor.",
            tag = "Romeo y Julieta",
        ),
        FunFact(
            id = "fact_cohiba_diplomat",
            topic = FunFactTopic.CIGAR,
            emoji = "🇨🇺",
            headline = "Cohiba Started as a Top-Secret Diplomatic Gift",
            body = "Created in 1966 at Havana's ornate El Laguito mansion, Cohiba cigars weren't even sold to the public for their first 16 years — they were exclusively gifted to visiting heads of state and foreign diplomats until 1982.",
            tag = "Cohiba · El Laguito",
        ),
        FunFact(
            id = "fact_connecticut_tents",
            topic = FunFactTopic.CIGAR,
            emoji = "⛺",
            headline = "Giant Cheesecloth Clouds Over Connecticut",
            body = "Silky Connecticut Shade wrappers grow under acres of stretched white cheesecloth tents that mimic tropical cloud cover. Filtering direct sunlight keeps the leaves delicate, golden, vein-free, and naturally creamy.",
            tag = "Connecticut Shade",
        ),
        FunFact(
            id = "fact_cigar_ash_insulator",
            topic = FunFactTopic.CIGAR,
            emoji = "🌡️",
            headline = "Long Ash Keeps Your Cigar Cooler",
            body = "Resist tapping your ash every few puffs! Letting an inch of firm white ash build up acts as a natural thermal insulator that slows airflow, lowers burn temperature, and preserves sweet, nuanced wrapper oils.",
            tag = "Smoking Tip",
        ),
        FunFact(
            id = "fact_torcedor_lectores",
            topic = FunFactTopic.CIGAR,
            emoji = "📖",
            headline = "Why Montecristo & Romeo y Julieta Are Named After Books",
            body = "Since 1865, Cuban rolling rooms have employed 'lectores' (readers) who read newspapers and classic novels aloud while torcedores roll cigars. Rollers loved Alexandre Dumas's 'The Count of Monte Cristo' and Shakespeare's 'Romeo and Juliet' so much that brands were named after them!",
            tag = "Cuban Tradition",
        ),
        FunFact(
            id = "fact_padron_box_press",
            topic = FunFactTopic.CIGAR,
            emoji = "📦",
            headline = "Box-Pressing Started as a Shipping Trick",
            body = "Classic Cuban cigars got softly squared simply from being packed tightly inside cedar dress boxes. Padrón elevated the technique in 1994 with the 1964 Anniversary Series, popularizing the crisp modern trunk-press that cools smoke and feels effortless in hand.",
            tag = "Padrón · Design",
        ),
        FunFact(
            id = "fact_ring_gauge_math",
            topic = FunFactTopic.CIGAR,
            emoji = "📏",
            headline = "What a '50 Ring Gauge' Actually Measures",
            body = "Cigar ring gauge is measured in 64ths of an inch! A 64-ring cigar is exactly 1 full inch wide, while a classic 50-ring Robusto is 50/64ths (0.78 inches) in diameter. Thinner lancero cigars deliver a higher wrapper-to-filler ratio and punchier flavor!",
            tag = "Vitola Math",
        ),
        FunFact(
            id = "fact_nicaragua_volcanoes",
            topic = FunFactTopic.CIGAR,
            emoji = "🌋",
            headline = "Volcanic Ash Gives Nicaraguan Leaf Its Spice",
            body = "Estelí, Condega, Jalapa, and Ometepe Island sit beside active volcanoes whose mineral-rich black ash infuses Nicaraguan tobaccos with signature black pepper, dark cocoa, and espresso depth.",
            tag = "Nicaragua Terroir",
        ),
        FunFact(
            id = "fact_spanish_cedar",
            topic = FunFactTopic.CIGAR,
            emoji = "🪵",
            headline = "Spanish Cedar Isn't Spanish — Or Even a Cedar!",
            body = "The aromatic wood lining every great humidor (Cedrela odorata) actually comes from South and Central America and belongs to the mahogany family. Its natural oils stabilize humidity, repel tobacco beetles, and gently marry cigar aromas over years of aging.",
            tag = "Humidor Science",
        ),
        FunFact(
            id = "fact_cigar_beetle_freeze",
            topic = FunFactTopic.CIGAR,
            emoji = "❄️",
            headline = "Premium Cigars Take a Polar Plunge Before Shipping",
            body = "To protect humidors worldwide from tiny tobacco beetles (Lasioderma serricorne), major factories flash-freeze finished cigars below 0°F for several days and slowly thaw them before export — keeping leaves pristine without chemicals.",
            tag = "Factory Secret",
        ),
        FunFact(
            id = "fact_plume_vs_mold",
            topic = FunFactTopic.CIGAR,
            emoji = "🔬",
            headline = "The Great 'Plume' Debate",
            body = "For decades, dusty white spots on aged cigars were praised as crystallized oils called 'plume' or 'bloom.' Modern lab microscopy studies found most white growth is actually harmless surface mold — so keep your humidor around 65–69% RH and 65–70°F!",
            tag = "Storage Wisdom",
        ),
        FunFact(
            id = "fact_cao_amazon_basin",
            topic = FunFactTopic.CIGAR,
            emoji = "🛶",
            headline = "Tobacco Harvested Once Every 3 Years by Canoe",
            body = "CAO Amazon Basin uses wild Bragança tobacco grown deep in the Brazilian Amazon rainforest. Harvested only once every three years, the leaves are rolled into tight tubes called 'carottes,' aged six months in the jungle, and hauled out by canoe!",
            tag = "CAO · Rare Leaf",
        ),
        FunFact(
            id = "fact_ci_hamburg_size",
            topic = FunFactTopic.CIGAR,
            emoji = "🏪",
            headline = "America's Largest Freestanding Cigar Superstore",
            body = "Cigars International's Hamburg, Pennsylvania Superstore spans over 22,000 square feet with a massive walk-in humidor housing more than 1,200 cigar brands, a full lounge bar, and room for hundreds of aficionados.",
            tag = "Cigars International",
        ),

        // --- Drink Fun Facts ---
        FunFact(
            id = "fact_bourbon_new_oak",
            topic = FunFactTopic.DRINK,
            emoji = "🥃",
            headline = "Why Scotch Distilleries Love American Bourbon Laws",
            body = "By U.S. law, straight bourbon MUST age in brand-new charred American white oak barrels — they can only be used once! That law supplies millions of freshly emptied bourbon barrels to Scotland, Ireland, and Caribbean rum houses to age their finest spirits.",
            tag = "Bourbon & Scotch",
        ),
        FunFact(
            id = "fact_islay_peat",
            topic = FunFactTopic.DRINK,
            emoji = "🔥",
            headline = "Islay's Smoke Comes From 10,000-Year-Old Moss",
            body = "Single malts like Lagavulin, Laphroaig, and Ardbeg get their campfire and sea-spray aroma by drying malted barley over burning peat cut from Scottish bogs — compressed heather, moss, and coastal kelp formed over ten millennia.",
            tag = "Islay Single Malt",
        ),
        FunFact(
            id = "fact_angels_share",
            topic = FunFactTopic.DRINK,
            emoji = "👼",
            headline = "The 'Angel's Share' Evaporates Faster in the Tropics",
            body = "Whisky aged in cool Scotland loses about 2% per year through porous oak staves ('the angel's share'). Aged rum in hot Barbados, Panama, or Guatemala can lose up to 8–10% per year — concentrating caramel and oak flavors three times faster!",
            tag = "Barrel Aging",
        ),
        FunFact(
            id = "fact_port_atlantic",
            topic = FunFactTopic.DRINK,
            emoji = "⛵",
            headline = "Port Wine Was Invented to Survive Ocean Storms",
            body = "In the 1700s, British merchants shipping red wine home from Portugal's Douro Valley noticed casks spoiled at sea. Adding neutral grape brandy halted fermentation early — preserving luscious natural grape sweetness and creating the ultimate Maduro cigar companion!",
            tag = "Port Wine",
        ),
        FunFact(
            id = "fact_cognac_french_oak",
            topic = FunFactTopic.DRINK,
            emoji = "🥂",
            headline = "Cognac Uses Wide-Grain Limousin Forest Oak",
            body = "True Cognac can only come from six designated crus in southwest France, double-distilled in onion-shaped copper alembic stills and aged in porous French oak from Limousin or Tronçais forests that imparts silky apricot, vanilla, and rancio notes.",
            tag = "Cognac Craft",
        ),
        FunFact(
            id = "fact_cuban_espumita",
            topic = FunFactTopic.DRINK,
            emoji = "☕",
            headline = "The Secret to Cuban Cafecito Is 'Espumita'",
            body = "In Miami and Havana cigar lounges, baristas catch the very first dark drops from a stovetop moka pot and vigorously whip them with raw cane sugar into a thick golden paste — creating the glossy caramelized 'espumita' foam on top of every cafecito.",
            tag = "Coffee Ritual",
        ),
        FunFact(
            id = "fact_espresso_pressure",
            topic = FunFactTopic.DRINK,
            emoji = "⚡",
            headline = "Espresso Has Less Caffeine Than Drip Coffee",
            body = "'Espresso' means 'pressed out' in Italian — forced at 9 bars of pressure in 25 seconds. Because it's a tiny 1-oz shot, a single espresso actually has ~63 mg of caffeine compared to ~120+ mg in a standard mug of drip coffee, yet packs triple the silky body!",
            tag = "Espresso Science",
        ),
        FunFact(
            id = "fact_mezcal_earth_pit",
            topic = FunFactTopic.DRINK,
            emoji = "🌵",
            headline = "Mezcal Agave Hearts Slow-Roast Underground",
            body = "Unlike most tequila steamed in above-ground ovens, artisanal mezcal roasts giant agave piñas for 3–5 days inside conical earthen pits lined with hot volcanic stones and wood embers — creating smoky mineral magic beside Nicaraguan Corojo cigars.",
            tag = "Agave Spirits",
        ),
        FunFact(
            id = "fact_old_fashioned_name",
            topic = FunFactTopic.DRINK,
            emoji = "🍊",
            headline = "The Old Fashioned Was Literally the Original 'Cocktail'",
            body = "In 1806, 'cocktail' was defined as simply spirits, sugar, water, and bitters. When 1880s bartenders started adding absinthe and liqueurs, purists began ordering whiskey made 'the old-fashioned way' — giving cigar lounges their #1 classic cocktail!",
            tag = "Cocktail History",
        ),
        FunFact(
            id = "fact_oolong_charcoal",
            topic = FunFactTopic.DRINK,
            emoji = "🍵",
            headline = "Charcoal-Roasted Oolong Bridges Tea & Tobacco",
            body = "Taiwanese and Fujian oolong teas sit halfway between green and black tea and are slowly baked over glowing longan charcoal — developing toasted hazelnut, orchid, and cedar flavors that mirror Connecticut Shade and Cameroon wrappers.",
            tag = "Artisanal Tea",
        ),
        FunFact(
            id = "fact_stout_roast",
            topic = FunFactTopic.DRINK,
            emoji = "🍺",
            headline = "Imperial Stouts Were Brewed for Russian Tsars",
            body = "18th-century London brewers boosted alcohol and roasted black barley in porters so shipments to Empress Catherine the Great wouldn't freeze on Baltic ships. That same roasted-malt dark-chocolate core now pairs seamlessly with Oscuro and Maduro cigars.",
            tag = "Craft Beer",
        ),
        FunFact(
            id = "fact_japanese_whisky_mizunara",
            topic = FunFactTopic.DRINK,
            emoji = "🇯🇵",
            headline = "Rare Mizunara Oak Trees Must Grow 200 Years",
            body = "Japanese whisky makers prize native Mizunara oak casks for their sandalwood, coconut, and temple-incense notes — yet the trees must grow at least 200 years before coopers can craft a single barrel, and the wood is famously tricky and porous!",
            tag = "Japanese Whisky",
        ),

        // --- Pairing Lore Fun Facts ---
        FunFact(
            id = "fact_sugar_nicotine",
            topic = FunFactTopic.PAIRING,
            emoji = "🍯",
            headline = "Why Sweet Drinks Rescue a Strong Cigar Session",
            body = "Nicotine gently dips blood sugar during a hearty full-bodied smoke. Sipping a touch of natural sweetness — like tawny port, aged rum, root beer, cream soda, or dark chocolate — keeps your palate steady and turns peppery spice into velvet!",
            tag = "Sommelier Science",
        ),
        FunFact(
            id = "fact_ice_mutes_aroma",
            topic = FunFactTopic.PAIRING,
            emoji = "🧊",
            headline = "One Drop of Water Unlocks Whisky Aroma, Too Much Ice Hides It",
            body = "Ice-cold pours numb delicate taste buds and lock aromatic esters inside the glass. Adding just 2–3 drops of cool spring water to neat bourbon or Scotch releases guaiacol flavor molecules that harmonize with cedar and toasted almond smoke.",
            tag = "Tasting Hack",
        ),
        FunFact(
            id = "fact_retrohale_magic",
            topic = FunFactTopic.PAIRING,
            emoji = "👃",
            headline = "80% of Cigar & Spirit Flavor Happens in Your Nose",
            body = "Your tongue only senses sweet, salty, sour, bitter, and umami. Gently exhaling the last 10% of a puff through your nose ('retrohaling') wakes up olfactory receptors that unlock baking spice, floral honey, and roasted hazelnut you'd otherwise miss!",
            tag = "Palate Technique",
        ),
        FunFact(
            id = "fact_bubbles_reset",
            topic = FunFactTopic.PAIRING,
            emoji = "🫧",
            headline = "Carbonation Acts Like a Palate Windshield Wiper",
            body = "Rich cigar oils gradually coat the tongue by the final third. Crisp sparkling water, fiery ginger beer, Champagne, or Belgian tripel uses carbonic acid bubbles to lift heavy oils off your palate so every next puff tastes bright and fresh!",
            tag = "Palate Reset",
        ),
    )

    fun funFactForCigar(cigar: Cigar): FunFact {
        val text = "${cigar.brand} ${cigar.name} ${cigar.wrapper} ${cigar.origin}".lowercase()
        val byId = allFunFacts.associateBy { it.id }
        return when {
            text.contains("upmann") -> byId["fact_jfk_upmann"]!!
            text.contains("avo") -> byId["fact_avo_sinatra"]!!
            text.contains("toscano") -> byId["fact_toscano_rainstorm"]!!
            text.contains("opusx") || text.contains("fuente") -> byId["fact_opusx_impossible"]!!
            text.contains("churchill") || text.contains("romeo y julieta") -> byId["fact_churchill_vitola"]!!
            text.contains("cohiba") -> byId["fact_cohiba_diplomat"]!!
            text.contains("montecristo") -> byId["fact_torcedor_lectores"]!!
            text.contains("padrón") || text.contains("padron") -> byId["fact_padron_box_press"]!!
            text.contains("amazon") || text.contains("cao") -> byId["fact_cao_amazon_basin"]!!
            text.contains("connecticut") || cigar.wrapperShade == WrapperShade.CLARO -> byId["fact_connecticut_tents"]!!
            cigar.wrapperShade == WrapperShade.MADURO || cigar.wrapperShade == WrapperShade.OSCURO -> byId["fact_maduro_process"]!!
            text.contains("nicaragua") -> byId["fact_nicaragua_volcanoes"]!!
            else -> {
                val cigarFacts = allFunFacts.filter { it.topic == FunFactTopic.CIGAR || it.topic == FunFactTopic.PAIRING }
                cigarFacts[kotlin.math.abs(cigar.id.hashCode()) % cigarFacts.size]
            }
        }
    }

    fun funFactForBeverage(beverage: Beverage): FunFact {
        val text = "${beverage.name} ${beverage.subtitle}".lowercase()
        val byId = allFunFacts.associateBy { it.id }
        return when {
            text.contains("islay") || text.contains("peat") || text.contains("lagavulin") || text.contains("laphroaig") -> byId["fact_islay_peat"]!!
            text.contains("japanese") || text.contains("hibiki") || text.contains("yamazaki") -> byId["fact_japanese_whisky_mizunara"]!!
            text.contains("bourbon") || text.contains("rye") -> byId["fact_bourbon_new_oak"]!!
            text.contains("port") -> byId["fact_port_atlantic"]!!
            text.contains("cognac") || text.contains("armagnac") -> byId["fact_cognac_french_oak"]!!
            text.contains("cafecito") || text.contains("cuban") -> byId["fact_cuban_espumita"]!!
            text.contains("espresso") || text.contains("latte") || text.contains("coffee") -> byId["fact_espresso_pressure"]!!
            text.contains("mezcal") || text.contains("tequila") -> byId["fact_mezcal_earth_pit"]!!
            text.contains("old fashioned") -> byId["fact_old_fashioned_name"]!!
            text.contains("oolong") || text.contains("tea") -> byId["fact_oolong_charcoal"]!!
            text.contains("stout") || text.contains("porter") -> byId["fact_stout_roast"]!!
            beverage.category == BeverageCategory.RUM -> byId["fact_angels_share"]!!
            beverage.category == BeverageCategory.CRAFT_SODA -> byId["fact_bubbles_reset"]!!
            else -> {
                val drinkFacts = allFunFacts.filter { it.topic == FunFactTopic.DRINK || it.topic == FunFactTopic.PAIRING }
                drinkFacts[kotlin.math.abs(beverage.id.hashCode()) % drinkFacts.size]
            }
        }
    }
}
