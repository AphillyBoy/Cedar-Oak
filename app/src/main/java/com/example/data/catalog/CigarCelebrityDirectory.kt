package com.example.data.catalog

import com.example.model.CelebrityArchetype
import com.example.model.CelebrityProfile

object CigarCelebrityDirectory {

    private val internationalMarkers = listOf(
        "(UK)", "(England)", "(Scotland)", "(Ireland)", "(Ireland/UK)", "(France)",
        "(Italy)", "(Spain)", "(Argentina)", "(Brazil)", "(Dominican Republic)",
        "(Cuba)", "(Cuba/USA)", "(Mexico)", "(Canada)", "(Australia)",
        "(New Zealand/Australia)", "(Hong Kong)", "(India)", "(Sweden)",
        "(Austria/USA)", "(Barbados)", "(Puerto Rico)", "(Switzerland)",
        "(Ukraine/Switzerland)", "(Armenia/Lebanon)", "(Serbia)", "(Japan)",
        "(South Korea)", "(Germany)", "(Greece)", "(Portugal)", "(Colombia)",
        "(Venezuela)", "(Panama)", "(Jamaica)", "(Nigeria)", "(South Africa)",
        "(Egypt)", "(Netherlands)", "(Belgium)", "(Denmark)", "(Philippines)",
        "International",
    )

    /**
     * Rich, browsable directory of 105+ local (USA) & worldwide international celebrity aficionados,
     * each mapped to a cartoon archetype, country flag, and custom cartoon visual traits.
     */
    val allCelebrityProfiles: List<CelebrityProfile> = listOf(
        // --- UK, Ireland & Europe ---
        CelebrityProfile(
            id = "winston_churchill", name = "Sir Winston Churchill", country = "UK", flagEmoji = "🇬🇧",
            isInternational = true, archetype = CelebrityArchetype.STATESMAN, roleTag = "Prime Minister",
            note = "Inspired the iconic 7\" × 47 Romeo y Julieta Churchill vitola & Davidoff Winston Churchill heritage series.",
            favoriteBrand = "Romeo y Julieta", hasHat = true, accentHex = 0xFFC99B3BL,
        ),
        CelebrityProfile(
            id = "arnold_schwarzenegger", name = "Arnold Schwarzenegger", country = "Austria/USA", flagEmoji = "🇦🇹",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Action Icon & Governor",
            note = "Legendary collector of Cuban Cohiba, Montecristo No. 2, Partagás Serie D No. 4, Punch & Hoyo de Monterrey.",
            favoriteBrand = "Cohiba", hasGlasses = true, accentHex = 0xFFD9A326L,
        ),
        CelebrityProfile(
            id = "roger_moore", name = "Sir Roger Moore", country = "UK", flagEmoji = "🇬🇧",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "James Bond 007",
            note = "Favored Cuban Montecristo No. 2 and H. Upmann coronas throughout his iconic Bond era.",
            favoriteBrand = "Montecristo", accentHex = 0xFF8B5A2BL,
        ),
        CelebrityProfile(
            id = "sean_connery", name = "Sir Sean Connery", country = "Scotland", flagEmoji = "🏴󠁧󠁢󠁳󠁣󠁴󠁿",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Cinema Legend",
            note = "Celebrated Scottish icon who paired classic Cuban coronas and Davidoff cigars with single malt Scotch.",
            favoriteBrand = "Davidoff", hasBeardOrStache = true, accentHex = 0xFF3E6B89L,
        ),
        CelebrityProfile(
            id = "idris_elba", name = "Idris Elba", country = "UK", flagEmoji = "🇬🇧",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Actor & DJ",
            note = "British style icon known for savoring Scotch-cask Davidoff Late Hour and vintage Havana robustos.",
            favoriteBrand = "Davidoff", hasBeardOrStache = true, accentHex = 0xFF9C4A2FL,
        ),
        CelebrityProfile(
            id = "gordon_ramsay", name = "Gordon Ramsay", country = "UK", flagEmoji = "🇬🇧",
            isInternational = true, archetype = CelebrityArchetype.GOURMET, roleTag = "Michelin Chef",
            note = "Celebrates restaurant openings with refined Davidoff luxury cigars and single malt whisky.",
            favoriteBrand = "Davidoff", accentHex = 0xFFD47B2AL,
        ),
        CelebrityProfile(
            id = "david_beckham", name = "David Beckham", country = "UK", flagEmoji = "🇬🇧",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "Football Icon",
            note = "Photographed celebrating career milestones with Cuban Cohiba Siglo VI and Montecristo.",
            favoriteBrand = "Cohiba", hasBeardOrStache = true, accentHex = 0xFF2E5C4EL,
        ),
        CelebrityProfile(
            id = "lewis_hamilton", name = "Sir Lewis Hamilton", country = "UK", flagEmoji = "🇬🇧",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "7× F1 Champion",
            note = "Celebrated Formula 1 champion spotted enjoying luxury Davidoff & Cuban grand prix celebration smokes.",
            favoriteBrand = "Davidoff", hasGlasses = true, hasBeardOrStache = true, accentHex = 0xFF8E2430L,
        ),
        CelebrityProfile(
            id = "jeremy_irons", name = "Jeremy Irons", country = "UK", flagEmoji = "🇬🇧",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Oscar Winner",
            note = "Connoisseur of bold Cuban Partagás Serie D No. 4 and Ramón Allones Specially Selected.",
            favoriteBrand = "Partagás", hasBeardOrStache = true, accentHex = 0xFF8C1D18L,
        ),
        CelebrityProfile(
            id = "elton_john", name = "Sir Elton John", country = "UK", flagEmoji = "🇬🇧",
            isInternational = true, archetype = CelebrityArchetype.MUSIC, roleTag = "Music Legend",
            note = "British rock icon photographed enjoying classic Romeo y Julieta Cuban vitolas.",
            favoriteBrand = "Romeo y Julieta", hasGlasses = true, accentHex = 0xFFB04A88L,
        ),
        CelebrityProfile(
            id = "tom_hardy", name = "Tom Hardy", country = "UK", flagEmoji = "🇬🇧",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "British Actor",
            note = "Known to appreciate hearty, earthy full-strength Cuban Bolívar figurados.",
            favoriteBrand = "Bolívar", hasBeardOrStache = true, accentHex = 0xFF5D3A1AL,
        ),
        CelebrityProfile(
            id = "michael_caine", name = "Sir Michael Caine", country = "UK", flagEmoji = "🇬🇧",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "British Cinema Icon",
            note = "Lifelong London gentleman aficionado of classic Havana Montecristo & Romeo y Julieta.",
            favoriteBrand = "Romeo y Julieta", hasGlasses = true, accentHex = 0xFF4A6375L,
        ),
        CelebrityProfile(
            id = "conor_mcgregor", name = "Conor McGregor", country = "Ireland", flagEmoji = "🇮🇪",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "MMA Champion",
            note = "Frequently celebrates fight victories with Cuban Cohiba Behike and Padrón 1926 Serie Maduro.",
            favoriteBrand = "Padrón", hasBeardOrStache = true, accentHex = 0xFF2D6A4FL,
        ),
        CelebrityProfile(
            id = "pierce_brosnan", name = "Pierce Brosnan", country = "Ireland/UK", flagEmoji = "🇮🇪",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "James Bond 007",
            note = "Longtime admirer of Cuban Montecristo pyramids and Romeo y Julieta Churchills.",
            favoriteBrand = "Montecristo", hasBeardOrStache = true, accentHex = 0xFF52796FL,
        ),
        CelebrityProfile(
            id = "colin_farrell", name = "Colin Farrell", country = "Ireland", flagEmoji = "🇮🇪",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Irish Actor",
            note = "Noted Dublin lounge enthusiast drawn to earthy Montecristo and classic H. Upmann blends.",
            favoriteBrand = "H. Upmann", hasBeardOrStache = true, accentHex = 0xFF3A6B4CL,
        ),
        CelebrityProfile(
            id = "zinedine_zidane", name = "Zinedine Zidane", country = "France", flagEmoji = "🇫🇷",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "World Cup Legend",
            note = "French football icon associated with Cuban Cohiba Siglo VI championship celebrations.",
            favoriteBrand = "Cohiba", accentHex = 0xFF2B4C7EL,
        ),
        CelebrityProfile(
            id = "alain_delon", name = "Alain Delon", country = "France", flagEmoji = "🇫🇷",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "French Cinema Icon",
            note = "Classic French screen icon celebrated alongside vintage Cuban Montecristo torpedos.",
            favoriteBrand = "Montecristo", hasGlasses = true, accentHex = 0xFF3E4E6CL,
        ),
        CelebrityProfile(
            id = "gerard_depardieu", name = "Gérard Depardieu", country = "France", flagEmoji = "🇫🇷",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "French Actor",
            note = "Documented aficionado of robust, full-flavored Cuban Partagás cigars.",
            favoriteBrand = "Partagás", accentHex = 0xFF7B2D26L,
        ),
        CelebrityProfile(
            id = "jean_reno", name = "Jean Reno", country = "France", flagEmoji = "🇫🇷",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "French Actor",
            note = "Parisian connoisseur noted for enjoying refined Montecristo and Trinidad coronas.",
            favoriteBrand = "Trinidad", hasGlasses = true, hasBeardOrStache = true, accentHex = 0xFF455A64L,
        ),
        CelebrityProfile(
            id = "carlo_ancelotti", name = "Carlo Ancelotti", country = "Italy", flagEmoji = "🇮🇹",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "Champions League Manager",
            note = "Immortalized Real Madrid title parades wearing sunglasses with a Cuban Montecristo cigar.",
            favoriteBrand = "Montecristo", hasGlasses = true, accentHex = 0xFF1F4E79L,
        ),
        CelebrityProfile(
            id = "luciano_pavarotti", name = "Luciano Pavarotti", country = "Italy", flagEmoji = "🇮🇹",
            isInternational = true, archetype = CelebrityArchetype.MUSIC, roleTag = "Opera Tenor",
            note = "Celebrated opera maestro who treasured silky, refined Dominican Davidoff blends.",
            favoriteBrand = "Davidoff", hasBeardOrStache = true, accentHex = 0xFF9E3A26L,
        ),
        CelebrityProfile(
            id = "andrea_bocelli", name = "Andrea Bocelli", country = "Italy", flagEmoji = "🇮🇹",
            isInternational = true, archetype = CelebrityArchetype.MUSIC, roleTag = "Italian Tenor",
            note = "Connected to Mediterranean lounge elegance, Davidoff classics, and Tuscan Toscano heritage.",
            favoriteBrand = "Toscano", hasBeardOrStache = true, accentHex = 0xFF8C5327L,
        ),
        CelebrityProfile(
            id = "sophia_loren", name = "Sophia Loren", country = "Italy", flagEmoji = "🇮🇹",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Italian Screen Legend",
            note = "Golden-age Italian cinema icon photographed at Rome's classic cigar & espresso salons.",
            favoriteBrand = "Toscano", hasGlasses = true, accentHex = 0xFF9C2C3BL,
        ),
        CelebrityProfile(
            id = "miguel_angel_jimenez", name = "Miguel Ángel Jiménez", country = "Spain", flagEmoji = "🇪🇸",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "European Golf Legend",
            note = "Golf's 'Most Interesting Man' famously smoking Cuban Cohiba Siglo VI & Trinidad on tour.",
            favoriteBrand = "Cohiba", hasGlasses = true, accentHex = 0xFFB83B28L,
        ),
        CelebrityProfile(
            id = "antonio_banderas", name = "Antonio Banderas", country = "Spain", flagEmoji = "🇪🇸",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Spanish Actor",
            note = "Andalusian cinema star noted for enjoying classic Cuban Montecristo and La Flor Dominicana.",
            favoriteBrand = "Montecristo", hasBeardOrStache = true, accentHex = 0xFFA63429L,
        ),
        CelebrityProfile(
            id = "javier_bardem", name = "Javier Bardem", country = "Spain", flagEmoji = "🇪🇸",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Oscar Winner",
            note = "Celebrated Spanish actor known for savoring bold Cuban Partagás & Bolívar after shoots.",
            favoriteBrand = "Partagás", hasBeardOrStache = true, accentHex = 0xFF7D2E28L,
        ),
        CelebrityProfile(
            id = "julio_iglesias", name = "Julio Iglesias", country = "Spain", flagEmoji = "🇪🇸",
            isInternational = true, archetype = CelebrityArchetype.MUSIC, roleTag = "Latin Pop Legend",
            note = "Iconic Spanish singer who enjoys creamy Cuban Hoyo de Monterrey coronas.",
            favoriteBrand = "Hoyo de Monterrey", accentHex = 0xFFC27B29L,
        ),
        CelebrityProfile(
            id = "michael_schumacher", name = "Michael Schumacher", country = "Germany", flagEmoji = "🇩🇪",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "7× F1 Champion",
            note = "Famously lit Cuban Cohiba & Montecristo celebration cigars after Ferrari Grand Prix victories.",
            favoriteBrand = "Cohiba", accentHex = 0xFFB71C1CL,
        ),
        CelebrityProfile(
            id = "dirk_nowitzki", name = "Dirk Nowitzki", country = "Germany", flagEmoji = "🇩🇪",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "NBA Hall of Famer",
            note = "Part of the international NBA championship celebration tradition with fine Nicaraguan & Cuban cigars.",
            favoriteBrand = "Padrón", accentHex = 0xFF1E56A0L,
        ),
        CelebrityProfile(
            id = "jose_mourinho", name = "José Mourinho", country = "Portugal", flagEmoji = "🇵🇹",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "Football Manager",
            note = "Celebrated European football tactician spotted pairing Portuguese tawny port with Cuban coronas.",
            favoriteBrand = "Montecristo", accentHex = 0xFF1F6E43L,
        ),
        CelebrityProfile(
            id = "giannis_antetokounmpo", name = "Giannis Antetokounmpo", country = "Greece", flagEmoji = "🇬🇷",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "NBA Champion",
            note = "Celebrated his 2021 NBA Finals MVP triumph in the championship cigar & champagne locker room.",
            favoriteBrand = "Arturo Fuente", accentHex = 0xFF21669EL,
        ),
        CelebrityProfile(
            id = "zlatan_ibrahimovic", name = "Zlatan Ibrahimović", country = "Sweden", flagEmoji = "🇸🇪",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "Football Icon",
            note = "Walked onto the San Siro pitch smoking a celebratory championship cigar for AC Milan.",
            favoriteBrand = "Trinidad", hasBeardOrStache = true, accentHex = 0xFF2B5F9EL,
        ),
        CelebrityProfile(
            id = "nikola_jokic", name = "Nikola Jokić", country = "Serbia", flagEmoji = "🇷🇸",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "3× NBA MVP",
            note = "Iconic Serbian Finals MVP photographed celebrating Denver's title with victory cigars.",
            favoriteBrand = "Oliva", accentHex = 0xFF2F4F8FL,
        ),
        CelebrityProfile(
            id = "mads_mikkelsen", name = "Mads Mikkelsen", country = "Denmark", flagEmoji = "🇩🇰",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Danish Actor",
            note = "Nordic cinema star known to appreciate classic European lounge cigars and single malt whisky.",
            favoriteBrand = "Davidoff", accentHex = 0xFF8F2430L,
        ),
        CelebrityProfile(
            id = "jean_claude_van_damme", name = "Jean-Claude Van Damme", country = "Belgium", flagEmoji = "🇧🇪",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Action Star",
            note = "Belgian martial arts cinema icon photographed with classic Cuban & Dominican robustos.",
            favoriteBrand = "Cohiba", hasGlasses = true, accentHex = 0xFFB8860BL,
        ),
        CelebrityProfile(
            id = "zino_davidoff", name = "Zino Davidoff", country = "Ukraine/Switzerland", flagEmoji = "🇨🇭",
            isInternational = true, archetype = CelebrityArchetype.STATESMAN, roleTag = "Master Tobacconist",
            note = "Geneva's legendary 'King of Cigars' who pioneered the desktop humidor and Davidoff Signature No. 2.",
            favoriteBrand = "Davidoff", hasGlasses = true, accentHex = 0xFFC99B3BL,
        ),
        CelebrityProfile(
            id = "avo_uvezian", name = "Avo Uvezian", country = "Armenia/Lebanon", flagEmoji = "🇦🇲",
            isInternational = true, archetype = CelebrityArchetype.MUSIC, roleTag = "Jazz Pianist & Blender",
            note = "Composed Frank Sinatra's 'Strangers in the Night' melody and founded AVO Cigars in the Dominican Republic.",
            favoriteBrand = "AVO", hasHat = true, accentHex = 0xFFB36A2EL,
        ),

        // --- Latin America, Caribbean & Canada ---
        CelebrityProfile(
            id = "diego_maradona", name = "Diego Maradona", country = "Argentina", flagEmoji = "🇦🇷",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "Football Legend",
            note = "Argentine icon frequently photographed savoring prized Cuban Cohiba Behike & Espléndidos.",
            favoriteBrand = "Cohiba", hasBeardOrStache = true, accentHex = 0xFF4299E1L,
        ),
        CelebrityProfile(
            id = "pele", name = "Pelé", country = "Brazil", flagEmoji = "🇧🇷",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "3× World Cup King",
            note = "Brazilian football royalty celebrated alongside rich Brazilian Arapiraca & vintage Cuban cigars.",
            favoriteBrand = "CAO", accentHex = 0xFF2E8B57L,
        ),
        CelebrityProfile(
            id = "ronaldinho", name = "Ronaldinho", country = "Brazil", flagEmoji = "🇧🇷",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "Ballon d'Or Icon",
            note = "Charismatic Brazilian football magician spotted celebrating with luxury Cohiba & CAO Brazilia cigars.",
            favoriteBrand = "CAO", hasHat = true, accentHex = 0xFF228B22L,
        ),
        CelebrityProfile(
            id = "canelo_alvarez", name = "Canelo Álvarez", country = "Mexico", flagEmoji = "🇲🇽",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "Boxing Champion",
            note = "Mexican champion photographed celebrating title bouts with Padrón 1964/1926 & Casa Turrent San Andrés.",
            favoriteBrand = "Padrón", hasBeardOrStache = true, accentHex = 0xFF2F855AL,
        ),
        CelebrityProfile(
            id = "david_ortiz", name = "David 'Big Papi' Ortiz", country = "Dominican Republic", flagEmoji = "🇩🇴",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "MLB Hall of Famer",
            note = "Proud ambassador of Dominican cigar craftsmanship, Arturo Fuente OpusX & La Aurora.",
            favoriteBrand = "Arturo Fuente", hasBeardOrStache = true, accentHex = 0xFF9B2C2CL,
        ),
        CelebrityProfile(
            id = "sammy_sosa", name = "Sammy Sosa", country = "Dominican Republic", flagEmoji = "🇩🇴",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "MLB Slugger",
            note = "Longtime connoisseur of Arturo Fuente Don Carlos and Dominican reserve vitolas.",
            favoriteBrand = "Arturo Fuente", accentHex = 0xFF2B6CB0L,
        ),
        CelebrityProfile(
            id = "mariano_rivera", name = "Mariano Rivera", country = "Panama", flagEmoji = "🇵🇦",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "MLB Hall of Famer",
            note = "Celebrated Panamanian closer known for savoring smooth Dominican & Nicaraguan victory cigars.",
            favoriteBrand = "Arturo Fuente", accentHex = 0xFF2C5282L,
        ),
        CelebrityProfile(
            id = "miguel_cabrera", name = "Miguel Cabrera", country = "Venezuela", flagEmoji = "🇻🇪",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "Triple Crown Slugger",
            note = "Venezuelan baseball legend who shares Latin American clubhouse love for My Father & Padrón cigars.",
            favoriteBrand = "My Father", hasBeardOrStache = true, accentHex = 0xFFB7791FL,
        ),
        CelebrityProfile(
            id = "benicio_del_toro", name = "Benicio del Toro", country = "Puerto Rico", flagEmoji = "🇵🇷",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Oscar Winner",
            note = "Devoted admirer of full-bodied Cuban Partagás Serie D No. 4 and Bolívar Belicosos Finos.",
            favoriteBrand = "Partagás", accentHex = 0xFF7B241CL,
        ),
        CelebrityProfile(
            id = "andy_garcia", name = "Andy Garcia", country = "Cuba/USA", flagEmoji = "🇨🇺",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Actor & Director",
            note = "Close friend of Carlito Fuente and devoted collector of Arturo Fuente OpusX rarities.",
            favoriteBrand = "Arturo Fuente", hasHat = true, accentHex = 0xFF8B1E1EL,
        ),
        CelebrityProfile(
            id = "rihanna", name = "Rihanna", country = "Barbados", flagEmoji = "🇧🇧",
            isInternational = true, archetype = CelebrityArchetype.MUSIC, roleTag = "Global Music Icon",
            note = "Photographed enjoying flagship Cuban Cohiba cigars during iconic Havana cultural visits.",
            favoriteBrand = "Cohiba", hasGlasses = true, accentHex = 0xFF9B4DCAL,
        ),
        CelebrityProfile(
            id = "usain_bolt", name = "Usain Bolt", country = "Jamaica", flagEmoji = "🇯🇲",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "8× Olympic Champion",
            note = "Jamaican sprint legend celebrated across Caribbean lounges with aged rum and Macanudo/Cohiba.",
            favoriteBrand = "Macanudo", accentHex = 0xFFD69E2EL,
        ),
        CelebrityProfile(
            id = "sofia_vergara", name = "Sofía Vergara", country = "Colombia", flagEmoji = "🇨🇴",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Actress & Producer",
            note = "Colombian star photographed celebrating red-carpet nights with classic smooth cigars and Colombian coffee.",
            favoriteBrand = "Davidoff", accentHex = 0xFFD69E2EL,
        ),
        CelebrityProfile(
            id = "drake", name = "Drake", country = "Canada", flagEmoji = "🇨🇦",
            isInternational = true, archetype = CelebrityArchetype.MUSIC, roleTag = "Global Recording Artist",
            note = "Avid collector of rare Cuban Cohiba Behike releases and top-shelf Davidoff editions.",
            favoriteBrand = "Cohiba", hasBeardOrStache = true, accentHex = 0xFFB8860BL,
        ),
        CelebrityProfile(
            id = "wayne_gretzky", name = "Wayne Gretzky", country = "Canada", flagEmoji = "🇨🇦",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "The Great One",
            note = "Hockey icon known to mark milestones with vintage Arturo Fuente and fine wine.",
            favoriteBrand = "Arturo Fuente", accentHex = 0xFFC53030L,
        ),

        // --- Asia, Middle East, Africa & Oceania ---
        CelebrityProfile(
            id = "jackie_chan", name = "Jackie Chan", country = "Hong Kong", flagEmoji = "🇭🇰",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Global Action Legend",
            note = "International cinema superstar known to appreciate flagship Cuban Cohiba Siglo reserves.",
            favoriteBrand = "Cohiba", accentHex = 0xFFC53030L,
        ),
        CelebrityProfile(
            id = "shah_rukh_khan", name = "Shah Rukh Khan", country = "India", flagEmoji = "🇮🇳",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Bollywood Icon",
            note = "Global cinema legend associated with luxury Cuban Cohiba and Davidoff celebration evenings.",
            favoriteBrand = "Cohiba", accentHex = 0xFFDD6B20L,
        ),
        CelebrityProfile(
            id = "ken_watanabe", name = "Ken Watanabe", country = "Japan", flagEmoji = "🇯🇵",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Japanese Actor",
            note = "Acclaimed Japanese star noted for refined Tokyo cigar bar pairings with Japanese whisky & Davidoff.",
            favoriteBrand = "Davidoff", accentHex = 0xFF9B2C2CL,
        ),
        CelebrityProfile(
            id = "lee_byung_hun", name = "Lee Byung-hun", country = "South Korea", flagEmoji = "🇰🇷",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Korean Cinema Star",
            note = "Celebrated Korean actor associated with Seoul's luxury cigar & single malt lounge scene.",
            favoriteBrand = "Davidoff", accentHex = 0xFF2B6CB0L,
        ),
        CelebrityProfile(
            id = "manny_pacquiao", name = "Manny Pacquiao", country = "Philippines", flagEmoji = "🇵🇭",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "8-Division Champ",
            note = "Philippine boxing legend celebrated alongside historic Manila & Cuban-seed cigar traditions.",
            favoriteBrand = "Padrón", accentHex = 0xFF2B6CB0L,
        ),
        CelebrityProfile(
            id = "burna_boy", name = "Burna Boy", country = "Nigeria", flagEmoji = "🇳🇬",
            isInternational = true, archetype = CelebrityArchetype.MUSIC, roleTag = "Afrobeats Superstar",
            note = "Grammy-winning Nigerian icon frequently photographed with luxury Cuban Cohiba & cognac.",
            favoriteBrand = "Cohiba", hasGlasses = true, hasBeardOrStache = true, accentHex = 0xFF2F855AL,
        ),
        CelebrityProfile(
            id = "ernie_els", name = "Ernie Els", country = "South Africa", flagEmoji = "🇿🇦",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "4× Major Golf Champ",
            note = "South African golf Hall of Famer and winemaker who pairs smooth Montecristo & Cabernet.",
            favoriteBrand = "Montecristo", accentHex = 0xFF2F855AL,
        ),
        CelebrityProfile(
            id = "omar_sharif", name = "Omar Sharif", country = "Egypt", flagEmoji = "🇪🇬",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Classic Screen Legend",
            note = "Suave Egyptian cinema icon famously pictured enjoying Havana Montecristo & bridge evenings.",
            favoriteBrand = "Montecristo", hasBeardOrStache = true, accentHex = 0xFFB7791FL,
        ),
        CelebrityProfile(
            id = "hugh_jackman", name = "Hugh Jackman", country = "Australia", flagEmoji = "🇦🇺",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Actor & Showman",
            note = "Australian star known to appreciate smooth Romeo y Julieta Cuban classics on celebratory nights.",
            favoriteBrand = "Romeo y Julieta", hasBeardOrStache = true, accentHex = 0xFF2C7A7BL,
        ),
        CelebrityProfile(
            id = "russell_crowe", name = "Russell Crowe", country = "New Zealand/Australia", flagEmoji = "🇳🇿",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Oscar Winner",
            note = "Known aficionado of Cuban Montecristo No. 2 and vintage cellar wines.",
            favoriteBrand = "Montecristo", hasBeardOrStache = true, accentHex = 0xFF4A5568L,
        ),
        CelebrityProfile(
            id = "greg_norman", name = "Greg Norman", country = "Australia", flagEmoji = "🇦🇺",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "Golf Hall of Famer",
            note = "Australian golf legend who partnered on signature lounge evenings with Hoyo de Monterrey & Shiraz.",
            favoriteBrand = "Hoyo de Monterrey", hasHat = true, accentHex = 0xFFD69E2EL,
        ),

        // --- Local / United States Icons ---
        CelebrityProfile(
            id = "michael_jordan", name = "Michael Jordan", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.ATHLETE, roleTag = "6× NBA Champion",
            note = "Legendary humidor collector of Padrón 1964/1926, Cuban Cohiba Siglo VI, Partagás & Hoyo Epicure No. 2.",
            favoriteBrand = "Padrón", accentHex = 0xFFC53030L,
        ),
        CelebrityProfile(
            id = "jack_nicholson", name = "Jack Nicholson", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "3× Oscar Winner",
            note = "Cigar Aficionado cover legend renowned for Cuban Montecristo No. 2 and vintage Cohiba.",
            favoriteBrand = "Montecristo", hasGlasses = true, accentHex = 0xFFD69E2EL,
        ),
        CelebrityProfile(
            id = "jay_z", name = "Jay-Z", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.MUSIC, roleTag = "Hip-Hop Mogul",
            note = "Co-created Cohiba Comador with General Cigar and collects rare Cuban Cohiba Behike.",
            favoriteBrand = "Cohiba", hasGlasses = true, accentHex = 0xFFB8860BL,
        ),
        CelebrityProfile(
            id = "guy_fieri", name = "Guy Fieri", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.GOURMET, roleTag = "Chef & TV Host",
            note = "Official co-creator of the 94-rated Knuckle Sandwich cigar line with Erik Espinosa & AJ Fernandez.",
            favoriteBrand = "Espinosa", hasGlasses = true, hasBeardOrStache = true, accentHex = 0xFFDD6B20L,
        ),
        CelebrityProfile(
            id = "sylvester_stallone", name = "Sylvester Stallone", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Action Icon",
            note = "Longtime Arturo Fuente OpusX devotee featured multiple times in Cigar Aficionado.",
            favoriteBrand = "Arturo Fuente", accentHex = 0xFF9B2C2CL,
        ),
        CelebrityProfile(
            id = "steve_harvey", name = "Steve Harvey", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Host & Comedian",
            note = "Devoted lounge regular who names Padrón 1964 Anniversary Maduro and Fuente OpusX his favorites.",
            favoriteBrand = "Padrón", hasBeardOrStache = true, accentHex = 0xFF805AD5L,
        ),
        CelebrityProfile(
            id = "joe_rogan", name = "Joe Rogan", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Podcast Host",
            note = "Frequently praises Padrón 1964/1926 and Drew Estate Liga Privada No. 9 for cocoa-espresso draw.",
            favoriteBrand = "Drew Estate", accentHex = 0xFF4A5568L,
        ),
        CelebrityProfile(
            id = "dave_chappelle", name = "Dave Chappelle", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Stand-Up Legend",
            note = "Regularly photographed backstage enjoying box-pressed Padrón Anniversary vitolas.",
            favoriteBrand = "Padrón", accentHex = 0xFF38A169L,
        ),
        CelebrityProfile(
            id = "james_gandolfini", name = "James Gandolfini", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "The Sopranos Icon",
            note = "Immortalized CAO Brazilia and Macanudo Café cigars on screen as Tony Soprano.",
            favoriteBrand = "CAO", accentHex = 0xFF744210L,
        ),
        CelebrityProfile(
            id = "snoop_dogg", name = "Snoop Dogg", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.MUSIC, roleTag = "West Coast Icon",
            note = "Photographed enjoying bold Drew Estate boutique creations and celebratory luxury cigars.",
            favoriteBrand = "Drew Estate", hasGlasses = true, hasBeardOrStache = true, accentHex = 0xFF6B46C1L,
        ),
        CelebrityProfile(
            id = "charles_barkley", name = "Charles Barkley", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.ATHLETE, roleTag = "NBA Hall of Famer",
            note = "Frequently spotted on celebrity golf fairways enjoying Oliva Serie V Melanio & Rocky Patel.",
            favoriteBrand = "Oliva", accentHex = 0xFFB7791FL,
        ),
        CelebrityProfile(
            id = "tom_brady", name = "Tom Brady", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.ATHLETE, roleTag = "7× Super Bowl Champ",
            note = "Celebrated Super Bowl championships and golf outings alongside classic Padrón & Davidoff cigars.",
            favoriteBrand = "Davidoff", accentHex = 0xFF2B6CB0L,
        ),
        CelebrityProfile(
            id = "lebron_james", name = "LeBron James", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.ATHLETE, roleTag = "4× NBA Champion",
            note = "Celebrated NBA Finals titles smoking luxury Cohiba & Cuban vitolas in iconic locker-room photos.",
            favoriteBrand = "Cohiba", hasBeardOrStache = true, accentHex = 0xFF6B46C1L,
        ),
        CelebrityProfile(
            id = "red_auerbach", name = "Red Auerbach", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.ATHLETE, roleTag = "9× NBA Coach",
            note = "Invented basketball's iconic 'victory cigar' ritual on the Boston Celtics sideline.",
            favoriteBrand = "Hoyo de Monterrey", hasHat = true, accentHex = 0xFF276749L,
        ),
        CelebrityProfile(
            id = "derek_jeter", name = "Derek Jeter", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.ATHLETE, roleTag = "5× World Series Champ",
            note = "Yankees captain photographed celebrating World Series parades with celebratory cigars.",
            favoriteBrand = "Arturo Fuente", accentHex = 0xFF1A365DL,
        ),
        CelebrityProfile(
            id = "post_malone", name = "Post Malone", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.MUSIC, roleTag = "Chart-Topping Artist",
            note = "Known to unwind backstage with smooth Ashton, Davidoff & bourbon pairings.",
            favoriteBrand = "Ashton", hasBeardOrStache = true, accentHex = 0xFF975A16L,
        ),
        CelebrityProfile(
            id = "rick_ross", name = "Rick Ross", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.MUSIC, roleTag = "Hip-Hop Mogul",
            note = "Miami luxury lifestyle icon known for enjoying full-bodied Padrón & My Father cigars.",
            favoriteBrand = "My Father", hasGlasses = true, hasBeardOrStache = true, accentHex = 0xFFD69E2EL,
        ),
        CelebrityProfile(
            id = "bill_murray", name = "Bill Murray", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Comedy Legend",
            note = "Beloved pro-am golf regular frequently spotted with a classic stogie on the Pebble Beach links.",
            favoriteBrand = "Macanudo", hasHat = true, accentHex = 0xFF3182CEL,
        ),
        CelebrityProfile(
            id = "morgan_freeman", name = "Morgan Freeman", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Oscar Winner",
            note = "Longtime admirer of silky Dominican Davidoff Signature and creamy Macanudo Café classics.",
            favoriteBrand = "Macanudo", accentHex = 0xFF5A67D8L,
        ),
        CelebrityProfile(
            id = "robert_de_niro", name = "Robert De Niro", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "2× Oscar Winner",
            note = "Iconic New York lounge connoisseur drawn to Arturo Fuente OpusX and classic Montecristo.",
            favoriteBrand = "Arturo Fuente", accentHex = 0xFF4A5568L,
        ),
        CelebrityProfile(
            id = "al_pacino", name = "Al Pacino", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Cinema Legend",
            note = "Classic admirer of bold Cuban-heritage Bolívar and My Father full-bodied profiles.",
            favoriteBrand = "Bolívar", accentHex = 0xFF742A2AL,
        ),
        CelebrityProfile(
            id = "samuel_l_jackson", name = "Samuel L. Jackson", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Hollywood Icon",
            note = "Golfing cigar aficionado who enjoys box-pressed Padrón and Rocky Patel on the course.",
            favoriteBrand = "Padrón", hasGlasses = true, hasHat = true, accentHex = 0xFF805AD5L,
        ),
        CelebrityProfile(
            id = "matthew_mcconaughey", name = "Matthew McConaughey", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Oscar Winner",
            note = "Known for savoring smooth Macanudo, Gurkha, and Texas bourbon on relaxed afternoons.",
            favoriteBrand = "Macanudo", accentHex = 0xFFDD6B20L,
        ),
        CelebrityProfile(
            id = "ron_perlman", name = "Ron Perlman", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Actor & Aficionado",
            note = "Passionate cigar enthusiast famously smoking stout maduros on and off set in Hellboy & Sons of Anarchy.",
            favoriteBrand = "Alec Bradley", hasBeardOrStache = true, accentHex = 0xFF9B2C2CL,
        ),
        CelebrityProfile(
            id = "groucho_marx", name = "Groucho Marx", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Comedy Pioneer",
            note = "Cinema's most iconic cigar prop master—favored classic Dunhill & Havana coronas.",
            favoriteBrand = "Montecristo", hasGlasses = true, hasBeardOrStache = true, accentHex = 0xFF4A5568L,
        ),
        CelebrityProfile(
            id = "george_burns", name = "George Burns", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Centenarian Icon",
            note = "Famously smoked El Producto & mild American-heritage cigars to age 100.",
            favoriteBrand = "J.C. Newman", hasGlasses = true, accentHex = 0xFF718096L,
        ),
        CelebrityProfile(
            id = "ernest_hemingway", name = "Ernest Hemingway", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.STATESMAN, roleTag = "Nobel Author",
            note = "Inspired Arturo Fuente's legendary Hemingway Short Story & Work of Art figurado series.",
            favoriteBrand = "Arturo Fuente", hasBeardOrStache = true, accentHex = 0xFF2B6CB0L,
        ),
        CelebrityProfile(
            id = "mark_twain", name = "Mark Twain", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.STATESMAN, roleTag = "American Author",
            note = "Honored by Mark Twain Cigars; famously quipped he only ever smoked 'one cigar at a time.'",
            favoriteBrand = "Mark Twain", hasBeardOrStache = true, accentHex = 0xFF744210L,
        ),
        CelebrityProfile(
            id = "john_f_kennedy", name = "John F. Kennedy", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.STATESMAN, roleTag = "35th US President",
            note = "Famously secured 1,200 Cuban H. Upmann Petit Upmanns the night before signing the 1962 embargo.",
            favoriteBrand = "H. Upmann", accentHex = 0xFF2C5282L,
        ),
        CelebrityProfile(
            id = "babe_ruth", name = "Babe Ruth", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.ATHLETE, roleTag = "Baseball Immortal",
            note = "The Sultan of Swat famously celebrated home runs with classic Havana & Tampa-rolled cigars.",
            favoriteBrand = "J.C. Newman", hasHat = true, accentHex = 0xFF2A4365L,
        ),
        CelebrityProfile(
            id = "clint_eastwood", name = "Clint Eastwood", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Director & Icon",
            note = "Immortalized fire-cured Italian Toscano cheroots in Sergio Leone's Dollars Trilogy.",
            favoriteBrand = "Toscano", hasHat = true, accentHex = 0xFF744210L,
        ),
        CelebrityProfile(
            id = "anthony_bourdain", name = "Anthony Bourdain", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.GOURMET, roleTag = "Chef & Storyteller",
            note = "Celebrated global food & tobacco culture across Havana, Lisbon, and Rome with classic hand-rolled cigars.",
            favoriteBrand = "Romeo y Julieta", accentHex = 0xFFB83280L,
        ),
        CelebrityProfile(
            id = "demi_moore", name = "Demi Moore", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Hollywood Icon",
            note = "Historic Cigar Aficionado cover star who championed Cuban Montecristo No. 2 worldwide.",
            favoriteBrand = "Montecristo", accentHex = 0xFF97266DL,
        ),
        CelebrityProfile(
            id = "whoopi_goldberg", name = "Whoopi Goldberg", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "EGOT Winner",
            note = "Longtime fan of approachable, creamy mild-medium Macanudo & Dominican blends.",
            favoriteBrand = "Macanudo", hasGlasses = true, accentHex = 0xFF6B46C1L,
        ),
        CelebrityProfile(
            id = "karl_malone", name = "Karl Malone", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.ATHLETE, roleTag = "NBA Hall of Famer",
            note = "Partnered with La Aurora on his signature Barrel Aged Dominican cigar releases.",
            favoriteBrand = "La Aurora", accentHex = 0xFF3182CEL,
        ),
        CelebrityProfile(
            id = "mike_ditka", name = "Mike Ditka", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.ATHLETE, roleTag = "NFL Hall of Famer",
            note = "Partnered with Camacho on bold, full-flavored Honduran Corojo celebration releases.",
            favoriteBrand = "Camacho", hasGlasses = true, hasBeardOrStache = true, accentHex = 0xFFC05621L,
        ),
        CelebrityProfile(
            id = "francis_ford_coppola", name = "Francis Ford Coppola", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "5× Oscar Director",
            note = "Longtime admirer of Arturo Fuente Don Carlos and Hemingway Cameroon cigars.",
            favoriteBrand = "Arturo Fuente", hasGlasses = true, hasBeardOrStache = true, accentHex = 0xFF742A2AL,
        ),
        CelebrityProfile(
            id = "danny_devito", name = "Danny DeVito", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Actor & Producer",
            note = "Known aficionado of Cuban Partagás Robustos and Montecristo No. 2.",
            favoriteBrand = "Partagás", hasGlasses = true, accentHex = 0xFF9C4221L,
        ),

        // --- Expanded Global Icons (Europe, Latin America, Asia, Africa & Oceania) ---
        CelebrityProfile(
            id = "daniel_craig", name = "Daniel Craig", country = "UK", flagEmoji = "🇬🇧",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "James Bond 007",
            note = "Modern 007 icon associated with refined London cigar terraces, Scotch whisky & Davidoff blends.",
            favoriteBrand = "Davidoff", accentHex = 0xFF2B4C7EL,
        ),
        CelebrityProfile(
            id = "liam_neeson", name = "Liam Neeson", country = "Ireland", flagEmoji = "🇮🇪",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Irish Actor",
            note = "Northern Irish screen veteran known to savor smooth Cuban & Dominican coronas after filming.",
            favoriteBrand = "Romeo y Julieta", accentHex = 0xFF2D6A4FL,
        ),
        CelebrityProfile(
            id = "serge_gainsbourg", name = "Serge Gainsbourg", country = "France", flagEmoji = "🇫🇷",
            isInternational = true, archetype = CelebrityArchetype.MUSIC, roleTag = "French Chanson Icon",
            note = "Parisian musical provocateur forever linked to late-night French café smoke and bold dark leaf.",
            favoriteBrand = "Partagás", hasBeardOrStache = true, accentHex = 0xFF4A5568L,
        ),
        CelebrityProfile(
            id = "federico_fellini", name = "Federico Fellini", country = "Italy", flagEmoji = "🇮🇹",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "5× Oscar Director",
            note = "Maestro of Italian cinema who celebrated Rome's espresso and Tuscan Toscano heritage.",
            favoriteBrand = "Toscano", hasHat = true, accentHex = 0xFF8C5327L,
        ),
        CelebrityProfile(
            id = "gianluigi_buffon", name = "Gianluigi Buffon", country = "Italy", flagEmoji = "🇮🇹",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "World Cup Goalkeeper",
            note = "Italian football legend photographed enjoying celebratory Italian & Cuban post-title cigars.",
            favoriteBrand = "Montecristo", hasBeardOrStache = true, accentHex = 0xFF1F4E79L,
        ),
        CelebrityProfile(
            id = "roberto_baggio", name = "Roberto Baggio", country = "Italy", flagEmoji = "🇮🇹",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "Ballon d'Or Legend",
            note = "Beloved Italian football icon celebrated alongside traditional Venetian & Tuscan tobacco craft.",
            favoriteBrand = "Toscano", hasBeardOrStache = true, accentHex = 0xFF2B6CB0L,
        ),
        CelebrityProfile(
            id = "penelope_cruz", name = "Penélope Cruz", country = "Spain", flagEmoji = "🇪🇸",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Oscar Winner",
            note = "Celebrated Madrid cinema icon associated with classic Spanish lounge culture and smooth coronas.",
            favoriteBrand = "Montecristo", accentHex = 0xFFA63429L,
        ),
        CelebrityProfile(
            id = "rafael_nadal", name = "Rafael Nadal", country = "Spain", flagEmoji = "🇪🇸",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "22× Grand Slam Champ",
            note = "Mallorcan tennis legend who celebrates island evenings with fine Spanish wine & classic Cuban cigars.",
            favoriteBrand = "Cohiba", accentHex = 0xFFC53030L,
        ),
        CelebrityProfile(
            id = "jurgen_klopp", name = "Jürgen Klopp", country = "Germany", flagEmoji = "🇩🇪",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "Champions League Coach",
            note = "Charismatic German manager celebrated across European football title parties with victory cigars.",
            favoriteBrand = "Davidoff", hasGlasses = true, hasBeardOrStache = true, accentHex = 0xFFB71C1CL,
        ),
        CelebrityProfile(
            id = "ruud_gullit", name = "Ruud Gullit", country = "Netherlands", flagEmoji = "🇳🇱",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "Ballon d'Or Icon",
            note = "Dutch football legend who brings flair to European golf outings and classic Havana lounge pairings.",
            favoriteBrand = "Hoyo de Monterrey", hasBeardOrStache = true, accentHex = 0xFFDD6B20L,
        ),
        CelebrityProfile(
            id = "gary_player", name = "Gary Player", country = "South Africa", flagEmoji = "🇿🇦",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "9× Major Golf Legend",
            note = "South Africa's Black Knight celebrated alongside classic clubhouse cigars and Cape red wines.",
            favoriteBrand = "Montecristo", hasHat = true, accentHex = 0xFF2F855AL,
        ),
        CelebrityProfile(
            id = "takeshi_kitano", name = "Takeshi Kitano", country = "Japan", flagEmoji = "🇯🇵",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Japanese Auteur",
            note = "Acclaimed Tokyo filmmaker linked to refined Ginza whisky bars and precision-rolled luxury cigars.",
            favoriteBrand = "Davidoff", hasGlasses = true, accentHex = 0xFF9B2C2CL,
        ),
        CelebrityProfile(
            id = "neymar_jr", name = "Neymar Jr.", country = "Brazil", flagEmoji = "🇧🇷",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "Brazilian Football Star",
            note = "Global football superstar photographed celebrating title wins with luxury Cohiba & Brazilian blends.",
            favoriteBrand = "CAO", hasGlasses = true, accentHex = 0xFF2E8B57L,
        ),
        CelebrityProfile(
            id = "carlos_tevez", name = "Carlos Tevez", country = "Argentina", flagEmoji = "🇦🇷",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "Argentine Forward",
            note = "Buenos Aires football hero spotted celebrating championships with classic Cuban Montecristo & Cohiba.",
            favoriteBrand = "Cohiba", accentHex = 0xFF4299E1L,
        ),
        CelebrityProfile(
            id = "carlos_valderrama", name = "Carlos Valderrama", country = "Colombia", flagEmoji = "🇨🇴",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "Colombian Football Icon",
            note = "Iconic South American playmaker celebrated alongside rich Colombian coffee & warm Latin cigars.",
            favoriteBrand = "My Father", hasBeardOrStache = true, accentHex = 0xFFD69E2EL,
        ),
        CelebrityProfile(
            id = "bad_bunny", name = "Bad Bunny", country = "Puerto Rico", flagEmoji = "🇵🇷",
            isInternational = true, archetype = CelebrityArchetype.MUSIC, roleTag = "Global Latin Superstar",
            note = "Puerto Rican cultural icon photographed enjoying classic Caribbean hand-rolled cigars in San Juan.",
            favoriteBrand = "Arturo Fuente", hasGlasses = true, accentHex = 0xFF9B4DCAL,
        ),
        CelebrityProfile(
            id = "celia_cruz", name = "Celia Cruz", country = "Cuba", flagEmoji = "🇨🇺",
            isInternational = true, archetype = CelebrityArchetype.MUSIC, roleTag = "Queen of Salsa",
            note = "Havana's legendary musical ambassador immortalized alongside classic Cuban café & tobacco heritage.",
            favoriteBrand = "Romeo y Julieta", accentHex = 0xFFD9A326L,
        ),
        CelebrityProfile(
            id = "guillermo_del_toro", name = "Guillermo del Toro", country = "Mexico", flagEmoji = "🇲🇽",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "3× Oscar Director",
            note = "Mexican visionary filmmaker linked to Guadalajara craftsmanship and rich San Andrés maduro tobacco.",
            favoriteBrand = "Casa Turrent", hasGlasses = true, hasBeardOrStache = true, accentHex = 0xFF2F855AL,
        ),
        CelebrityProfile(
            id = "novak_djokovic", name = "Novak Djokovic", country = "Serbia", flagEmoji = "🇷🇸",
            isInternational = true, archetype = CelebrityArchetype.ATHLETE, roleTag = "24× Grand Slam Champ",
            note = "Serbian tennis icon whose championship celebrations honor Balkan hospitality and vintage cigars.",
            favoriteBrand = "Davidoff", accentHex = 0xFF2F4F8FL,
        ),
        CelebrityProfile(
            id = "salma_hayek", name = "Salma Hayek", country = "Mexico", flagEmoji = "🇲🇽",
            isInternational = true, archetype = CelebrityArchetype.CINEMA, roleTag = "Actress & Producer",
            note = "Veracruz-born cinema icon proud of Mexico's San Andrés Valley tobacco and artisanal mezcal.",
            favoriteBrand = "Casa Turrent", accentHex = 0xFFB83280L,
        ),

        // --- Expanded Local / United States Icons ---
        CelebrityProfile(
            id = "shaquille_oneal", name = "Shaquille O'Neal", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.ATHLETE, roleTag = "4× NBA Champion",
            note = "Hall of Fame big man who favors bold 60-ring-gauge Rocky Patel, Padrón & Drew Estate gordo vitolas.",
            favoriteBrand = "Rocky Patel", hasBeardOrStache = true, accentHex = 0xFF6B46C1L,
        ),
        CelebrityProfile(
            id = "john_wayne", name = "John Wayne", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Western Icon",
            note = "Hollywood legend remembered for classic American & Cuban-seed cigars on Western film sets.",
            favoriteBrand = "Hoyo de Monterrey", hasHat = true, accentHex = 0xFF744210L,
        ),
        CelebrityProfile(
            id = "orson_welles", name = "Orson Welles", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Auteur & Director",
            note = "Citizen Kane legend famously photographed smoking plump Cuban & Dominican figurados in edit suites.",
            favoriteBrand = "Montecristo", hasBeardOrStache = true, accentHex = 0xFF4A5568L,
        ),
        CelebrityProfile(
            id = "fifty_cent", name = "50 Cent", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.MUSIC, roleTag = "Hip-Hop Entrepreneur",
            note = "Pairs luxury champagne & cognac lines with top-shelf Cuban Cohiba and Padrón Anniversary cigars.",
            favoriteBrand = "Cohiba", hasHat = true, accentHex = 0xFFB8860BL,
        ),
        CelebrityProfile(
            id = "bruno_mars", name = "Bruno Mars", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.MUSIC, roleTag = "15× Grammy Winner",
            note = "Las Vegas residency showman often photographed with aged rum and vintage-style Ashton & Fuente cigars.",
            favoriteBrand = "Ashton", hasGlasses = true, hasHat = true, accentHex = 0xFFC27B29L,
        ),
        CelebrityProfile(
            id = "kevin_hart", name = "Kevin Hart", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Comedian & Actor",
            note = "Regularly shares lounge nights pairing his Gran Coramino tequila with Padrón & Arturo Fuente.",
            favoriteBrand = "Arturo Fuente", hasBeardOrStache = true, accentHex = 0xFFDD6B20L,
        ),
        CelebrityProfile(
            id = "bruce_willis", name = "Bruce Willis", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.CINEMA, roleTag = "Action Legend",
            note = "Classic Cigar Aficionado cover icon who favored smooth Montecristo & Arturo Fuente Hemingway.",
            favoriteBrand = "Arturo Fuente", accentHex = 0xFF2B6CB0L,
        ),
        CelebrityProfile(
            id = "ulysses_s_grant", name = "Ulysses S. Grant", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.STATESMAN, roleTag = "18th US President",
            note = "Famous 19th-century statesman and general renowned for carrying classic hand-rolled American cigars.",
            favoriteBrand = "J.C. Newman", hasBeardOrStache = true, hasHat = true, accentHex = 0xFF2A4365L,
        ),
        CelebrityProfile(
            id = "john_mayer", name = "John Mayer", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.MUSIC, roleTag = "Guitar Virtuoso",
            note = "Watch and lounge connoisseur known to pair smooth Davidoff & Ashton cigars with Japanese whisky.",
            favoriteBrand = "Davidoff", hasGlasses = true, accentHex = 0xFF3E6B89L,
        ),
        CelebrityProfile(
            id = "frank_sinatra", name = "Frank Sinatra", country = "USA", flagEmoji = "🇺🇸",
            isInternational = false, archetype = CelebrityArchetype.MUSIC, roleTag = "Chairman of the Board",
            note = "Forever linked to Rat Pack lounge nights, Jack Daniel's on the rocks, and Avo Uvezian's 'Strangers in the Night'.",
            favoriteBrand = "AVO", hasHat = true, accentHex = 0xFF1F4E79L,
        ),
    )

    private val profileByNameKey: Map<String, CelebrityProfile> =
        allCelebrityProfiles.associateBy { it.name.lowercase() }

    val cigarSpecificCelebrities: Map<String, List<String>> = mapOf(
        "cohiba_behike_52" to listOf(
            "🇦🇹 Arnold Schwarzenegger (Austria/USA) — Famous admirer of flagship Cuban Cohiba cigars.",
            "🇦🇷 Diego Maradona (Argentina) — Football icon frequently photographed with prized Cuban Cohiba vitolas.",
            "🇨🇦 Drake (Canada) — Celebrated collector of ultra-rare Cuban Cohiba Behike releases.",
            "🇮🇪 Conor McGregor (Ireland) — Photographed celebrating career milestones with Cuban Cohiba Behike.",
            "🇩🇪 Michael Schumacher (Germany) — Famously celebrated Ferrari Formula 1 wins with Cuban Cohiba.",
            "🇳🇬 Burna Boy (Nigeria) — Afrobeats superstar frequently photographed with luxury Cohiba & cognac.",
            "🇺🇸 Jack Nicholson (USA) — Longtime collector of vintage Cuban Cohiba editions.",
            "🇺🇸 Jay-Z (USA) — Frequently celebrates achievements with rare Cohiba vitolas.",
            "🇧🇧 Rihanna (Barbados) — Photographed enjoying Cuban Cohiba cigars during Havana visits.",
        ),
        "cohiba_siglo6" to listOf(
            "🇪🇸 Miguel Ángel Jiménez (Spain) — European Tour golf legend famously smoking Cuban Cohiba Siglo VI on the links.",
            "🇬🇧 David Beckham (UK) — Photographed celebrating major moments with Cuban Cohiba cigars.",
            "🇫🇷 Zinedine Zidane (France) — French football legend associated with Cuban Cohiba celebrations.",
            "🇮🇳 Shah Rukh Khan (India) — Bollywood superstar noted for admiring luxury Cuban Cohiba reserves.",
            "🇭🇰 Jackie Chan (Hong Kong) — International action icon known to appreciate flagship Cuban Cohiba cigars.",
            "🇺🇸 LeBron James (USA) — Celebrated NBA Finals victories with luxury Cuban Cohiba cigars.",
            "🇦🇹 Arnold Schwarzenegger (Austria/USA) — Regularly pictured enjoying Cuban Cohiba Siglo vitolas.",
            "🇺🇸 Michael Jordan (USA) — Keeps Cuban Cohiba Siglo VI among his prized humidor reserves.",
        ),
        "cohiba_comador_toro" to listOf(
            "🇺🇸 Jay-Z (USA) — Co-created and endorsed Cohiba Comador directly with General Cigar Company.",
            "🇺🇸 LeBron James (USA) — Prominent celebrity enthusiast of Cohiba luxury releases.",
            "🇨🇦 Drake (Canada) — Collector of luxury Cohiba editions.",
        ),
        "montecristo_no2" to listOf(
            "🇬🇧 Sir Roger Moore (UK) — James Bond icon who famously favored Cuban Montecristo No. 2 cigars on set.",
            "🇮🇹 Carlo Ancelotti (Italy) — Legendary football manager famous for his Real Madrid championship cigar celebrations.",
            "🇮🇪 Pierce Brosnan (Ireland/UK) — Longtime admirer of Cuban Montecristo pyramids.",
            "🇫🇷 Alain Delon (France) — Classic French cinema icon associated with Cuban Montecristo torpedos.",
            "🇪🇸 Antonio Banderas (Spain) — Spanish actor noted for enjoying classic Cuban Montecristo vitolas.",
            "🇵🇹 José Mourinho (Portugal) — Celebrated football tactician who pairs classic Cuban coronas with vintage port.",
            "🇪🇬 Omar Sharif (Egypt) — Suave screen legend pictured enjoying Havana Montecristo & bridge nights.",
            "🇳🇿 Russell Crowe (New Zealand/Australia) — Known aficionado of Cuban Montecristo No. 2.",
            "🇺🇸 Jack Nicholson (USA) — Classic Montecristo No. 2 devotee profiled in Cigar Aficionado.",
            "🇺🇸 Demi Moore (USA) — Cigar Aficionado cover icon who helped popularize Cuban Montecristo No. 2.",
        ),
        "partagas_d4" to listOf(
            "🇬🇧 Jeremy Irons (UK) — Academy Award-winning British actor known to favor bold Cuban Partagás cigars.",
            "🇫🇷 Gérard Depardieu (France) — French cinema veteran with a documented taste for full-bodied Cuban Partagás.",
            "🇪🇸 Javier Bardem (Spain) — Celebrated Spanish actor drawn to bold Cuban Partagás vitolas.",
            "🇵🇷 Benicio del Toro (Puerto Rico) — Celebrated admirer of earthy, full-strength Cuban Partagás.",
            "🇦🇹 Arnold Schwarzenegger (Austria/USA) — Longtime devotee of Cuban Partagás Serie D No. 4.",
            "🇺🇸 Michael Jordan (USA) — Frequently names Cuban Partagás among his championship smokes.",
            "🇺🇸 Danny DeVito (USA) — Known aficionado of Cuban Partagás Robustos.",
        ),
        "ryj_churchill_cuban" to listOf(
            "🇬🇧 Sir Winston Churchill (UK) — Iconic British Prime Minister whose 1946 Havana visit inspired the 7\" × 47 Churchill vitola.",
            "🇬🇧 Sir Elton John (UK) — British music legend photographed enjoying classic Cuban Romeo y Julieta cigars.",
            "🇬🇧 Sir Michael Caine (UK) — Classic British gentleman aficionado of Romeo y Julieta Havana editions.",
            "🇦🇺 Hugh Jackman (Australia) — Known to appreciate smooth Cuban Romeo y Julieta classics on celebratory nights.",
            "🇮🇪 Pierce Brosnan (Ireland/UK) — Celebrated enthusiast of Romeo y Julieta Cuban heritage cigars.",
            "🇺🇸 Anthony Bourdain (USA) — Celebrated Havana's classic cigar culture on his culinary travels.",
        ),
        "davidoff_winston_churchill" to listOf(
            "🇬🇧 Sir Winston Churchill (UK) — Official heritage tribute celebrating the British statesman's iconic cigar ritual.",
            "🇬🇧 Idris Elba (UK) — British actor and style icon known to enjoy refined Davidoff luxury blends.",
            "🇬🇧 Gordon Ramsay (UK) — Michelin-starred chef noted for celebrating openings with Davidoff cigars.",
            "🇬🇧 Sir Lewis Hamilton (UK) — 7× Formula 1 champion spotted with refined Davidoff luxury releases.",
            "🇩🇰 Mads Mikkelsen (Denmark) — Nordic cinema star drawn to classic European Davidoff lounges.",
        ),
        "davidoff_late_hour" to listOf(
            "🇬🇧 Sir Winston Churchill (UK) — Inspired by Churchill's late-night writing hours and fondness for Scotch whisky.",
            "🏴󠁧󠁢󠁳󠁣󠁴󠁿 Sir Sean Connery (Scotland) — Celebrated Scottish legend who paired refined cigars with single malt Scotch.",
            "🇬🇧 Idris Elba (UK) — Admired by British lounge aficionados pairing Scotch-aged Davidoff leaf with single malt.",
            "🇬🇧 Gordon Ramsay (UK) — Celebrated British gastronome drawn to whisky-cask-aged Davidoff craftsmanship.",
            "🇯🇵 Ken Watanabe (Japan) — Noted for pairing refined Davidoff cigars with Japanese single malt whisky.",
        ),
        "davidoff_winston_churchill_late" to listOf(
            "🇬🇧 Sir Winston Churchill (UK) — Inspired by Churchill's darkest-hour reflections and Scotch whisky ritual.",
            "🇬🇧 Idris Elba (UK) — Notable British fan of refined Davidoff evening cigars.",
            "🇰🇷 Lee Byung-hun (South Korea) — Celebrated Korean actor associated with Seoul's luxury Davidoff lounge culture.",
        ),
        "davidoff_sig2" to listOf(
            "🇨🇭 Zino Davidoff (Ukraine/Switzerland) — The signature Panatela vitola of the legendary Geneva tobacconist.",
            "🇮🇹 Luciano Pavarotti (Italy) — Celebrated opera tenor who prized mild, refined Davidoff cigars.",
            "🇮🇹 Andrea Bocelli (Italy) — Italian tenor associated with elegant Mediterranean & Davidoff lounge traditions.",
            "🇨🇴 Sofía Vergara (Colombia) — Celebrated star photographed enjoying smooth luxury cigars with Colombian coffee.",
            "🇺🇸 Tom Brady (USA) — 7× Super Bowl champion associated with classic celebration Davidoff & Padrón picks.",
            "🇺🇸 Morgan Freeman (USA) — Noted admirer of silky, refined Dominican Davidoff blends.",
        ),
        "hoyo_epicure_2" to listOf(
            "🇦🇹 Arnold Schwarzenegger (Austria/USA) — Regularly names Hoyo de Monterrey among his favorite Cuban daytime cigars.",
            "🇪🇸 Julio Iglesias (Spain) — Legendary Spanish crooner known for enjoying creamy Cuban Hoyo de Monterrey coronas.",
            "🇦🇺 Greg Norman (Australia) — Golf Hall of Famer who enjoys creamy daytime cigars on the fairways.",
            "🇺🇸 Red Auerbach (USA) — Celtics legend who pioneered basketball's iconic victory cigar tradition.",
            "🇺🇸 Michael Jordan (USA) — Frequently enjoys Hoyo de Monterrey Epicure No. 2 on the golf course.",
        ),
        "trinidad_fundadores" to listOf(
            "🇪🇸 Miguel Ángel Jiménez (Spain) — Spanish golf icon known to enjoy refined Cuban Trinidad vitolas.",
            "🇫🇷 Jean Reno (France) — Parisian screen icon noted for refined Cuban coronas and Trinidad blends.",
            "🇸🇪 Zlatan Ibrahimović (Sweden) — Famous for celebratory championship cigars including top-shelf Cuban marques.",
        ),
        "bolivar_belicosos" to listOf(
            "🇵🇷 Benicio del Toro (Puerto Rico) — Noted enthusiast of bold, earthy Cuban Bolívar figurados.",
            "🇬🇧 Tom Hardy (UK) — British actor photographed enjoying hearty, full-strength cigars.",
            "🇺🇸 Al Pacino (USA) — Longtime aficionado of full-bodied Cuban-heritage cigars.",
        ),
        "ramon_allones_specially_selected" to listOf(
            "🇬🇧 Jeremy Irons (UK) — Connoisseur of classic full-flavored Havana robustos.",
            "🇮🇹 Carlo Ancelotti (Italy) — Admirer of rich, balanced Cuban heritage blends.",
        ),
        "upmann_magnum46" to listOf(
            "🇺🇸 John F. Kennedy (USA) — Famously secured 1,200 H. Upmann Petit Upmann Cuban cigars before signing the 1962 embargo.",
            "🇬🇧 Sir Roger Moore (UK) — Classic British gentleman aficionado of vintage Havana coronas.",
            "🇮🇪 Colin Farrell (Ireland) — Noted Irish aficionado of balanced classic Havana blends.",
        ),
        "fuente_opusx" to listOf(
            "🇨🇺 Andy Garcia (Cuba/USA) — Close friend of Carlito Fuente and devoted collector of Chateau de la Fuente rarities.",
            "🇩🇴 David 'Big Papi' Ortiz (Dominican Republic) — Baseball Hall of Famer and proud ambassador of Dominican cigar craft.",
            "🇨🇦 Wayne Gretzky (Canada) — Hockey legend known to celebrate milestones with top-tier Arturo Fuente cigars.",
            "🇬🇷 Giannis Antetokounmpo (Greece) — NBA Finals MVP celebrating in the championship cigar tradition.",
            "🇵🇦 Mariano Rivera (Panama) — Hall of Fame Yankees closer celebrating with top Dominican craftsmanship.",
            "🇺🇸 Sylvester Stallone (USA) — Longtime OpusX devotee featured in Cigar Aficionado interviews.",
            "🇺🇸 Derek Jeter (USA) — 5× World Series champion associated with celebratory Fuente & Cuban smokes.",
            "🇺🇸 Steve Harvey (USA) — Frequently names Arturo Fuente OpusX among his celebratory lounge favorites.",
            "🇺🇸 Robert De Niro (USA) — Longtime New York lounge regular and admirer of Arturo Fuente rarities.",
        ),
        "fuente_short_story" to listOf(
            "🇺🇸 Ernest Hemingway (USA) — Line named in honor of the Nobel laureate and famed Havana cigar aficionado.",
            "🇺🇸 Francis Ford Coppola (USA) — Longtime admirer of Arturo Fuente Hemingway perfectos.",
        ),
        "fuente_don_carlos_aniv" to listOf(
            "🇩🇴 Sammy Sosa (Dominican Republic) — Celebrated Dominican baseball slugger and Arturo Fuente aficionado.",
            "🇺🇸 Francis Ford Coppola (USA) — Longtime admirer of Arturo Fuente's Cameroon-wrapped Don Carlos heritage.",
        ),
        "padron_1964" to listOf(
            "🇲🇽 Canelo Álvarez (Mexico) — Champion Mexican boxer photographed celebrating wins with aged Padrón reserves.",
            "🇩🇪 Dirk Nowitzki (Germany) — NBA Hall of Famer part of basketball's international championship cigar tradition.",
            "🇵🇭 Manny Pacquiao (Philippines) — 8-division boxing icon celebrated alongside championship Nicaraguan reserves.",
            "🇺🇸 Michael Jordan (USA) — Celebrated enthusiast of aged Padrón box-pressed releases.",
            "🇺🇸 Steve Harvey (USA) — Frequently cites Padrón 1964 Anniversary Maduro as his go-to evening smoke.",
            "🇺🇸 Joe Rogan (USA) — Often highlights Padrón 1964 for effortless draw and cocoa richness.",
            "🇺🇸 Samuel L. Jackson (USA) — Noted admirer of box-pressed Nicaraguan Padrón classics.",
        ),
        "padron_1926_no9" to listOf(
            "🇮🇪 Conor McGregor (Ireland) — Frequently photographed enjoying full-bodied Padrón 1926 Serie Maduro vitolas.",
            "🇲🇽 Canelo Álvarez (Mexico) — Known to appreciate flagship Nicaraguan box-pressed Maduro cigars.",
            "🇺🇸 Michael Jordan (USA) — Names Padrón 1926 among his finest full-bodied evening smokes.",
            "🇺🇸 Joe Rogan (USA) — Regularly recommends Padrón 1926 Serie for cocoa-espresso depth.",
            "🇺🇸 Dave Chappelle (USA) — Regularly photographed enjoying Padrón Anniversary vitolas backstage.",
        ),
        "oliva_melanio" to listOf(
            "🇺🇸 Charles Barkley (USA) — Frequently spotted enjoying Oliva Serie V & Melanio cigars at charity golf tournaments.",
            "🇷🇸 Nikola Jokić (Serbia) — 3× NBA MVP part of basketball's international championship cigar tradition.",
        ),
        "my_father_the_judge" to listOf(
            "🇻🇪 Miguel Cabrera (Venezuela) — Triple Crown slugger part of Latin baseball's love for My Father & Nicaraguan leaf.",
            "🇺🇸 Rick Ross (USA) — Miami hip-hop mogul known for full-bodied My Father & Padrón smokes.",
        ),
        "espinosa_knuckle_sandwich_habano" to listOf(
            "🇺🇸 Guy Fieri (USA) — Official celebrity co-creator and brand partner of Knuckle Sandwich Cigars with Erik Espinosa.",
        ),
        "espinosa_knuckle_sandwich_maduro" to listOf(
            "🇺🇸 Guy Fieri (USA) — Official celebrity co-creator and endorser of Knuckle Sandwich Cigars.",
        ),
        "espinosa_knuckle_sandwich_ct" to listOf(
            "🇺🇸 Guy Fieri (USA) — Official celebrity co-creator and endorser of Knuckle Sandwich Cigars.",
        ),
        "ci_la_aurora_preferidos_gold" to listOf(
            "🇺🇸 Karl Malone (USA) — NBA Hall of Famer who collaborated with La Aurora on his Barrel Aged signature cigar series.",
            "🇩🇴 David 'Big Papi' Ortiz (Dominican Republic) — Proud supporter of historic Santiago, Dominican Republic cigar makers.",
        ),
        "macanudo_cafe" to listOf(
            "🇺🇸 James Gandolfini (USA) — Frequently smoked Macanudo Café cigars on screen in The Sopranos.",
            "🇺🇸 Bill Murray (USA) — Beloved celebrity golf regular known for relaxed classic stogies on the links.",
            "🇺🇸 Morgan Freeman (USA) — Known for favoring smooth, mellow Macanudo classics.",
            "🇺🇸 Matthew McConaughey (USA) — Noted for enjoying mellow classic cigars during golf outings.",
            "🇺🇸 Whoopi Goldberg (USA) — Longtime fan of approachable, creamy mild-medium cigars.",
            "🇯🇲 Usain Bolt (Jamaica) — Sprint legend celebrated across Jamaican lounges where Macanudo was born.",
        ),
        "cao_brazilia" to listOf(
            "🇧🇷 Pelé & Ronaldinho (Brazil) — Brazilian football royalty celebrated alongside rich Brazilian Arapiraca tobacco heritage.",
            "🇺🇸 James Gandolfini (USA) — CAO Brazilia and Macanudo were famously smoked on screen in The Sopranos.",
        ),
        "liga_privada_9" to listOf(
            "🇺🇸 Joe Rogan (USA) — Frequently cites Drew Estate Liga Privada No. 9 as a go-to full-bodied smoke.",
            "🇺🇸 Snoop Dogg (USA) — Photographed enjoying bold boutique Drew Estate & luxury lounge cigars.",
        ),
        "alec_prensado" to listOf(
            "🇺🇸 Ron Perlman (USA) — Passionate cigar aficionado known for hearty box-pressed Honduran & Nicaraguan maduros.",
        ),
        "ci_casa_magna_colorado" to listOf(
            "🇩🇴 Manuel Quesada & Nestor Plasencia (Dominican Republic / Nicaragua) — Iconic master-blender alliance that won #1 Cigar of the Year 2008.",
        ),
        "la_aroma_original" to listOf(
            "🇬🇧 Sir Winston Churchill (UK) — Historical favorite of Churchill during the brand's original Havana era.",
        ),
        "ci_mark_twain_no1" to listOf(
            "🇺🇸 Mark Twain (USA) — Named in homage to the American author who famously quipped he smoked only one cigar at a time.",
        ),
        "rocky_patel_decade" to listOf(
            "🇺🇸 Charles Barkley & Samuel L. Jackson (USA) — Frequent celebrity supporters of Rocky Patel charity golf & Burn lounges.",
        ),
    )

    val brandCelebrities: Map<String, List<String>> = mapOf(
        "Cohiba" to listOf(
            "🇦🇷 Diego Maradona (Argentina), 🇬🇧 David Beckham (UK), 🇨🇦 Drake (Canada), 🇪🇸 Miguel Ángel Jiménez (Spain), 🇩🇪 Michael Schumacher (Germany), 🇭🇰 Jackie Chan (Hong Kong), 🇮🇳 Shah Rukh Khan (India), 🇳🇬 Burna Boy (Nigeria) & 🇺🇸 Jay-Z (USA) are famously associated with Cohiba.",
        ),
        "Montecristo" to listOf(
            "🇬🇧 Sir Roger Moore (UK), 🇮🇹 Carlo Ancelotti (Italy), 🇫🇷 Alain Delon (France), 🇪🇸 Antonio Banderas (Spain), 🇵🇹 José Mourinho (Portugal), 🇪🇬 Omar Sharif (Egypt), 🇿🇦 Ernie Els (South Africa), 🇳🇿 Russell Crowe (New Zealand/Australia) & 🇺🇸 Jack Nicholson (USA) are renowned Montecristo aficionados.",
        ),
        "Partagás" to listOf(
            "🇬🇧 Jeremy Irons (UK), 🇫🇷 Gérard Depardieu (France), 🇪🇸 Javier Bardem (Spain), 🇵🇷 Benicio del Toro (Puerto Rico), 🇦🇹 Arnold Schwarzenegger (Austria/USA) & 🇺🇸 Michael Jordan (USA) are documented Partagás admirers.",
        ),
        "Romeo y Julieta" to listOf(
            "🇬🇧 Sir Winston Churchill (UK), 🇬🇧 Sir Elton John (UK), 🇬🇧 Sir Michael Caine (UK), 🇮🇪 Pierce Brosnan (Ireland/UK), 🇦🇺 Hugh Jackman (Australia) & 🇺🇸 Anthony Bourdain (USA) are celebrated Romeo y Julieta enthusiasts.",
        ),
        "Davidoff" to listOf(
            "🇬🇧 Sir Winston Churchill (UK), 🏴󠁧󠁢󠁳󠁣󠁴󠁿 Sir Sean Connery (Scotland), 🇬🇧 Idris Elba (UK), 🇬🇧 Gordon Ramsay (UK), 🇬🇧 Sir Lewis Hamilton (UK), 🇯🇵 Ken Watanabe (Japan), 🇰🇷 Lee Byung-hun (South Korea), 🇮🇹 Luciano Pavarotti (Italy) & 🇨🇭 Zino Davidoff (Ukraine/Switzerland) define Davidoff's global prestige.",
        ),
        "Hoyo de Monterrey" to listOf(
            "🇪🇸 Julio Iglesias (Spain), 🇦🇺 Greg Norman (Australia), 🇦🇹 Arnold Schwarzenegger (Austria/USA), 🇺🇸 Red Auerbach (USA) & 🇺🇸 Michael Jordan (USA) are longtime Hoyo de Monterrey fans.",
        ),
        "H. Upmann" to listOf(
            "🇺🇸 John F. Kennedy (USA), 🇬🇧 Sir Roger Moore (UK) & 🇮🇪 Colin Farrell (Ireland) helped cement H. Upmann's diplomatic and cinematic legacy.",
        ),
        "Trinidad" to listOf(
            "🇪🇸 Miguel Ángel Jiménez (Spain), 🇫🇷 Jean Reno (France) & 🇸🇪 Zlatan Ibrahimović (Sweden) popularized Trinidad worldwide.",
        ),
        "Bolívar" to listOf(
            "🇵🇷 Benicio del Toro (Puerto Rico), 🇬🇧 Tom Hardy (UK) & 🇺🇸 Al Pacino (USA) are drawn to bold Cuban Bolívar character.",
        ),
        "Ramón Allones" to listOf(
            "🇬🇧 Jeremy Irons (UK) & 🇮🇹 Carlo Ancelotti (Italy) represent the European connoisseur following for Ramón Allones.",
        ),
        "Padrón" to listOf(
            "🇮🇪 Conor McGregor (Ireland), 🇲🇽 Canelo Álvarez (Mexico), 🇩🇪 Dirk Nowitzki (Germany), 🇵🇭 Manny Pacquiao (Philippines), 🇺🇸 Michael Jordan, Joe Rogan, Steve Harvey, Samuel L. Jackson & Dave Chappelle frequently enjoy Padrón blends.",
        ),
        "Arturo Fuente" to listOf(
            "🇨🇺 Andy Garcia (Cuba/USA), 🇩🇴 David 'Big Papi' Ortiz (Dominican Republic), 🇨🇦 Wayne Gretzky (Canada), 🇬🇷 Giannis Antetokounmpo (Greece), 🇵🇦 Mariano Rivera (Panama), 🇺🇸 Sylvester Stallone, Derek Jeter & Robert De Niro are longtime Arturo Fuente admirers.",
        ),
        "My Father" to listOf(
            "🇻🇪 Miguel Cabrera (Venezuela) & 🇺🇸 Rick Ross (USA) highlight the Garcia family's acclaimed Nicaraguan house.",
        ),
        "Ashton" to listOf(
            "🇺🇸 Post Malone (USA) & classic lounge connoisseurs favor Ashton's silky Dominican craftsmanship.",
        ),
        "Alec Bradley" to listOf(
            "🇺🇸 Ron Perlman (USA) & boutique lounge aficionados appreciate bold Honduran & Nicaraguan Alec Bradley blends.",
        ),
        "J.C. Newman" to listOf(
            "🇺🇸 Babe Ruth & George Burns (USA) represent America's golden-age Tampa cigar heritage still kept alive by J.C. Newman.",
        ),
        "Casa Turrent" to listOf(
            "🇲🇽 Canelo Álvarez (Mexico) & Alejandro Turrent (Mexico) — Flagship representation of proud Mexican San Andrés tobacco heritage.",
        ),
        "Toscano" to listOf(
            "🇮🇹 Sophia Loren (Italy), 🇮🇹 Andrea Bocelli (Italy) & 🇺🇸 Clint Eastwood (USA) immortalized Italian fire-cured Toscano cheroots on screen and across Tuscany.",
        ),
        "AVO" to listOf(
            "🇦🇲 Avo Uvezian (Armenia/Lebanon) — Founded by the internationally acclaimed jazz pianist, composer, and tobacconist.",
        ),
        "Camacho" to listOf(
            "🇺🇸 Mike Ditka (USA) — NFL Hall of Fame coach who partnered on signature bold Honduran Corojo releases.",
        ),
        "Oliva" to listOf(
            "🇺🇸 Charles Barkley (USA) & 🇷🇸 Nikola Jokić (Serbia) — Popular Nicaraguan celebration cigars across pro basketball.",
        ),
        "La Aroma de Cuba" to listOf(
            "🇬🇧 Sir Winston Churchill (UK) favored La Aroma de Cuba during its historic Havana era.",
        ),
        "La Aurora" to listOf(
            "🇺🇸 Karl Malone (USA) & 🇩🇴 David 'Big Papi' Ortiz (Dominican Republic) — Celebrated ties to Santiago's oldest cigar factory (1903).",
        ),
        "CAO" to listOf(
            "🇧🇷 Pelé & Ronaldinho (Brazil) celebrate Brazilian Arapiraca heritage; 🇺🇸 James Gandolfini (USA) famously smoked CAO on screen in The Sopranos.",
        ),
        "Macanudo" to listOf(
            "🇯🇲 Usain Bolt (Jamaica) & 🇺🇸 James Gandolfini, Bill Murray, Morgan Freeman, Matthew McConaughey & Whoopi Goldberg (USA) popularized smooth Macanudo classics.",
        ),
        "Drew Estate" to listOf(
            "🇺🇸 Joe Rogan & Snoop Dogg (USA) frequently highlight bold Drew Estate creations.",
        ),
        "Espinosa" to listOf(
            "🇺🇸 Guy Fieri (USA) partnered with Erik Espinosa on the 94-rated Knuckle Sandwich cigar line.",
        ),
        "Rocky Patel" to listOf(
            "🇺🇸 Charles Barkley & Samuel L. Jackson (USA) are frequent celebrity guests at Rocky Patel charity & Burn lounge events.",
        ),
        "Mark Twain" to listOf(
            "🇺🇸 Mark Twain (USA) — Named in tribute to the famed American author and lifelong cigar enthusiast.",
        ),
        "Por Larrañaga" to listOf(
            "🇬🇧 Rudyard Kipling (UK/India) famously celebrated Por Larrañaga cigars in his Victorian poetry.",
        ),
        "Punch" to listOf(
            "🇦🇹 Arnold Schwarzenegger (Austria/USA) & classic British club culture made Punch a global staple since 1840.",
        ),
        "Gurkha" to listOf(
            "🇺🇸 Matthew McConaughey (USA) & international luxury collectors have been spotted with limited-edition Gurkha releases.",
        ),
        "Plasencia" to listOf(
            "🇳🇮 Nestor Plasencia (Nicaragua) & Latin American gastronomy icons champion Plasencia's five-generation organic estates.",
        ),
        "Joya de Nicaragua" to listOf(
            "🇳🇮 Historic official Nicaraguan diplomatic cigar gifted to international heads of state since 1968.",
        ),
    )

    fun isInternationalNote(note: String): Boolean =
        internationalMarkers.any { marker -> note.contains(marker, ignoreCase = true) }

    fun hasInternationalCelebrity(notes: List<String>): Boolean =
        notes.any { isInternationalNote(it) }

    /**
     * Resolves structured CelebrityProfiles associated with a cigar's celebrity notes or brand
     * so UI screens can render cartoon portraits right beside each celebrity mention.
     */
    fun profilesForNotes(notes: List<String>, brand: String): List<CelebrityProfile> {
        if (notes.isEmpty()) return emptyList()
        val combined = notes.joinToString(" ").lowercase()
        val matched = allCelebrityProfiles.filter { profile ->
            val lowerName = profile.name.lowercase()
            val lastWord = lowerName.substringAfterLast(" ")
            combined.contains(lowerName) ||
                (lastWord.length >= 6 && combined.contains(lastWord))
        }
        if (matched.isNotEmpty()) return matched.distinctBy { it.id }
        return allCelebrityProfiles.filter { it.favoriteBrand.equals(brand, ignoreCase = true) }.take(3)
    }

    fun searchProfiles(query: String, onlyInternational: Boolean): List<CelebrityProfile> {
        val q = query.trim().lowercase()
        return allCelebrityProfiles.filter { p ->
            val matchesScope = !onlyInternational || p.isInternational
            val matchesQuery = q.isEmpty() ||
                p.name.lowercase().contains(q) ||
                p.country.lowercase().contains(q) ||
                p.favoriteBrand.lowercase().contains(q) ||
                p.roleTag.lowercase().contains(q) ||
                p.note.lowercase().contains(q)
            matchesScope && matchesQuery
        }
    }

    /**
     * Returns an iconic mix of local US and worldwide celebrities across all 5 cartoon archetypes
     * for the Discover screen Celebrity Cartoon Lounge spotlight.
     */
    fun featuredSpotlightProfiles(): List<CelebrityProfile> {
        val spotlightIds = listOf(
            "winston_churchill",
            "michael_jordan",
            "arnold_schwarzenegger",
            "guy_fieri",
            "diego_maradona",
            "idris_elba",
            "bad_bunny",
            "gordon_ramsay",
            "carlo_ancelotti",
            "jay_z",
            "ken_watanabe",
            "frank_sinatra",
            "burna_boy",
            "canelo_alvarez",
            "shaquille_oneal",
            "rihanna",
        )
        val byId = allCelebrityProfiles.associateBy { it.id }
        return spotlightIds.mapNotNull { byId[it] }
    }
}
