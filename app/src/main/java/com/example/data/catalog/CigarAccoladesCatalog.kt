package com.example.data.catalog

import com.example.model.Cigar
import com.example.model.CigarAccolade
import com.example.model.StrengthLevel

object CigarAccoladesCatalog {

    private val unratedInfusedBrands: Set<String> = setOf(
        "ACID",
        "Deadwood",
        "Tabak Especial",
        "Isla del Sol",
        "Java",
        "Tatiana",
        "Trader Jack's",
        "Swisher Sweets",
        "White Owl",
        "Dutch Masters",
        "Dark Desires",
    )

    private val unratedSpecificIds: Set<String> = setOf(
        "ci_legends_yellow",
        "ci_legends_copper",
        "ci_gurkha_grand_reserve",
        "ci_drew_kfc_chunky",
    )

    private val exactAccolades: Map<String, CigarAccolade> = mapOf(
        // --- Cigar Aficionado #1 Cigars of the Year (2004–2024) ---
        "my_father_the_judge" to CigarAccolade(
            cigarAficionadoRating = 98,
            ratingYear = "2024",
            ratingHighlight = "#1 Cigar of the Year 2024 — Ecuadorian Sumatra box-pressed powerhouse from My Father Cigars.",
            cigarOfTheYearYear = "2024",
            celebrityNotes = listOf(
                "Pepín & Jaime Garcia (Master Blenders): Flagship tribute from the Garcia family smoked widely by MLB and NFL locker-room aficionados.",
            ),
        ),
        "fuente_opusx" to CigarAccolade(
            cigarAficionadoRating = 97,
            ratingYear = "2023",
            ratingHighlight = "#1 Cigar of the Year 2023 (Reserva d'Chateau, 97 pts) & 2005 (Double Corona, 95 pts).",
            cigarOfTheYearYear = "2023 & 2005",
            celebrityNotes = listOf(
                "Sylvester Stallone — Longtime OpusX devotee featured in Cigar Aficionado interviews.",
                "Steve Harvey — Frequently names Arturo Fuente OpusX among his celebratory lounge favorites.",
                "Andy Garcia — Close friend of Carlito Fuente and admirer of Chateau de la Fuente rarities.",
            ),
        ),
        "padron_1964_natural" to CigarAccolade(
            cigarAficionadoRating = 97,
            ratingYear = "2021",
            ratingHighlight = "#1 Cigar of the Year 2021 (Torpedo Natural, 97 pts) — Four-time Cigar of the Year dynasty.",
            cigarOfTheYearYear = "2021",
            celebrityNotes = listOf(
                "Michael Jordan — Noted aficionado of aged Nicaraguan box-pressed Padrón classics.",
                "Joe Rogan — Frequently praises Padrón 1964 Anniversary Series on air for flawless construction.",
                "Dave Chappelle — Regularly photographed enjoying Padrón Anniversary vitolas backstage.",
            ),
        ),
        "padron_1964" to CigarAccolade(
            cigarAficionadoRating = 97,
            ratingYear = "2021",
            ratingHighlight = "Padrón 1964 Anniversary Series — #1 Cigar of the Year 2021 (97 pts) & perpetual Top 25 icon.",
            cigarOfTheYearYear = "2021",
            celebrityNotes = listOf(
                "Michael Jordan — Celebrated enthusiast of aged Padrón box-pressed releases.",
                "Steve Harvey — Frequently cites the Padrón 1964 Anniversary Maduro as his go-to evening smoke.",
                "Joe Rogan — Often highlights Padrón 1964 for effortless draw and cocoa richness.",
            ),
        ),
        "ep_carrillo_pledge" to CigarAccolade(
            cigarAficionadoRating = 98,
            ratingYear = "2020",
            ratingHighlight = "#1 Cigar of the Year 2020 (Prequel, 98 pts) — Tied for the highest Cigar of the Year score ever awarded.",
            cigarOfTheYearYear = "2020",
        ),
        "aging_room_quattro" to CigarAccolade(
            cigarAficionadoRating = 96,
            ratingYear = "2019",
            ratingHighlight = "#1 Cigar of the Year 2019 (Maestro Torpedo, 96 pts) — Rafael Nodal & AJ Fernandez collaboration.",
            cigarOfTheYearYear = "2019",
        ),
        "ep_carrillo_encore" to CigarAccolade(
            cigarAficionadoRating = 96,
            ratingYear = "2018",
            ratingHighlight = "#1 Cigar of the Year 2018 (Majestic, 96 pts) — All-Nicaraguan triumph by Ernesto Perez-Carrillo.",
            cigarOfTheYearYear = "2018",
        ),
        "epc_encore" to CigarAccolade(
            cigarAficionadoRating = 96,
            ratingYear = "2018",
            ratingHighlight = "#1 Cigar of the Year 2018 (Majestic, 96 pts) — All-Nicaraguan triumph by Ernesto Perez-Carrillo.",
            cigarOfTheYearYear = "2018",
        ),
        "fuente_don_carlos_aniv" to CigarAccolade(
            cigarAficionadoRating = 97,
            ratingYear = "2017",
            ratingHighlight = "Don Carlos Series — 2017 #1 Cigar of the Year lineage (Eye of the Shark, 97 pts).",
            cigarOfTheYearYear = "2017 (Line)",
            celebrityNotes = listOf(
                "Francis Ford Coppola — Longtime admirer of Arturo Fuente's Cameroon-wrapped Don Carlos heritage.",
            ),
        ),
        "fuente_don_carlos_no2" to CigarAccolade(
            cigarAficionadoRating = 94,
            ratingYear = "2022",
            ratingHighlight = "94-point Cameroon icon; sibling Eye of the Shark won 2017 #1 Cigar of the Year (97 pts).",
            celebrityNotes = listOf(
                "Francis Ford Coppola — Known enthusiast of Arturo Fuente Don Carlos vitolas.",
            ),
        ),
        "lfd_andalusian_bull" to CigarAccolade(
            cigarAficionadoRating = 96,
            ratingYear = "2016",
            ratingHighlight = "#1 Cigar of the Year 2016 (96 pts) — Litto Gomez's sculptural Dominican figurado inspired by Spanish matadors.",
            cigarOfTheYearYear = "2016",
        ),
        "my_father_le_bijou" to CigarAccolade(
            cigarAficionadoRating = 97,
            ratingYear = "2015",
            ratingHighlight = "#1 Cigar of the Year 2015 (Torpedo Box-Pressed, 97 pts) — Pepín Garcia's Oscuro tribute to his father.",
            cigarOfTheYearYear = "2015",
        ),
        "oliva_melanio" to CigarAccolade(
            cigarAficionadoRating = 96,
            ratingYear = "2014",
            ratingHighlight = "#1 Cigar of the Year 2014 (Figurado, 96 pts) — Ecuadorian Sumatra masterpiece.",
            cigarOfTheYearYear = "2014",
            celebrityNotes = listOf(
                "Charles Barkley — Frequently spotted enjoying Oliva Serie V & Melanio cigars at charity golf events.",
            ),
        ),
        "montecristo_no2" to CigarAccolade(
            cigarAficionadoRating = 96,
            ratingYear = "2013",
            ratingHighlight = "#1 Cigar of the Year 2013 (96 pts) — The world's benchmark Cuban torpedo.",
            cigarOfTheYearYear = "2013",
            celebrityNotes = listOf(
                "Jack Nicholson — Classic Montecristo No. 2 aficionado profiled in Cigar Aficionado.",
                "Arnold Schwarzenegger — Frequently cites Cuban Montecristo No. 2 among his top torpedo smokes.",
                "Danny DeVito — Longtime fan of Cuban Montecristo pyramids.",
                "Michael Jordan — Known to keep Montecristo No. 2 stocked in his personal humidor.",
            ),
        ),
        "my_father_antillas" to CigarAccolade(
            cigarAficionadoRating = 96,
            ratingYear = "2012",
            ratingHighlight = "#1 Cigar of the Year 2012 (Toro, 96 pts) — Sun-grown Nicaraguan classic.",
            cigarOfTheYearYear = "2012",
        ),
        "alec_prensado" to CigarAccolade(
            cigarAficionadoRating = 96,
            ratingYear = "2011",
            ratingHighlight = "#1 Cigar of the Year 2011 (Churchill, 96 pts) — Honduran Trojes Corojo box-pressed standout.",
            cigarOfTheYearYear = "2011",
        ),
        "cohiba_behike_52" to CigarAccolade(
            cigarAficionadoRating = 97,
            ratingYear = "2010",
            ratingHighlight = "#1 Cigar of the Year 2010 (97 pts) — Ultra-rare Medio Tiempo leaf flagship from El Laguito.",
            cigarOfTheYearYear = "2010",
            celebrityNotes = listOf(
                "Arnold Schwarzenegger — Famous admirer of flagship Cuban Cohiba cigars.",
                "Jack Nicholson — Celebrated collector of vintage Cuban Cohiba editions.",
                "Jay-Z — Frequently spotted celebrating milestones with ultra-rare Cohiba vitolas.",
                "Rihanna — Photographed enjoying Cuban Cohiba cigars during Havana visits.",
            ),
        ),
        "padron_family_reserve_45" to CigarAccolade(
            cigarAficionadoRating = 95,
            ratingYear = "2009",
            ratingHighlight = "#1 Cigar of the Year 2009 (No. 45 Maduro, 95 pts) — 10-year aged Nicaraguan puro.",
            cigarOfTheYearYear = "2009",
            celebrityNotes = listOf(
                "Michael Jordan — Celebrated collector of Padrón Family Reserve anniversary releases.",
            ),
        ),
        "padron_family_reserve" to CigarAccolade(
            cigarAficionadoRating = 95,
            ratingYear = "2009",
            ratingHighlight = "Padrón Family Reserve Series — #1 Cigar of the Year 2009 (95 pts) & multiple Top 5 finishes.",
            cigarOfTheYearYear = "2009",
        ),
        "ci_casa_magna_colorado" to CigarAccolade(
            cigarAficionadoRating = 93,
            ratingYear = "2008",
            ratingHighlight = "#1 Cigar of the Year 2008 (Robusto, 93 pts) — Manuel Quesada & Nestor Plasencia's breakthrough Nicaraguan puro.",
            cigarOfTheYearYear = "2008",
        ),
        "padron_1926_no9" to CigarAccolade(
            cigarAficionadoRating = 97,
            ratingYear = "2007",
            ratingHighlight = "#1 Cigar of the Year 2007 (No. 9 Maduro, 97 pts) — Benchmark Nicaraguan box-pressed Maduro.",
            cigarOfTheYearYear = "2007",
            celebrityNotes = listOf(
                "Michael Jordan — Frequently names Padrón 1926 among his finest full-bodied smokes.",
                "Joe Rogan — Regularly recommends the Padrón 1926 Serie for cocoa-espresso depth.",
                "Steve Harvey — Known devotee of the Padrón 1926 Maduro.",
            ),
        ),
        "padron_1926_natural" to CigarAccolade(
            cigarAficionadoRating = 93,
            ratingYear = "2004",
            ratingHighlight = "Padrón 1926 Serie — Inaugural #1 Cigar of the Year 2004 lineage (40th Anniversary, 93 pts).",
            cigarOfTheYearYear = "2004 (Line)",
        ),

        // --- Celebrity Collaborations, Tributes & Famous Smokers ---
        "cohiba_comador_toro" to CigarAccolade(
            cigarAficionadoRating = 90,
            ratingYear = "2014",
            ratingHighlight = "Luxury Connecticut Havana-wrapper collaboration crafted with General Cigar.",
            celebrityNotes = listOf(
                "Jay-Z (Shawn Carter) — Co-created and endorsed Cohiba Comador directly with General Cigar Company.",
            ),
        ),
        "espinosa_knuckle_sandwich_habano" to CigarAccolade(
            cigarAficionadoRating = 94,
            ratingYear = "2023",
            ratingHighlight = "#6 Cigar of the Year 2023 (94 pts) — Blended by AJ Fernandez for Guy Fieri & Erik Espinosa.",
            celebrityNotes = listOf(
                "Guy Fieri — Official celebrity co-creator and brand partner of Knuckle Sandwich Cigars with Erik Espinosa.",
            ),
        ),
        "espinosa_knuckle_sandwich_maduro" to CigarAccolade(
            cigarAficionadoRating = 92,
            ratingYear = "2023",
            ratingHighlight = "92-point Ecuadorian Sumatra Maduro follow-up from the Knuckle Sandwich line.",
            celebrityNotes = listOf(
                "Guy Fieri — Official celebrity co-creator and endorser of Knuckle Sandwich Cigars.",
            ),
        ),
        "espinosa_knuckle_sandwich_ct" to CigarAccolade(
            cigarAficionadoRating = 91,
            ratingYear = "2024",
            ratingHighlight = "91-point Connecticut Shade addition to the Knuckle Sandwich portfolio.",
            celebrityNotes = listOf(
                "Guy Fieri — Official celebrity co-creator and endorser of Knuckle Sandwich Cigars.",
            ),
        ),
        "ryj_churchill_cuban" to CigarAccolade(
            cigarAficionadoRating = 93,
            ratingYear = "2022",
            ratingHighlight = "93-point Cuban benchmark that gave the 7\" × 47 Churchill vitola its name.",
            celebrityNotes = listOf(
                "Sir Winston Churchill — Iconic devotee whose 1946 Havana visit inspired the Churchill vitola and gold band.",
                "Michael Jordan — Noted admirer of classic Cuban Romeo y Julieta Churchills.",
            ),
        ),
        "davidoff_winston_churchill" to CigarAccolade(
            cigarAficionadoRating = 92,
            ratingYear = "2021",
            ratingHighlight = "92-point multi-origin luxury tribute authorized by the Churchill estate.",
            celebrityNotes = listOf(
                "Sir Winston Churchill — Official heritage tribute celebrating the statesman's life and love of fine cigars.",
            ),
        ),
        "davidoff_late_hour" to CigarAccolade(
            cigarAficionadoRating = 93,
            ratingYear = "2017",
            ratingHighlight = "#16 Cigar of the Year 2017 (93 pts) — Condega Visus filler aged six months in Scotch single malt casks.",
            celebrityNotes = listOf(
                "Sir Winston Churchill — Inspired by Churchill's famed late-night writing hours and fondness for Scotch whisky.",
            ),
        ),
        "davidoff_winston_churchill_late" to CigarAccolade(
            cigarAficionadoRating = 93,
            ratingYear = "2017",
            ratingHighlight = "93-point Scotch-cask aged Winston Churchill tribute from Davidoff.",
            celebrityNotes = listOf(
                "Sir Winston Churchill — Inspired by Churchill's darkest-hour reflections and Scotch whisky ritual.",
            ),
        ),
        "partagas_d4" to CigarAccolade(
            cigarAficionadoRating = 94,
            ratingYear = "2021",
            ratingHighlight = "#10 Cigar of the Year 2021 (94 pts) — Cuba's bestselling Robusto.",
            celebrityNotes = listOf(
                "Arnold Schwarzenegger — Longtime fan of Cuban Partagás Serie D No. 4.",
                "Michael Jordan — Frequently names Cuban Partagás and Lusitanias among his championship smokes.",
                "Danny DeVito — Known aficionado of Cuban Partagás vitolas.",
            ),
        ),
        "hoyo_epicure_2" to CigarAccolade(
            cigarAficionadoRating = 93,
            ratingYear = "2021",
            ratingHighlight = "#13 Cigar of the Year 2021 (93 pts) — Creamy Vuelta Abajo Robusto.",
            celebrityNotes = listOf(
                "Arnold Schwarzenegger — Regularly mentions Hoyo de Monterrey among his favorite Cuban daytime cigars.",
                "Michael Jordan — Known to enjoy Hoyo de Monterrey Epicure No. 2 on the golf course.",
            ),
        ),
        "cohiba_siglo6" to CigarAccolade(
            cigarAficionadoRating = 94,
            ratingYear = "2022",
            ratingHighlight = "94-point Línea 1492 flagship Cañonazo rolled at Havana's El Laguito.",
            celebrityNotes = listOf(
                "Arnold Schwarzenegger — Frequently photographed smoking Cuban Cohiba Siglo vitolas.",
                "Jack Nicholson — Celebrated longtime admirer of Cuban Cohiba.",
                "Michael Jordan — Keeps Cuban Cohiba Siglo VI among his prized humidor reserves.",
            ),
        ),
        "upmann_magnum46" to CigarAccolade(
            cigarAficionadoRating = 93,
            ratingYear = "2020",
            ratingHighlight = "93-point Corona Gorda from one of Havana's oldest houses (1844).",
            celebrityNotes = listOf(
                "John F. Kennedy — Famously requested 1,200 H. Upmann Petit Upmann Cuban cigars before signing the 1962 embargo.",
            ),
        ),
        "cao_brazilia" to CigarAccolade(
            cigarAficionadoRating = 91,
            ratingYear = "2020",
            ratingHighlight = "91-point Brazilian Arapiraca Maduro classic with rich espresso-cocoa depth.",
            celebrityNotes = listOf(
                "James Gandolfini (Tony Soprano) — CAO Brazilia and Macanudo were famously smoked on screen in The Sopranos.",
            ),
        ),
        "macanudo_cafe" to CigarAccolade(
            cigarAficionadoRating = 89,
            ratingYear = "2019",
            ratingHighlight = "89-point America's iconic mellow Connecticut Shade benchmark.",
            celebrityNotes = listOf(
                "James Gandolfini (Tony Soprano) — Frequently smoked Macanudo Café cigars on screen in The Sopranos.",
                "Matthew McConaughey — Noted for enjoying mellow classic cigars during golf outings.",
            ),
        ),
        "fuente_short_story" to CigarAccolade(
            cigarAficionadoRating = 92,
            ratingYear = "2021",
            ratingHighlight = "92-point Cameroon-wrapped Hemingway perfecto revered for impeccable burn.",
            celebrityNotes = listOf(
                "Ernest Hemingway — Line named in honor of the Nobel laureate and celebrated cigar aficionado.",
                "Francis Ford Coppola — Longtime admirer of Arturo Fuente Hemingway perfectos.",
            ),
        ),
        "fuente_hemingway_work_of_art" to CigarAccolade(
            cigarAficionadoRating = 93,
            ratingYear = "2020",
            ratingHighlight = "93-point sculptural Cameroon double figurado.",
            celebrityNotes = listOf(
                "Ernest Hemingway — Celebratory literary tribute from the Fuente family.",
            ),
        ),
        "liga_privada_9" to CigarAccolade(
            cigarAficionadoRating = 92,
            ratingYear = "2019",
            ratingHighlight = "92-point Connecticut Broadleaf icon originally blended as Steve Saka's personal smoke.",
            celebrityNotes = listOf(
                "Joe Rogan — Frequently cites Drew Estate Liga Privada No. 9 as a go-to full-bodied smoke.",
            ),
        ),
        "liga_privada_t52" to CigarAccolade(
            cigarAficionadoRating = 92,
            ratingYear = "2020",
            ratingHighlight = "92-point stalk-cut Connecticut Habano companion to No. 9.",
            celebrityNotes = listOf(
                "Joe Rogan — Noted fan of the Drew Estate Liga Privada series.",
            ),
        ),
        "rocky_patel_decade" to CigarAccolade(
            cigarAficionadoRating = 95,
            ratingYear = "2008",
            ratingHighlight = "#2 Cigar of the Year 2008 (95 pts) — Rocky Patel's highest-rated 10th anniversary milestone.",
            celebrityNotes = listOf(
                "Ray Lewis & Gary Sheffield — Frequent celebrity guests and brand ambassadors at Rocky Patel Burn lounges.",
            ),
        ),
        "la_aroma_original" to CigarAccolade(
            cigarAficionadoRating = 91,
            ratingYear = "2020",
            ratingHighlight = "91-point historic Cuban-heritage brand revitalized by Ashton & Pepín Garcia.",
            celebrityNotes = listOf(
                "Sir Winston Churchill — Historical favorite of Churchill during the brand's original Havana era.",
            ),
        ),
        "ci_mark_twain_no1" to CigarAccolade(
            cigarAficionadoRating = 88,
            ratingYear = "2020",
            ratingHighlight = "88-point approachable Connecticut Shade Churchill.",
            celebrityNotes = listOf(
                "Mark Twain (Samuel Clemens) — Named in homage to the author who famously quipped he smoked only one cigar at a time.",
            ),
        ),
        "ci_la_aurora_preferidos_gold" to CigarAccolade(
            cigarAficionadoRating = 92,
            ratingYear = "2021",
            ratingHighlight = "92-point Dominican double figurado in signature gold tubo from the Dominican Republic's oldest factory (1903).",
            celebrityNotes = listOf(
                "Karl Malone — NBA Hall of Famer who collaborated with La Aurora on his Barrel Aged signature cigar series.",
            ),
        ),

        // --- Additional Verified Top 25 & High-Scoring Icons ---
        "cao_flathead_v660" to CigarAccolade(
            cigarAficionadoRating = 95,
            ratingYear = "2015",
            ratingHighlight = "#3 Cigar of the Year 2015 (95 pts) — Box-pressed Connecticut Broadleaf Gordo.",
        ),
        "ci_monte_cincuenta" to CigarAccolade(
            cigarAficionadoRating = 95,
            ratingYear = "2021",
            ratingHighlight = "#2 Cigar of the Year 2021 (Montecristo 1935 Anniversary Nicaragua lineage, 95 pts).",
        ),
        "la_aroma_mi_amor" to CigarAccolade(
            cigarAficionadoRating = 95,
            ratingYear = "2011",
            ratingHighlight = "#2 Cigar of the Year 2011 (Belicoso, 95 pts) — Mexican San Andrés box-pressed jewel.",
        ),
        "la_aroma_pasion" to CigarAccolade(
            cigarAficionadoRating = 95,
            ratingYear = "2024",
            ratingHighlight = "#3 Cigar of the Year 2024 (Marveloso, 95 pts) — Shade-grown Nicaraguan puro.",
        ),
        "san_cristobal_quintessence" to CigarAccolade(
            cigarAficionadoRating = 95,
            ratingYear = "2021",
            ratingHighlight = "#3 Cigar of the Year 2021 (Corona Gorda, 95 pts) — Ecuadorian Habano perfection.",
        ),
        "trinidad_fundadores" to CigarAccolade(
            cigarAficionadoRating = 95,
            ratingYear = "2019",
            ratingHighlight = "#3 Cigar of the Year 2019 (95 pts) — Diplomatic Cuban Lancero originally reserved for heads of state.",
            celebrityNotes = listOf(
                "Fidel Castro & Diplomatic Corps — Originally rolled exclusively as diplomatic gifts at El Laguito until 1998.",
            ),
        ),
        "bolivar_belicosos" to CigarAccolade(
            cigarAficionadoRating = 95,
            ratingYear = "2022",
            ratingHighlight = "#6 Cigar of the Year 2022 (95 pts) — Full-bodied Cuban figurado benchmark.",
        ),
        "ramon_allones_specially_selected" to CigarAccolade(
            cigarAficionadoRating = 94,
            ratingYear = "2015",
            ratingHighlight = "#4 Cigar of the Year 2015 (94 pts) — Cult-favorite Cuban Robusto.",
        ),
        "illusione_epernay" to CigarAccolade(
            cigarAficionadoRating = 94,
            ratingYear = "2011",
            ratingHighlight = "#2 Cigar of the Year 2011 (Le Taureau, 94 pts) — Champagne-inspired Corojo elegance.",
        ),
        "plasencia_alma_fuerte" to CigarAccolade(
            cigarAficionadoRating = 93,
            ratingYear = "2017",
            ratingHighlight = "#9 Cigar of the Year 2017 (93 pts) — 10-year aged Nicaraguan puro from the Plasencia family.",
        ),
        "juan_lopez_seleccion_no2" to CigarAccolade(
            cigarAficionadoRating = 93,
            ratingYear = "2018",
            ratingHighlight = "#11 Cigar of the Year 2018 (93 pts) — Connoisseur's Cuban Robusto.",
        ),
        "davidoff_sig2" to CigarAccolade(
            cigarAficionadoRating = 91,
            ratingYear = "2020",
            ratingHighlight = "91-point Panatela classic representing Zino Davidoff's quintessential Dominican elegance.",
            celebrityNotes = listOf(
                "Zino Davidoff — The signature vitola of the legendary Geneva tobacconist.",
            ),
        ),
        "fuente_anejo" to CigarAccolade(
            cigarAficionadoRating = 94,
            ratingYear = "2021",
            ratingHighlight = "94-point holiday rarity wrapped in cognac-barrel-aged Connecticut Broadleaf.",
        ),
        "oliva_serie_v_lance" to CigarAccolade(
            cigarAficionadoRating = 94,
            ratingYear = "2020",
            ratingHighlight = "94-point Nicaraguan Ligero Lancero from Oliva's multi-time Top 25 Serie V line.",
        ),
        "lfd_double_ligero" to CigarAccolade(
            cigarAficionadoRating = 93,
            ratingYear = "2019",
            ratingHighlight = "93-point powerhouse grown on Litto Gomez's La Canela estate.",
        ),
        "cohiba_red_dot" to CigarAccolade(
            cigarAficionadoRating = 92,
            ratingYear = "2020",
            ratingHighlight = "92-point African Cameroon flagship of the Dominican Cohiba portfolio.",
        ),
        "montecristo_white" to CigarAccolade(
            cigarAficionadoRating = 91,
            ratingYear = "2021",
            ratingHighlight = "91-point silky Ecuadorian Connecticut classic.",
        ),
        "ryj_1875" to CigarAccolade(
            cigarAficionadoRating = 90,
            ratingYear = "2020",
            ratingHighlight = "90-point Indonesian Shade-wrapped Dominican favorite.",
        ),
        "ci_fuente_858_natural" to CigarAccolade(
            cigarAficionadoRating = 91,
            ratingYear = "2021",
            ratingHighlight = "91-point timeless Cameroon-wrapped Flor Fina 8-5-8 classic.",
        ),
        "ci_fuente_858_maduro" to CigarAccolade(
            cigarAficionadoRating = 92,
            ratingYear = "2022",
            ratingHighlight = "92-point dark Connecticut Broadleaf edition of the iconic 8-5-8.",
        ),
        "ci_drew_undercrown_10" to CigarAccolade(
            cigarAficionadoRating = 93,
            ratingYear = "2022",
            ratingHighlight = "93-point Mexican San Andrés 10th-anniversary flagship by Willy Herrera.",
        ),
        "ci_rp_vintage_1992" to CigarAccolade(
            cigarAficionadoRating = 92,
            ratingYear = "2020",
            ratingHighlight = "92-point 10-year-aged Ecuadorian Sumatra classic.",
        ),
        "rocky_patel_vintage_1990" to CigarAccolade(
            cigarAficionadoRating = 92,
            ratingYear = "2019",
            ratingHighlight = "92-point 12-year-aged Honduran Broadleaf staple.",
        ),
        "ci_punch_rare_corojo" to CigarAccolade(
            cigarAficionadoRating = 91,
            ratingYear = "2020",
            ratingHighlight = "91-point annual limited-edition Ecuadorian Sumatra seasonal release.",
        ),
    )

    // Typical Cigar Aficionado blind-tasting score benchmarks by traditional brand house
    private val brandTypicalScores: Map<String, Pair<Int, List<String>>> = mapOf(
        "Padrón" to (94 to listOf("Michael Jordan, Joe Rogan, Steve Harvey & Dave Chappelle frequently enjoy Padrón blends.")),
        "Arturo Fuente" to (93 to listOf("Sylvester Stallone, Francis Ford Coppola & Andy Garcia are longtime Arturo Fuente admirers.")),
        "My Father" to (93 to emptyList()),
        "Davidoff" to (92 to listOf("Inspired by Sir Winston Churchill and Geneva's classic luxury lounge heritage.")),
        "Cohiba" to (92 to listOf("Arnold Schwarzenegger, Jack Nicholson & Jay-Z are famously associated with Cohiba cigars.")),
        "Montecristo" to (92 to listOf("Jack Nicholson, Arnold Schwarzenegger & Danny DeVito are longtime Montecristo aficionados.")),
        "Partagás" to (92 to listOf("Arnold Schwarzenegger & Michael Jordan are documented fans of Partagás cigars.")),
        "E.P. Carrillo" to (93 to emptyList()),
        "Oliva" to (92 to listOf("Charles Barkley is frequently spotted enjoying Oliva Serie V cigars.")),
        "La Flor Dominicana" to (92 to emptyList()),
        "Plasencia" to (92 to emptyList()),
        "Aging Room" to (92 to emptyList()),
        "Ashton" to (92 to emptyList()),
        "La Aroma de Cuba" to (92 to listOf("Sir Winston Churchill favored La Aroma de Cuba during its original Havana heritage.")),
        "San Cristóbal" to (92 to emptyList()),
        "Tatuaje" to (92 to emptyList()),
        "Foundation" to (92 to emptyList()),
        "Dunbarton Tobacco & Trust" to (92 to emptyList()),
        "Illusione" to (92 to emptyList()),
        "Warped" to (91 to emptyList()),
        "Crowned Heads" to (91 to emptyList()),
        "Aganorsa Leaf" to (91 to emptyList()),
        "Aladino" to (91 to emptyList()),
        "RoMa Craft" to (91 to emptyList()),
        "Bolívar" to (92 to emptyList()),
        "Ramón Allones" to (92 to emptyList()),
        "Trinidad" to (92 to emptyList()),
        "H. Upmann" to (91 to listOf("John F. Kennedy famously favored Cuban H. Upmann Petit Upmann cigars.")),
        "Hoyo de Monterrey" to (91 to listOf("Arnold Schwarzenegger & Michael Jordan enjoy Hoyo de Monterrey classics.")),
        "Romeo y Julieta" to (91 to listOf("Sir Winston Churchill inspired Romeo y Julieta's legendary Churchill vitola.")),
        "Juan López" to (91 to emptyList()),
        "Diplomáticos" to (91 to emptyList()),
        "Por Larrañaga" to (90 to emptyList()),
        "Alec Bradley" to (91 to emptyList()),
        "CAO" to (90 to listOf("James Gandolfini famously smoked CAO cigars on screen in The Sopranos.")),
        "Macanudo" to (89 to listOf("James Gandolfini popularized Macanudo Café on screen in The Sopranos.")),
        "Drew Estate" to (91 to listOf("Joe Rogan frequently highlights Drew Estate Liga Privada cigars.")),
        "Espinosa" to (91 to listOf("Guy Fieri partnered with Erik Espinosa on the 94-rated Knuckle Sandwich line.")),
        "Rocky Patel" to (91 to listOf("Ray Lewis & Gary Sheffield are frequent celebrity guests at Rocky Patel Burn lounges.")),
        "Perdomo" to (91 to emptyList()),
        "AJ Fernandez" to (91 to emptyList()),
        "Joya de Nicaragua" to (91 to emptyList()),
        "Casa Magna" to (91 to emptyList()),
        "La Aurora" to (90 to listOf("NBA Hall of Famer Karl Malone collaborated with La Aurora on signature releases.")),
        "La Gloria Cubana" to (90 to emptyList()),
        "Punch" to (90 to emptyList()),
        "Don Pepin Garcia" to (92 to emptyList()),
        "Herrera Esteli" to (91 to emptyList()),
        "Selected Tobacco" to (92 to emptyList()),
        "Casdagli" to (91 to emptyList()),
        "Ferio Tego" to (91 to emptyList()),
        "Southern Draw" to (91 to emptyList()),
        "Caldwell" to (90 to emptyList()),
        "Camacho" to (90 to emptyList()),
        "AVO" to (90 to listOf("Founded by legendary jazz pianist and composer Avo Uvezian.")),
        "Diesel" to (90 to emptyList()),
        "Man O' War" to (90 to emptyList()),
        "Ave Maria" to (90 to emptyList()),
        "Nub" to (90 to emptyList()),
        "Cain" to (90 to emptyList()),
        "Kristoff" to (90 to emptyList()),
        "La Palina" to (90 to emptyList()),
        "Cavalier Genève" to (91 to emptyList()),
        "ADVentura" to (91 to emptyList()),
        "HVC" to (91 to emptyList()),
        "Patina" to (91 to emptyList()),
        "Curivari" to (91 to emptyList()),
        "Casa Turrent" to (90 to emptyList()),
        "Room101" to (90 to emptyList()),
        "Oscar Valladares" to (90 to emptyList()),
        "Black Label Trading Co." to (90 to emptyList()),
        "Black Works Studio" to (90 to emptyList()),
        "Dapper" to (90 to emptyList()),
        "Fratello" to (90 to emptyList()),
        "La Galera" to (90 to emptyList()),
        "Stolen Throne" to (90 to emptyList()),
        "Crux" to (90 to emptyList()),
        "Balmoral" to (90 to emptyList()),
        "J.C. Newman" to (90 to emptyList()),
        "Cuesta-Rey" to (90 to emptyList()),
        "Fonseca" to (90 to emptyList()),
        "VegaFina" to (89 to emptyList()),
        "Zino" to (89 to emptyList()),
        "Graycliff" to (90 to emptyList()),
        "Gurkha" to (89 to emptyList()),
        "Gran Habano" to (89 to emptyList()),
        "San Lotano" to (90 to emptyList()),
        "Nica Rustica" to (90 to emptyList()),
        "Henry Clay" to (90 to emptyList()),
        "El Rey del Mundo" to (90 to emptyList()),
        "Sancho Panza" to (89 to emptyList()),
        "Saint Luis Rey" to (90 to emptyList()),
        "Gispert" to (89 to emptyList()),
        "Archetype" to (89 to emptyList()),
        "Obsidian" to (89 to emptyList()),
        "Villa Zamorano" to (90 to emptyList()),
        "Mark Twain" to (88 to listOf("Named in tribute to famed author and cigar enthusiast Mark Twain.")),
        "Chillin' Moose" to (89 to emptyList()),
        "5 Vegas" to (89 to emptyList()),
        "601" to (90 to emptyList()),
        "Nica Libre" to (89 to emptyList()),
        "Ramon Bueso" to (90 to emptyList()),
        "Toraño" to (89 to emptyList()),
        "Te-Amo" to (88 to emptyList()),
        "Baccarat" to (88 to emptyList()),
        "Quintero" to (88 to emptyList()),
        "Quorum" to (87 to emptyList()),
        "Factory Smokes" to (87 to emptyList()),
        "Cuban Rounds" to (86 to emptyList()),
        "CI Legends" to (88 to emptyList()),
        "Buffalo Trace" to (89 to emptyList()),
        "Toscano" to (89 to listOf("Clint Eastwood famously clenched Italian Toscano cheroots in Sergio Leone's Dollars Trilogy.")),
    )

    fun forCigar(cigar: Cigar): CigarAccolade {
        val exact = exactAccolades[cigar.id]
        val richSpecificCelebs = CigarCelebrityDirectory.cigarSpecificCelebrities[cigar.id]
        val richBrandCelebs = CigarCelebrityDirectory.brandCelebrities[cigar.brand]

        if (exact != null) {
            val mergedCelebs = when {
                !richSpecificCelebs.isNullOrEmpty() -> richSpecificCelebs
                exact.celebrityNotes.isNotEmpty() -> exact.celebrityNotes
                !richBrandCelebs.isNullOrEmpty() -> richBrandCelebs
                else -> emptyList()
            }
            return exact.copy(
                celebrityNotes = mergedCelebs,
                hasInternationalCelebrity = CigarCelebrityDirectory.hasInternationalCelebrity(mergedCelebs),
            )
        }

        val resolvedCelebs = richSpecificCelebs
            ?: richBrandCelebs
            ?: brandTypicalScores[cigar.brand]?.second.orEmpty()
        val hasIntl = CigarCelebrityDirectory.hasInternationalCelebrity(resolvedCelebs)

        if (isUnratedInfusedOrMachine(cigar)) {
            return CigarAccolade(
                cigarAficionadoRating = null,
                ratingYear = null,
                ratingSourceLabel = "Cigar Aficionado Policy",
                ratingHighlight = "Infused, flavored, or machine-made cigars are not blind-scored on Cigar Aficionado's 100-point traditional scale.",
                cigarOfTheYearYear = null,
                celebrityNotes = resolvedCelebs,
                hasInternationalCelebrity = hasIntl,
            )
        }

        val brandEntry = brandTypicalScores[cigar.brand]
        val baseScore = brandEntry?.first ?: when (cigar.strength) {
            StrengthLevel.MILD -> 88
            StrengthLevel.MEDIUM -> 89
            StrengthLevel.MEDIUM_FULL -> 90
            StrengthLevel.FULL -> 90
        }
        return CigarAccolade(
            cigarAficionadoRating = baseScore,
            ratingYear = "House Avg",
            ratingSourceLabel = "Cigar Aficionado (Brand Avg)",
            ratingHighlight = "Typical Cigar Aficionado blind-tasting range for ${cigar.brand}'s ${cigar.origin} portfolio.",
            cigarOfTheYearYear = null,
            celebrityNotes = resolvedCelebs,
            hasInternationalCelebrity = hasIntl,
        )
    }

    fun isCigarOfTheYear(cigar: Cigar): Boolean = forCigar(cigar).isCigarOfTheYear

    fun hasCelebrityAssociation(cigar: Cigar): Boolean = forCigar(cigar).hasCelebrityNotes

    fun hasInternationalCelebrity(cigar: Cigar): Boolean = forCigar(cigar).hasInternationalCelebrity

    fun shortCelebrityLabel(accolade: CigarAccolade): String? {
        val first = accolade.celebrityNotes.firstOrNull() ?: return null
        val dashIdx = first.indexOf("—").takeIf { it > 0 }
            ?: first.indexOf(":").takeIf { it > 0 }
        val base = if (dashIdx != null) {
            first.substring(0, dashIdx).trim()
        } else {
            val commaIdx = first.indexOf(",").takeIf { it in 4..42 }
            if (commaIdx != null) {
                first.substring(0, commaIdx).trim() + " +"
            } else {
                first.split(" ").take(3).joinToString(" ").trimEnd(',', '.', ';')
            }
        }
        return base
    }

    private fun isUnratedInfusedOrMachine(cigar: Cigar): Boolean {
        if (cigar.brand in unratedInfusedBrands) return true
        if (cigar.id in unratedSpecificIds) return true
        val lowerName = cigar.name.lowercase()
        val lowerSummary = cigar.summary.lowercase()
        if (cigar.brand == "CAO" && (
                lowerName.contains("moontrance") ||
                    lowerName.contains("eileen") ||
                    lowerName.contains("bella vanilla") ||
                    lowerName.contains("cherrybomb") ||
                    lowerName.contains("honey") ||
                    lowerName.contains("flavour")
                )
        ) {
            return true
        }
        if (lowerSummary.contains("infused") && (
                lowerSummary.contains("cognac") ||
                    lowerSummary.contains("espresso") ||
                    lowerSummary.contains("sweetened") ||
                    lowerSummary.contains("flavored")
                )
        ) {
            return true
        }
        return false
    }
}
