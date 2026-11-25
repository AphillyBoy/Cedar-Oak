package com.example.data.catalog

import com.example.model.CartoonTraits
import com.example.model.CelebrityProfile
import com.example.model.EyeColor
import com.example.model.FacialHair
import com.example.model.GlassesStyle
import com.example.model.HairColor
import com.example.model.HairStyle
import com.example.model.Headwear
import com.example.model.OutfitStyle
import com.example.model.PortraitGender
import com.example.model.SkinTone

/**
 * Individually researched cartoon appearance traits for every celebrity in CigarCelebrityDirectory,
 * ensuring accurate skin tone, gender, hair, eye color, headwear, glasses, and attire.
 */
object CelebrityCartoonTraitsMap {

    private val byCelebrityId: Map<String, CartoonTraits> = mapOf(
        // --- UK, Ireland & Europe ---
        "winston_churchill" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.RECEDING_SILVER, HairColor.WHITE,
            FacialHair.NONE, EyeColor.BLUE, Headwear.BOWLER, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
        "arnold_schwarzenegger" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SHORT_PART, HairColor.BROWN,
            FacialHair.NONE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.SUIT_TIE,
        ),
        "roger_moore" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.BROWN,
            FacialHair.NONE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
        "sean_connery" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.RECEDING_SILVER, HairColor.WHITE,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
        "idris_elba" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BUZZ, HairColor.BLACK,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "gordon_ramsay" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SPIKY, HairColor.BLONDE,
            FacialHair.NONE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.CHEF_COAT,
        ),
        "david_beckham" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SLICK_BACK, HairColor.BLONDE,
            FacialHair.STUBBLE, EyeColor.HAZEL, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "lewis_hamilton" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.BROWN, HairStyle.LOCS_BRAIDS, HairColor.BLACK,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.ROCK_JACKET,
        ),
        "jeremy_irons" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SLICK_BACK, HairColor.GRAY,
            FacialHair.GOATEE, EyeColor.GREEN, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "elton_john" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.BROWN,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.CLASSIC_ROUND, OutfitStyle.ROCK_JACKET,
        ),
        "tom_hardy" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.BUZZ, HairColor.DARK_BROWN,
            FacialHair.FULL_BEARD, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.ROCK_JACKET,
        ),
        "michael_caine" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.GRAY,
            FacialHair.NONE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.SQUARE_FRAMES, OutfitStyle.SUIT_TIE,
        ),
        "conor_mcgregor" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SLICK_BACK, HairColor.AUBURN,
            FacialHair.FULL_BEARD, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "pierce_brosnan" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SLICK_BACK, HairColor.GRAY,
            FacialHair.STUBBLE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
        "colin_farrell" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SLICK_BACK, HairColor.DARK_BROWN,
            FacialHair.STUBBLE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.ROCK_JACKET,
        ),
        "zinedine_zidane" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.BALD, HairColor.BLACK,
            FacialHair.STUBBLE, EyeColor.GREEN, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "alain_delon" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SLICK_BACK, HairColor.DARK_BROWN,
            FacialHair.NONE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.SUIT_TIE,
        ),
        "gerard_depardieu" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.LONG_WAVY, HairColor.BLONDE,
            FacialHair.NONE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.CASUAL_POLO,
        ),
        "jean_reno" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.SHORT_PART, HairColor.GRAY,
            FacialHair.STUBBLE, EyeColor.DARK, Headwear.NONE, GlassesStyle.CLASSIC_ROUND, OutfitStyle.SUIT_TIE,
        ),
        "carlo_ancelotti" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.SHORT_PART, HairColor.WHITE,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.SUIT_TIE,
        ),
        "luciano_pavarotti" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.SLICK_BACK, HairColor.BLACK,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
        "andrea_bocelli" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.CURLY, HairColor.DARK_BROWN,
            FacialHair.STUBBLE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
        "sophia_loren" to CartoonTraits(
            PortraitGender.FEMININE, SkinTone.OLIVE, HairStyle.LONG_WAVY, HairColor.DARK_BROWN,
            FacialHair.NONE, EyeColor.HAZEL, Headwear.NONE, GlassesStyle.CAT_EYE, OutfitStyle.TUXEDO,
        ),
        "miguel_angel_jimenez" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.CURLY, HairColor.AUBURN,
            FacialHair.STUBBLE, EyeColor.DARK, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.CASUAL_POLO,
        ),
        "antonio_banderas" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.SLICK_BACK, HairColor.DARK_BROWN,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "javier_bardem" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.CURLY, HairColor.DARK_BROWN,
            FacialHair.STUBBLE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.ROCK_JACKET,
        ),
        "julio_iglesias" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.TAN, HairStyle.SLICK_BACK, HairColor.DARK_BROWN,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
        "michael_schumacher" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.BROWN,
            FacialHair.NONE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "dirk_nowitzki" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.BLONDE,
            FacialHair.NONE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "jose_mourinho" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.SHORT_PART, HairColor.GRAY,
            FacialHair.STUBBLE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "giannis_antetokounmpo" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BUZZ, HairColor.BLACK,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "zlatan_ibrahimovic" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SLICK_BACK, HairColor.DARK_BROWN,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "nikola_jokic" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.BUZZ, HairColor.BROWN,
            FacialHair.STUBBLE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "mads_mikkelsen" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SLICK_BACK, HairColor.GRAY,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "jean_claude_van_damme" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SLICK_BACK, HairColor.BROWN,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.ROCK_JACKET,
        ),
        "zino_davidoff" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.RECEDING_SILVER, HairColor.WHITE,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.SQUARE_FRAMES, OutfitStyle.SUIT_TIE,
        ),
        "avo_uvezian" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.RECEDING_SILVER, HairColor.WHITE,
            FacialHair.MUSTACHE, EyeColor.DARK, Headwear.FEDORA, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),

        // --- Latin America, Caribbean & Canada ---
        "diego_maradona" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.CURLY, HairColor.BLACK,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "pele" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BUZZ, HairColor.BLACK,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "ronaldinho" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.BROWN, HairStyle.LONG_WAVY, HairColor.BLACK,
            FacialHair.NONE, EyeColor.DARK, Headwear.BASEBALL_CAP, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "canelo_alvarez" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.AUBURN,
            FacialHair.FULL_BEARD, EyeColor.HAZEL, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "david_ortiz" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BUZZ, HairColor.BLACK,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.BASEBALL_CAP, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "sammy_sosa" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.BROWN, HairStyle.BUZZ, HairColor.BLACK,
            FacialHair.NONE, EyeColor.DARK, Headwear.BASEBALL_CAP, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "mariano_rivera" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.BROWN, HairStyle.BUZZ, HairColor.BLACK,
            FacialHair.NONE, EyeColor.DARK, Headwear.BASEBALL_CAP, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "miguel_cabrera" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.TAN, HairStyle.BUZZ, HairColor.BLACK,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.BASEBALL_CAP, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "benicio_del_toro" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.SLICK_BACK, HairColor.DARK_BROWN,
            FacialHair.STUBBLE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "andy_garcia" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.SLICK_BACK, HairColor.DARK_BROWN,
            FacialHair.NONE, EyeColor.DARK, Headwear.FEDORA, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "rihanna" to CartoonTraits(
            PortraitGender.FEMININE, SkinTone.BROWN, HairStyle.LONG_WAVY, HairColor.BLACK,
            FacialHair.NONE, EyeColor.GREEN, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.ROCK_JACKET,
        ),
        "usain_bolt" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BUZZ, HairColor.BLACK,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "sofia_vergara" to CartoonTraits(
            PortraitGender.FEMININE, SkinTone.OLIVE, HairStyle.LONG_WAVY, HairColor.BROWN,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
        "drake" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.TAN, HairStyle.BUZZ, HairColor.BLACK,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.ROCK_JACKET,
        ),
        "wayne_gretzky" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.BROWN,
            FacialHair.NONE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),

        // --- Asia, Middle East, Africa & Oceania ---
        "jackie_chan" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SHORT_PART, HairColor.BLACK,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.CASUAL_POLO,
        ),
        "shah_rukh_khan" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.TAN, HairStyle.SLICK_BACK, HairColor.BLACK,
            FacialHair.STUBBLE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
        "ken_watanabe" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SHORT_PART, HairColor.BLACK,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "lee_byung_hun" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SLICK_BACK, HairColor.BLACK,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "manny_pacquiao" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.TAN, HairStyle.SHORT_PART, HairColor.BLACK,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "burna_boy" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.LOCS_BRAIDS, HairColor.BLACK,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.ROCK_JACKET,
        ),
        "ernie_els" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.BROWN,
            FacialHair.NONE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.CASUAL_POLO,
        ),
        "omar_sharif" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.SLICK_BACK, HairColor.DARK_BROWN,
            FacialHair.MUSTACHE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
        "hugh_jackman" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SHORT_PART, HairColor.DARK_BROWN,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
        "russell_crowe" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.GRAY,
            FacialHair.FULL_BEARD, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "greg_norman" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.WHITE,
            FacialHair.NONE, EyeColor.BLUE, Headwear.COWBOY_HAT, GlassesStyle.NONE, OutfitStyle.CASUAL_POLO,
        ),

        // --- Local / United States Icons ---
        "michael_jordan" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BALD, HairColor.BLACK,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "jack_nicholson" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.RECEDING_SILVER, HairColor.GRAY,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.SUIT_TIE,
        ),
        "jay_z" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.LOCS_BRAIDS, HairColor.BLACK,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.TUXEDO,
        ),
        "guy_fieri" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SPIKY, HairColor.BLEACHED,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.CHEF_COAT,
        ),
        "sylvester_stallone" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.SLICK_BACK, HairColor.DARK_BROWN,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.ROCK_JACKET,
        ),
        "steve_harvey" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BALD, HairColor.BLACK,
            FacialHair.WALRUS_STACHE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "joe_rogan" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.BALD, HairColor.DARK_BROWN,
            FacialHair.STUBBLE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.CASUAL_POLO,
        ),
        "dave_chappelle" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BALD, HairColor.BLACK,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.CASUAL_POLO,
        ),
        "james_gandolfini" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.RECEDING_SILVER, HairColor.DARK_BROWN,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.CASUAL_POLO,
        ),
        "snoop_dogg" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.LOCS_BRAIDS, HairColor.BLACK,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.ROCK_JACKET,
        ),
        "charles_barkley" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BALD, HairColor.BLACK,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "tom_brady" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.BROWN,
            FacialHair.NONE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "lebron_james" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BUZZ, HairColor.BLACK,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "red_auerbach" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.RECEDING_SILVER, HairColor.GRAY,
            FacialHair.NONE, EyeColor.DARK, Headwear.FEDORA, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "derek_jeter" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.TAN, HairStyle.BUZZ, HairColor.BLACK,
            FacialHair.NONE, EyeColor.GREEN, Headwear.BASEBALL_CAP, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "post_malone" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.CURLY, HairColor.BROWN,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.ROCK_JACKET,
        ),
        "rick_ross" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BUZZ, HairColor.BLACK,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.ROCK_JACKET,
        ),
        "bill_murray" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.RECEDING_SILVER, HairColor.GRAY,
            FacialHair.NONE, EyeColor.BLUE, Headwear.FLAT_CAP, GlassesStyle.NONE, OutfitStyle.CASUAL_POLO,
        ),
        "morgan_freeman" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.SHORT_PART, HairColor.GRAY,
            FacialHair.MUSTACHE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "robert_de_niro" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SLICK_BACK, HairColor.GRAY,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "al_pacino" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.SLICK_BACK, HairColor.DARK_BROWN,
            FacialHair.STUBBLE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "samuel_l_jackson" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BALD, HairColor.BLACK,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.FLAT_CAP, GlassesStyle.SQUARE_FRAMES, OutfitStyle.CASUAL_POLO,
        ),
        "matthew_mcconaughey" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.CURLY, HairColor.BROWN,
            FacialHair.STUBBLE, EyeColor.GREEN, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.CASUAL_POLO,
        ),
        "ron_perlman" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SLICK_BACK, HairColor.GRAY,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.ROCK_JACKET,
        ),
        "groucho_marx" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.BLACK,
            FacialHair.WALRUS_STACHE, EyeColor.DARK, Headwear.NONE, GlassesStyle.CLASSIC_ROUND, OutfitStyle.TUXEDO,
        ),
        "george_burns" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.GRAY,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.SQUARE_FRAMES, OutfitStyle.SUIT_TIE,
        ),
        "ernest_hemingway" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SHORT_PART, HairColor.WHITE,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.CASUAL_POLO,
        ),
        "mark_twain" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.CURLY, HairColor.WHITE,
            FacialHair.WALRUS_STACHE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "john_f_kennedy" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.BROWN,
            FacialHair.NONE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "babe_ruth" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SHORT_PART, HairColor.DARK_BROWN,
            FacialHair.NONE, EyeColor.DARK, Headwear.BASEBALL_CAP, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "clint_eastwood" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.GRAY,
            FacialHair.STUBBLE, EyeColor.BLUE, Headwear.COWBOY_HAT, GlassesStyle.NONE, OutfitStyle.ROCK_JACKET,
        ),
        "anthony_bourdain" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.CURLY, HairColor.GRAY,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.CHEF_COAT,
        ),
        "demi_moore" to CartoonTraits(
            PortraitGender.FEMININE, SkinTone.FAIR, HairStyle.LONG_WAVY, HairColor.BLACK,
            FacialHair.NONE, EyeColor.GREEN, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
        "whoopi_goldberg" to CartoonTraits(
            PortraitGender.FEMININE, SkinTone.DEEP, HairStyle.LOCS_BRAIDS, HairColor.BLACK,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.CLASSIC_ROUND, OutfitStyle.CASUAL_POLO,
        ),
        "karl_malone" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BALD, HairColor.BLACK,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.COWBOY_HAT, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "mike_ditka" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.GRAY,
            FacialHair.WALRUS_STACHE, EyeColor.DARK, Headwear.NONE, GlassesStyle.AVIATOR, OutfitStyle.CASUAL_POLO,
        ),
        "francis_ford_coppola" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.SHORT_PART, HairColor.GRAY,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.SQUARE_FRAMES, OutfitStyle.SUIT_TIE,
        ),
        "danny_devito" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.RECEDING_SILVER, HairColor.GRAY,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.CLASSIC_ROUND, OutfitStyle.SUIT_TIE,
        ),

        // --- Expanded Global Icons ---
        "daniel_craig" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.BLONDE,
            FacialHair.NONE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
        "liam_neeson" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.GRAY,
            FacialHair.NONE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "serge_gainsbourg" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.CURLY, HairColor.DARK_BROWN,
            FacialHair.STUBBLE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.ROCK_JACKET,
        ),
        "federico_fellini" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.RECEDING_SILVER, HairColor.GRAY,
            FacialHair.NONE, EyeColor.DARK, Headwear.FEDORA, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "gianluigi_buffon" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.SLICK_BACK, HairColor.DARK_BROWN,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "roberto_baggio" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.CURLY, HairColor.DARK_BROWN,
            FacialHair.GOATEE, EyeColor.GREEN, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "penelope_cruz" to CartoonTraits(
            PortraitGender.FEMININE, SkinTone.OLIVE, HairStyle.LONG_WAVY, HairColor.DARK_BROWN,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
        "rafael_nadal" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.OLIVE, HairStyle.SHORT_PART, HairColor.DARK_BROWN,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.CASUAL_POLO,
        ),
        "jurgen_klopp" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.BROWN,
            FacialHair.FULL_BEARD, EyeColor.BLUE, Headwear.BASEBALL_CAP, GlassesStyle.SQUARE_FRAMES, OutfitStyle.CASUAL_POLO,
        ),
        "ruud_gullit" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.BROWN, HairStyle.LOCS_BRAIDS, HairColor.BLACK,
            FacialHair.MUSTACHE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "gary_player" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.WHITE,
            FacialHair.NONE, EyeColor.BLUE, Headwear.FLAT_CAP, GlassesStyle.NONE, OutfitStyle.CASUAL_POLO,
        ),
        "takeshi_kitano" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.BUZZ, HairColor.GRAY,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.SUIT_TIE,
        ),
        "neymar_jr" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.TAN, HairStyle.SPIKY, HairColor.DARK_BROWN,
            FacialHair.STUBBLE, EyeColor.DARK, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.JERSEY_SPORT,
        ),
        "carlos_tevez" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.TAN, HairStyle.SHORT_PART, HairColor.BLACK,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "carlos_valderrama" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.TAN, HairStyle.CURLY, HairColor.BLEACHED,
            FacialHair.WALRUS_STACHE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.JERSEY_SPORT,
        ),
        "bad_bunny" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.TAN, HairStyle.CURLY, HairColor.DARK_BROWN,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.NONE, GlassesStyle.SUNGLASSES, OutfitStyle.ROCK_JACKET,
        ),
        "celia_cruz" to CartoonTraits(
            PortraitGender.FEMININE, SkinTone.DEEP, HairStyle.UPDO_GLAM, HairColor.BLONDE,
            FacialHair.NONE, EyeColor.DARK, Headwear.HEADWRAP, GlassesStyle.NONE, OutfitStyle.ROCK_JACKET,
        ),
        "guillermo_del_toro" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SHORT_PART, HairColor.GRAY,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.CLASSIC_ROUND, OutfitStyle.SUIT_TIE,
        ),
        "novak_djokovic" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SHORT_PART, HairColor.DARK_BROWN,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.CASUAL_POLO,
        ),
        "salma_hayek" to CartoonTraits(
            PortraitGender.FEMININE, SkinTone.OLIVE, HairStyle.LONG_WAVY, HairColor.DARK_BROWN,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),

        // --- Expanded Local / United States Icons ---
        "shaquille_oneal" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BALD, HairColor.BLACK,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "john_wayne" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.BROWN,
            FacialHair.NONE, EyeColor.BLUE, Headwear.COWBOY_HAT, GlassesStyle.NONE, OutfitStyle.CASUAL_POLO,
        ),
        "orson_welles" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SLICK_BACK, HairColor.GRAY,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
        "fifty_cent" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BUZZ, HairColor.BLACK,
            FacialHair.GOATEE, EyeColor.DARK, Headwear.BASEBALL_CAP, GlassesStyle.NONE, OutfitStyle.ROCK_JACKET,
        ),
        "bruno_mars" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.TAN, HairStyle.CURLY, HairColor.BLACK,
            FacialHair.MUSTACHE, EyeColor.DARK, Headwear.FEDORA, GlassesStyle.SUNGLASSES, OutfitStyle.ROCK_JACKET,
        ),
        "kevin_hart" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.DEEP, HairStyle.BUZZ, HairColor.BLACK,
            FacialHair.FULL_BEARD, EyeColor.DARK, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.CASUAL_POLO,
        ),
        "bruce_willis" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.BALD, HairColor.GRAY,
            FacialHair.STUBBLE, EyeColor.BLUE, Headwear.NONE, GlassesStyle.NONE, OutfitStyle.ROCK_JACKET,
        ),
        "ulysses_s_grant" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.FAIR, HairStyle.SHORT_PART, HairColor.BROWN,
            FacialHair.FULL_BEARD, EyeColor.BLUE, Headwear.FEDORA, GlassesStyle.NONE, OutfitStyle.SUIT_TIE,
        ),
        "john_mayer" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SLICK_BACK, HairColor.DARK_BROWN,
            FacialHair.NONE, EyeColor.DARK, Headwear.NONE, GlassesStyle.CLASSIC_ROUND, OutfitStyle.ROCK_JACKET,
        ),
        "frank_sinatra" to CartoonTraits(
            PortraitGender.MASCULINE, SkinTone.LIGHT, HairStyle.SLICK_BACK, HairColor.DARK_BROWN,
            FacialHair.NONE, EyeColor.BLUE, Headwear.FEDORA, GlassesStyle.NONE, OutfitStyle.TUXEDO,
        ),
    )

    fun traitsFor(profile: CelebrityProfile): CartoonTraits =
        byCelebrityId[profile.id] ?: profile.traits

    fun hasExplicitMapping(profileId: String): Boolean = profileId in byCelebrityId
}
