package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.catalog.CelebrityCartoonTraitsMap
import com.example.data.catalog.CigarCelebrityDirectory
import com.example.model.CartoonTraits
import com.example.model.CelebrityProfile
import com.example.model.FacialHair
import com.example.model.GlassesStyle
import com.example.model.HairStyle
import com.example.model.Headwear
import com.example.model.OutfitStyle
import com.example.model.PortraitGender

/**
 * Custom editorial vector cartoon portrait matched accurately to each celebrity's real appearance
 * (skin tone, gender, hair style & color, eye color, facial hair, eyewear, headwear, and attire).
 */
@Composable
fun CelebrityCartoonAvatar(
    profile: CelebrityProfile,
    size: Dp = 62.dp,
    modifier: Modifier = Modifier,
) {
    val accentColor = Color(profile.accentHex)
    val traits = remember(profile.id) { CelebrityCartoonTraitsMap.traitsFor(profile) }

    Box(
        modifier = modifier
            .size(size)
            .semantics {
                contentDescription = "Cartoon portrait for ${profile.name}"
            },
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .border(2.5.dp, accentColor, CircleShape)
                .background(Color(0xFF1F1612)),
        ) {
            drawCartoonPortrait(traits = traits, accentColor = accentColor)
        }

        // Country flag emoji badge on bottom-right corner
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceContainerHighest,
            tonalElevation = 2.dp,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .border(1.dp, accentColor, CircleShape),
        ) {
            Text(
                text = profile.flagEmoji,
                fontSize = (size.value * 0.22f).coerceIn(11f, 15f).sp,
                modifier = Modifier.padding(horizontal = 3.dp, vertical = 1.dp),
            )
        }
    }
}

private fun DrawScope.drawCartoonPortrait(
    traits: CartoonTraits,
    accentColor: Color,
) {
    val w = size.width
    val h = size.height
    val cx = w * 0.50f
    val ink = Color(0xFF1A120D)
    val strokeW = (w * 0.024f).coerceAtLeast(1.2f)

    val skinBase = Color(traits.skinTone.baseHex)
    val skinShadow = Color(traits.skinTone.shadowHex)
    val hairCol = Color(traits.hairColor.hex)
    val eyeCol = Color(traits.eyeColor.hex)
    val isFem = traits.gender == PortraitGender.FEMININE

    // 1. Warm comic lounge radial backdrop
    drawRect(
        brush = Brush.radialGradient(
            colors = listOf(
                accentColor.copy(alpha = 0.52f),
                Color(0xFF2B1E18),
                Color(0xFF16100D),
            ),
            center = Offset(cx, h * 0.42f),
            radius = w * 0.65f,
        ),
    )

    // 2. Back hair layer for long wavy / locs / glam styles
    when (traits.hairStyle) {
        HairStyle.LONG_WAVY -> {
            val backHair = Path().apply {
                moveTo(cx - w * 0.28f, h * 0.36f)
                cubicTo(cx - w * 0.38f, h * 0.62f, cx - w * 0.34f, h * 0.88f, cx - w * 0.20f, h * 0.96f)
                lineTo(cx + w * 0.20f, h * 0.96f)
                cubicTo(cx + w * 0.34f, h * 0.88f, cx + w * 0.38f, h * 0.62f, cx + w * 0.28f, h * 0.36f)
                close()
            }
            drawPath(backHair, color = hairCol)
            drawPath(backHair, color = ink, style = Stroke(strokeW))
        }
        HairStyle.LOCS_BRAIDS -> {
            for (offsetFactor in listOf(-0.26f, -0.20f, 0.20f, 0.26f)) {
                drawRoundRect(
                    color = hairCol,
                    topLeft = Offset(cx + w * offsetFactor - w * 0.04f, h * 0.34f),
                    size = Size(w * 0.08f, h * 0.46f),
                    cornerRadius = CornerRadius(w * 0.04f, w * 0.04f),
                )
            }
        }
        HairStyle.UPDO_GLAM -> {
            drawCircle(
                color = hairCol,
                radius = w * 0.20f,
                center = Offset(cx, h * 0.22f),
            )
            drawCircle(
                color = ink,
                radius = w * 0.20f,
                center = Offset(cx, h * 0.22f),
                style = Stroke(strokeW),
            )
        }
        else -> Unit
    }

    // 3. Shoulders & Attire
    drawCartoonOutfit(traits = traits, accentColor = accentColor, ink = ink, strokeW = strokeW)

    // 4. Neck
    val neckW = if (isFem) w * 0.16f else w * 0.20f
    drawRoundRect(
        color = skinShadow,
        topLeft = Offset(cx - neckW / 2f, h * 0.62f),
        size = Size(neckW, h * 0.18f),
        cornerRadius = CornerRadius(w * 0.05f, w * 0.05f),
    )

    // 5. Ears
    val earY = h * 0.48f
    val earRx = w * 0.055f
    val earRy = h * 0.07f
    val headHalfW = if (isFem) w * 0.22f else w * 0.24f
    drawOval(
        color = skinShadow,
        topLeft = Offset(cx - headHalfW - earRx * 0.7f, earY - earRy),
        size = Size(earRx * 2f, earRy * 2f),
    )
    drawOval(
        color = skinShadow,
        topLeft = Offset(cx + headHalfW - earRx * 1.3f, earY - earRy),
        size = Size(earRx * 2f, earRy * 2f),
    )
    // Glam gold earrings for feminine portraits
    if (isFem) {
        drawCircle(Color(0xFFF6D365), radius = w * 0.025f, center = Offset(cx - headHalfW - w * 0.01f, earY + earRy * 0.85f))
        drawCircle(Color(0xFFF6D365), radius = w * 0.025f, center = Offset(cx + headHalfW + w * 0.01f, earY + earRy * 0.85f))
    }

    // 6. Head Shape
    val headTop = h * 0.22f
    val headH = h * 0.46f
    drawOval(
        color = skinBase,
        topLeft = Offset(cx - headHalfW, headTop),
        size = Size(headHalfW * 2f, headH),
    )
    // Subtle cheek blush/dimension
    drawOval(
        color = skinShadow.copy(alpha = 0.28f),
        topLeft = Offset(cx - headHalfW * 0.88f, headTop + headH * 0.56f),
        size = Size(headHalfW * 1.76f, headH * 0.42f),
    )
    drawOval(
        color = ink,
        topLeft = Offset(cx - headHalfW, headTop),
        size = Size(headHalfW * 2f, headH),
        style = Stroke(strokeW),
    )

    // 7. Facial Hair (drawn before mouth/nose or layered naturally)
    if (!isFem) {
        drawCartoonFacialHair(
            facialHair = traits.facialHair,
            hairCol = hairCol,
            cx = cx,
            headTop = headTop,
            headH = headH,
            headHalfW = headHalfW,
        )
    }

    // 8. Eyes & Eyebrows
    val eyeY = headTop + headH * 0.46f
    val eyeOffsetX = headHalfW * 0.40f
    val eyeR = w * 0.036f

    // Sclera + Iris + Pupil + Specular glint
    for (sign in listOf(-1f, 1f)) {
        val ex = cx + sign * eyeOffsetX
        drawCircle(Color.White, radius = eyeR * 1.15f, center = Offset(ex, eyeY))
        drawCircle(eyeCol, radius = eyeR * 0.78f, center = Offset(ex, eyeY))
        drawCircle(ink, radius = eyeR * 0.42f, center = Offset(ex, eyeY))
        drawCircle(Color.White, radius = eyeR * 0.24f, center = Offset(ex - eyeR * 0.28f, eyeY - eyeR * 0.28f))

        if (isFem) {
            // Subtle comic eyelashes
            drawLine(
                color = ink,
                start = Offset(ex + sign * eyeR * 0.9f, eyeY - eyeR * 0.6f),
                end = Offset(ex + sign * eyeR * 1.5f, eyeY - eyeR * 1.1f),
                strokeWidth = strokeW * 0.8f,
                cap = StrokeCap.Round,
            )
        }

        // Eyebrow
        val browY = eyeY - eyeR * 1.9f
        val browStroke = if (isFem) strokeW * 0.95f else strokeW * 1.55f
        drawLine(
            color = hairCol,
            start = Offset(ex - eyeR * 1.1f, browY + (if (sign < 0) 0f else -w * 0.008f)),
            end = Offset(ex + eyeR * 1.1f, browY + (if (sign > 0) 0f else -w * 0.008f)),
            strokeWidth = browStroke,
            cap = StrokeCap.Round,
        )
    }

    // 9. Nose
    val nosePath = Path().apply {
        moveTo(cx, eyeY + eyeR * 0.8f)
        lineTo(cx - w * 0.022f, headTop + headH * 0.63f)
        lineTo(cx + w * 0.028f, headTop + headH * 0.64f)
    }
    drawPath(nosePath, color = skinShadow.copy(alpha = 0.9f), style = Stroke(strokeW * 0.95f, cap = StrokeCap.Round))

    // 10. Mouth / Smile
    val mouthY = headTop + headH * 0.77f
    if (isFem) {
        drawOval(
            color = Color(0xFFC0394B),
            topLeft = Offset(cx - w * 0.065f, mouthY - h * 0.022f),
            size = Size(w * 0.13f, h * 0.044f),
        )
    } else {
        val smilePath = Path().apply {
            moveTo(cx - w * 0.075f, mouthY)
            quadraticTo(cx, mouthY + h * 0.045f, cx + w * 0.075f, mouthY - h * 0.008f)
        }
        drawPath(smilePath, color = ink, style = Stroke(strokeW, cap = StrokeCap.Round))
    }

    // 11. Front Hair Layer
    drawCartoonFrontHair(
        hairStyle = traits.hairStyle,
        hairCol = hairCol,
        ink = ink,
        cx = cx,
        headTop = headTop,
        headH = headH,
        headHalfW = headHalfW,
        strokeW = strokeW,
    )

    // 12. Eyewear
    drawCartoonGlasses(
        glasses = traits.glasses,
        cx = cx,
        eyeY = eyeY,
        eyeOffsetX = eyeOffsetX,
        eyeR = eyeR,
        ink = ink,
        accentColor = accentColor,
        strokeW = strokeW,
    )

    // 13. Headwear (drawn cleanly over the crown)
    drawCartoonHeadwear(
        headwear = traits.headwear,
        cx = cx,
        headTop = headTop,
        headHalfW = headHalfW,
        accentColor = accentColor,
        ink = ink,
        strokeW = strokeW,
    )

    // 14. Playful mini cartoon cigar tucked at mouth corner with glowing amber ember & smoke curl
    val cigarStart = Offset(cx + w * 0.06f, mouthY + h * 0.005f)
    val cigarEnd = Offset(cx + w * 0.29f, mouthY + h * 0.055f)
    drawLine(
        color = Color(0xFF6B3E20),
        start = cigarStart,
        end = cigarEnd,
        strokeWidth = (w * 0.045f).coerceAtLeast(2.4f),
        cap = StrokeCap.Round,
    )
    // Gold cigar band
    drawCircle(
        color = Color(0xFFE8B84B),
        radius = w * 0.018f,
        center = Offset(cx + w * 0.14f, mouthY + h * 0.022f),
    )
    // Glowing cherry tip
    drawCircle(
        color = Color(0xFFFF6D2E),
        radius = w * 0.022f,
        center = cigarEnd,
    )
    // Tiny curling smoke wisp
    val smokePath = Path().apply {
        moveTo(cigarEnd.x + w * 0.01f, cigarEnd.y - h * 0.02f)
        cubicTo(
            cigarEnd.x + w * 0.06f, cigarEnd.y - h * 0.09f,
            cigarEnd.x - w * 0.02f, cigarEnd.y - h * 0.14f,
            cigarEnd.x + w * 0.04f, cigarEnd.y - h * 0.21f,
        )
    }
    drawPath(
        path = smokePath,
        color = Color.White.copy(alpha = 0.55f),
        style = Stroke(width = strokeW * 0.85f, cap = StrokeCap.Round),
    )
}

private fun DrawScope.drawCartoonOutfit(
    traits: CartoonTraits,
    accentColor: Color,
    ink: Color,
    strokeW: Float,
) {
    val w = size.width
    val h = size.height
    val cx = w * 0.50f

    val jacketColor = when (traits.outfit) {
        OutfitStyle.TUXEDO -> Color(0xFF191920)
        OutfitStyle.SUIT_TIE -> Color(0xFF283447)
        OutfitStyle.JERSEY_SPORT -> accentColor
        OutfitStyle.CHEF_COAT -> Color(0xFFF3EFE8)
        OutfitStyle.ROCK_JACKET -> Color(0xFF241E22)
        OutfitStyle.CASUAL_POLO -> accentColor.copy(alpha = 0.85f)
    }

    drawOval(
        color = jacketColor,
        topLeft = Offset(cx - w * 0.39f, h * 0.72f),
        size = Size(w * 0.78f, h * 0.42f),
    )
    drawOval(
        color = ink,
        topLeft = Offset(cx - w * 0.39f, h * 0.72f),
        size = Size(w * 0.78f, h * 0.42f),
        style = Stroke(strokeW),
    )

    when (traits.outfit) {
        OutfitStyle.TUXEDO -> {
            // Crisp white dress shirt V + classic bow tie
            val shirt = Path().apply {
                moveTo(cx - w * 0.10f, h * 0.73f)
                lineTo(cx, h * 0.94f)
                lineTo(cx + w * 0.10f, h * 0.73f)
                close()
            }
            drawPath(shirt, color = Color(0xFFFDFBF7))
            // Bow tie
            val bow = Path().apply {
                moveTo(cx - w * 0.08f, h * 0.76f)
                lineTo(cx, h * 0.79f)
                lineTo(cx - w * 0.08f, h * 0.82f)
                close()
                moveTo(cx + w * 0.08f, h * 0.76f)
                lineTo(cx, h * 0.79f)
                lineTo(cx + w * 0.08f, h * 0.82f)
                close()
            }
            drawPath(bow, color = Color(0xFFB71C1C))
        }
        OutfitStyle.SUIT_TIE -> {
            val shirt = Path().apply {
                moveTo(cx - w * 0.09f, h * 0.73f)
                lineTo(cx, h * 0.93f)
                lineTo(cx + w * 0.09f, h * 0.73f)
                close()
            }
            drawPath(shirt, color = Color(0xFFF7F4EE))
            drawLine(
                color = accentColor,
                start = Offset(cx, h * 0.77f),
                end = Offset(cx, h * 0.94f),
                strokeWidth = w * 0.045f,
                cap = StrokeCap.Round,
            )
        }
        OutfitStyle.JERSEY_SPORT -> {
            // Classic sporty V-neck trim
            val collar = Path().apply {
                moveTo(cx - w * 0.12f, h * 0.74f)
                lineTo(cx, h * 0.86f)
                lineTo(cx + w * 0.12f, h * 0.74f)
            }
            drawPath(collar, color = Color.White, style = Stroke(strokeW * 1.6f, cap = StrokeCap.Round))
        }
        OutfitStyle.CHEF_COAT -> {
            // Double-breasted chef coat buttons
            for (yFactor in listOf(0.82f, 0.90f)) {
                drawCircle(ink, radius = w * 0.018f, center = Offset(cx - w * 0.07f, h * yFactor))
                drawCircle(ink, radius = w * 0.018f, center = Offset(cx + w * 0.07f, h * yFactor))
            }
        }
        OutfitStyle.ROCK_JACKET -> {
            // Lapel V accents
            drawLine(Color(0xFFD8D0C8), Offset(cx - w * 0.12f, h * 0.74f), Offset(cx - w * 0.04f, h * 0.88f), strokeW)
            drawLine(Color(0xFFD8D0C8), Offset(cx + w * 0.12f, h * 0.74f), Offset(cx + w * 0.04f, h * 0.88f), strokeW)
        }
        OutfitStyle.CASUAL_POLO -> {
            drawLine(Color.White.copy(alpha = 0.7f), Offset(cx, h * 0.76f), Offset(cx, h * 0.88f), strokeW)
        }
    }
}

private fun DrawScope.drawCartoonFacialHair(
    facialHair: FacialHair,
    hairCol: Color,
    cx: Float,
    headTop: Float,
    headH: Float,
    headHalfW: Float,
) {
    val mouthY = headTop + headH * 0.77f
    when (facialHair) {
        FacialHair.NONE -> Unit
        FacialHair.STUBBLE -> {
            drawOval(
                color = hairCol.copy(alpha = 0.26f),
                topLeft = Offset(cx - headHalfW * 0.82f, headTop + headH * 0.65f),
                size = Size(headHalfW * 1.64f, headH * 0.35f),
            )
        }
        FacialHair.MUSTACHE -> {
            drawOval(
                color = hairCol,
                topLeft = Offset(cx - headHalfW * 0.42f, mouthY - headH * 0.12f),
                size = Size(headHalfW * 0.84f, headH * 0.11f),
            )
        }
        FacialHair.WALRUS_STACHE -> {
            drawOval(
                color = hairCol,
                topLeft = Offset(cx - headHalfW * 0.56f, mouthY - headH * 0.13f),
                size = Size(headHalfW * 1.12f, headH * 0.16f),
            )
        }
        FacialHair.GOATEE -> {
            drawOval(
                color = hairCol,
                topLeft = Offset(cx - headHalfW * 0.38f, mouthY - headH * 0.10f),
                size = Size(headHalfW * 0.76f, headH * 0.34f),
            )
        }
        FacialHair.FULL_BEARD -> {
            val beard = Path().apply {
                moveTo(cx - headHalfW * 0.94f, headTop + headH * 0.56f)
                cubicTo(
                    cx - headHalfW * 0.96f, headTop + headH * 1.04f,
                    cx + headHalfW * 0.96f, headTop + headH * 1.04f,
                    cx + headHalfW * 0.94f, headTop + headH * 0.56f,
                )
                lineTo(cx + headHalfW * 0.52f, mouthY - headH * 0.08f)
                lineTo(cx - headHalfW * 0.52f, mouthY - headH * 0.08f)
                close()
            }
            drawPath(beard, color = hairCol)
        }
    }
}

private fun DrawScope.drawCartoonFrontHair(
    hairStyle: HairStyle,
    hairCol: Color,
    ink: Color,
    cx: Float,
    headTop: Float,
    headH: Float,
    headHalfW: Float,
    strokeW: Float,
) {
    when (hairStyle) {
        HairStyle.BALD -> {
            // Playful cartoon specular shine on bald crown
            drawOval(
                color = Color.White.copy(alpha = 0.34f),
                topLeft = Offset(cx - headHalfW * 0.42f, headTop + headH * 0.08f),
                size = Size(headHalfW * 0.45f, headH * 0.10f),
            )
        }
        HairStyle.BUZZ -> {
            val buzz = Path().apply {
                moveTo(cx - headHalfW * 0.95f, headTop + headH * 0.32f)
                cubicTo(cx - headHalfW * 0.85f, headTop - headH * 0.05f, cx + headHalfW * 0.85f, headTop - headH * 0.05f, cx + headHalfW * 0.95f, headTop + headH * 0.32f)
                quadraticTo(cx, headTop + headH * 0.14f, cx - headHalfW * 0.95f, headTop + headH * 0.32f)
                close()
            }
            drawPath(buzz, color = hairCol)
        }
        HairStyle.SHORT_PART -> {
            val hair = Path().apply {
                moveTo(cx - headHalfW * 0.98f, headTop + headH * 0.36f)
                cubicTo(cx - headHalfW * 1.02f, headTop - headH * 0.14f, cx + headHalfW * 0.96f, headTop - headH * 0.14f, cx + headHalfW * 0.98f, headTop + headH * 0.34f)
                quadraticTo(cx + headHalfW * 0.25f, headTop + headH * 0.12f, cx - headHalfW * 0.28f, headTop + headH * 0.24f)
                quadraticTo(cx - headHalfW * 0.65f, headTop + headH * 0.16f, cx - headHalfW * 0.98f, headTop + headH * 0.36f)
                close()
            }
            drawPath(hair, color = hairCol)
            drawPath(hair, color = ink, style = Stroke(strokeW * 0.85f))
        }
        HairStyle.SLICK_BACK -> {
            val slick = Path().apply {
                moveTo(cx - headHalfW * 0.98f, headTop + headH * 0.34f)
                cubicTo(cx - headHalfW * 0.96f, headTop - headH * 0.18f, cx + headHalfW * 0.96f, headTop - headH * 0.18f, cx + headHalfW * 0.98f, headTop + headH * 0.34f)
                quadraticTo(cx, headTop + headH * 0.06f, cx - headHalfW * 0.98f, headTop + headH * 0.34f)
                close()
            }
            drawPath(slick, color = hairCol)
            drawPath(slick, color = ink, style = Stroke(strokeW * 0.85f))
        }
        HairStyle.CURLY -> {
            val puffs = listOf(
                Offset(cx - headHalfW * 0.72f, headTop + headH * 0.16f),
                Offset(cx - headHalfW * 0.36f, headTop + headH * 0.02f),
                Offset(cx, headTop - headH * 0.02f),
                Offset(cx + headHalfW * 0.36f, headTop + headH * 0.02f),
                Offset(cx + headHalfW * 0.72f, headTop + headH * 0.16f),
            )
            puffs.forEach { pt ->
                drawCircle(hairCol, radius = headHalfW * 0.36f, center = pt)
            }
        }
        HairStyle.SPIKY -> {
            val spikes = Path().apply {
                moveTo(cx - headHalfW * 0.94f, headTop + headH * 0.30f)
                lineTo(cx - headHalfW * 0.85f, headTop - headH * 0.08f)
                lineTo(cx - headHalfW * 0.48f, headTop + headH * 0.04f)
                lineTo(cx - headHalfW * 0.22f, headTop - headH * 0.18f)
                lineTo(cx + headHalfW * 0.05f, headTop + headH * 0.02f)
                lineTo(cx + headHalfW * 0.36f, headTop - headH * 0.15f)
                lineTo(cx + headHalfW * 0.62f, headTop + headH * 0.04f)
                lineTo(cx + headHalfW * 0.88f, headTop - headH * 0.06f)
                lineTo(cx + headHalfW * 0.94f, headTop + headH * 0.30f)
                quadraticTo(cx, headTop + headH * 0.16f, cx - headHalfW * 0.94f, headTop + headH * 0.30f)
                close()
            }
            drawPath(spikes, color = hairCol)
            drawPath(spikes, color = ink, style = Stroke(strokeW * 0.8f))
        }
        HairStyle.LONG_WAVY, HairStyle.UPDO_GLAM -> {
            val fringe = Path().apply {
                moveTo(cx - headHalfW * 1.02f, headTop + headH * 0.40f)
                cubicTo(cx - headHalfW * 0.98f, headTop - headH * 0.12f, cx + headHalfW * 0.98f, headTop - headH * 0.12f, cx + headHalfW * 1.02f, headTop + headH * 0.40f)
                quadraticTo(cx + headHalfW * 0.25f, headTop + headH * 0.14f, cx - headHalfW * 0.15f, headTop + headH * 0.24f)
                quadraticTo(cx - headHalfW * 0.65f, headTop + headH * 0.18f, cx - headHalfW * 1.02f, headTop + headH * 0.40f)
                close()
            }
            drawPath(fringe, color = hairCol)
            drawPath(fringe, color = ink, style = Stroke(strokeW * 0.8f))
        }
        HairStyle.LOCS_BRAIDS -> {
            for (xOff in listOf(-0.72f, -0.38f, 0f, 0.38f, 0.72f)) {
                drawCircle(hairCol, radius = headHalfW * 0.26f, center = Offset(cx + headHalfW * xOff, headTop + headH * 0.08f))
            }
        }
        HairStyle.RECEDING_SILVER -> {
            // Side tufts + shiny receding forehead
            drawOval(
                color = hairCol,
                topLeft = Offset(cx - headHalfW * 1.04f, headTop + headH * 0.24f),
                size = Size(headHalfW * 0.34f, headH * 0.30f),
            )
            drawOval(
                color = hairCol,
                topLeft = Offset(cx + headHalfW * 0.70f, headTop + headH * 0.24f),
                size = Size(headHalfW * 0.34f, headH * 0.30f),
            )
            drawOval(
                color = Color.White.copy(alpha = 0.26f),
                topLeft = Offset(cx - headHalfW * 0.34f, headTop + headH * 0.08f),
                size = Size(headHalfW * 0.38f, headH * 0.09f),
            )
        }
    }
}

private fun DrawScope.drawCartoonGlasses(
    glasses: GlassesStyle,
    cx: Float,
    eyeY: Float,
    eyeOffsetX: Float,
    eyeR: Float,
    ink: Color,
    accentColor: Color,
    strokeW: Float,
) {
    if (glasses == GlassesStyle.NONE) return
    val lensR = eyeR * 2.15f
    val leftCx = cx - eyeOffsetX
    val rightCx = cx + eyeOffsetX

    when (glasses) {
        GlassesStyle.CLASSIC_ROUND -> {
            drawCircle(ink, radius = lensR, center = Offset(leftCx, eyeY), style = Stroke(strokeW * 1.1f))
            drawCircle(ink, radius = lensR, center = Offset(rightCx, eyeY), style = Stroke(strokeW * 1.1f))
            drawLine(ink, Offset(leftCx + lensR, eyeY), Offset(rightCx - lensR, eyeY), strokeW * 1.1f)
        }
        GlassesStyle.SQUARE_FRAMES -> {
            val lw = lensR * 2.0f
            val lh = lensR * 1.55f
            drawRoundRect(ink, Offset(leftCx - lw / 2f, eyeY - lh / 2f), Size(lw, lh), CornerRadius(4f, 4f), style = Stroke(strokeW * 1.25f))
            drawRoundRect(ink, Offset(rightCx - lw / 2f, eyeY - lh / 2f), Size(lw, lh), CornerRadius(4f, 4f), style = Stroke(strokeW * 1.25f))
            drawLine(ink, Offset(leftCx + lw / 2f, eyeY - lh * 0.1f), Offset(rightCx - lw / 2f, eyeY - lh * 0.1f), strokeW * 1.2f)
        }
        GlassesStyle.SUNGLASSES -> {
            val lw = lensR * 2.15f
            val lh = lensR * 1.55f
            drawRoundRect(Color(0xFF141214), Offset(leftCx - lw / 2f, eyeY - lh / 2f), Size(lw, lh), CornerRadius(6f, 6f))
            drawRoundRect(Color(0xFF141214), Offset(rightCx - lw / 2f, eyeY - lh / 2f), Size(lw, lh), CornerRadius(6f, 6f))
            drawLine(Color(0xFF141214), Offset(leftCx + lw / 2f, eyeY - lh * 0.15f), Offset(rightCx - lw / 2f, eyeY - lh * 0.15f), strokeW * 1.4f)
            // Cool diagonal shine
            drawLine(Color.White.copy(alpha = 0.35f), Offset(leftCx - lw * 0.25f, eyeY - lh * 0.3f), Offset(leftCx + lw * 0.1f, eyeY + lh * 0.25f), strokeW * 0.9f)
            drawLine(Color.White.copy(alpha = 0.35f), Offset(rightCx - lw * 0.25f, eyeY - lh * 0.3f), Offset(rightCx + lw * 0.1f, eyeY + lh * 0.25f), strokeW * 0.9f)
        }
        GlassesStyle.AVIATOR -> {
            val lw = lensR * 2.1f
            val lh = lensR * 1.75f
            drawOval(Color(0xAA4A3728), Offset(leftCx - lw / 2f, eyeY - lh / 2.2f), Size(lw, lh))
            drawOval(Color(0xAA4A3728), Offset(rightCx - lw / 2f, eyeY - lh / 2.2f), Size(lw, lh))
            drawOval(Color(0xFFD9A326), Offset(leftCx - lw / 2f, eyeY - lh / 2.2f), Size(lw, lh), style = Stroke(strokeW))
            drawOval(Color(0xFFD9A326), Offset(rightCx - lw / 2f, eyeY - lh / 2.2f), Size(lw, lh), style = Stroke(strokeW))
            drawLine(Color(0xFFD9A326), Offset(leftCx + lw * 0.35f, eyeY - lh * 0.35f), Offset(rightCx - lw * 0.35f, eyeY - lh * 0.35f), strokeW)
        }
        GlassesStyle.CAT_EYE -> {
            val lw = lensR * 2.0f
            val lh = lensR * 1.4f
            drawOval(Color(0xCC1E1519), Offset(leftCx - lw / 2f, eyeY - lh / 2f), Size(lw, lh))
            drawOval(Color(0xCC1E1519), Offset(rightCx - lw / 2f, eyeY - lh / 2f), Size(lw, lh))
            drawOval(accentColor, Offset(leftCx - lw / 2f, eyeY - lh / 2f), Size(lw, lh), style = Stroke(strokeW * 1.15f))
            drawOval(accentColor, Offset(rightCx - lw / 2f, eyeY - lh / 2f), Size(lw, lh), style = Stroke(strokeW * 1.15f))
            drawLine(accentColor, Offset(leftCx + lw / 2f, eyeY), Offset(rightCx - lw / 2f, eyeY), strokeW)
        }
        GlassesStyle.NONE -> Unit
    }
}

private fun DrawScope.drawCartoonHeadwear(
    headwear: Headwear,
    cx: Float,
    headTop: Float,
    headHalfW: Float,
    accentColor: Color,
    ink: Color,
    strokeW: Float,
) {
    val w = size.width
    val h = size.height
    when (headwear) {
        Headwear.NONE -> Unit
        Headwear.FEDORA -> {
            val hatCol = Color(0xFF2F3542)
            // Classic tilted Sinatra/Rat-Pack fedora crown + band + brim
            drawRoundRect(
                color = hatCol,
                topLeft = Offset(cx - headHalfW * 0.85f, headTop - h * 0.14f),
                size = Size(headHalfW * 1.70f, h * 0.22f),
                cornerRadius = CornerRadius(w * 0.08f, w * 0.08f),
            )
            // Satin ribbon band
            drawRect(
                color = accentColor,
                topLeft = Offset(cx - headHalfW * 0.85f, headTop + h * 0.02f),
                size = Size(headHalfW * 1.70f, h * 0.045f),
            )
            // Wide brim
            drawOval(
                color = hatCol,
                topLeft = Offset(cx - headHalfW * 1.38f, headTop + h * 0.04f),
                size = Size(headHalfW * 2.76f, h * 0.075f),
            )
            drawOval(
                color = ink,
                topLeft = Offset(cx - headHalfW * 1.38f, headTop + h * 0.04f),
                size = Size(headHalfW * 2.76f, h * 0.075f),
                style = Stroke(strokeW * 0.85f),
            )
        }
        Headwear.BOWLER -> {
            val bowlerCol = Color(0xFF1F1D20)
            drawOval(
                color = bowlerCol,
                topLeft = Offset(cx - headHalfW * 0.82f, headTop - h * 0.15f),
                size = Size(headHalfW * 1.64f, h * 0.25f),
            )
            drawOval(
                color = bowlerCol,
                topLeft = Offset(cx - headHalfW * 1.24f, headTop + h * 0.04f),
                size = Size(headHalfW * 2.48f, h * 0.07f),
            )
            drawOval(
                color = ink,
                topLeft = Offset(cx - headHalfW * 1.24f, headTop + h * 0.04f),
                size = Size(headHalfW * 2.48f, h * 0.07f),
                style = Stroke(strokeW * 0.85f),
            )
        }
        Headwear.FLAT_CAP -> {
            drawOval(
                color = accentColor.copy(alpha = 0.92f),
                topLeft = Offset(cx - headHalfW * 1.05f, headTop - h * 0.07f),
                size = Size(headHalfW * 2.15f, h * 0.16f),
            )
            drawOval(
                color = ink,
                topLeft = Offset(cx - headHalfW * 1.05f, headTop - h * 0.07f),
                size = Size(headHalfW * 2.15f, h * 0.16f),
                style = Stroke(strokeW * 0.8f),
            )
        }
        Headwear.BASEBALL_CAP -> {
            drawOval(
                color = accentColor,
                topLeft = Offset(cx - headHalfW * 0.98f, headTop - h * 0.08f),
                size = Size(headHalfW * 1.96f, h * 0.20f),
            )
            // Visor bill
            drawOval(
                color = accentColor.copy(alpha = 0.85f),
                topLeft = Offset(cx - headHalfW * 0.55f, headTop + h * 0.05f),
                size = Size(headHalfW * 1.65f, h * 0.065f),
            )
        }
        Headwear.COWBOY_HAT -> {
            val tanHat = Color(0xFF9C7248)
            drawRoundRect(
                color = tanHat,
                topLeft = Offset(cx - headHalfW * 0.76f, headTop - h * 0.14f),
                size = Size(headHalfW * 1.52f, h * 0.22f),
                cornerRadius = CornerRadius(w * 0.08f, w * 0.08f),
            )
            drawOval(
                color = tanHat,
                topLeft = Offset(cx - headHalfW * 1.50f, headTop + h * 0.03f),
                size = Size(headHalfW * 3.00f, h * 0.08f),
            )
            drawOval(
                color = ink,
                topLeft = Offset(cx - headHalfW * 1.50f, headTop + h * 0.03f),
                size = Size(headHalfW * 3.00f, h * 0.08f),
                style = Stroke(strokeW * 0.85f),
            )
        }
        Headwear.HEADWRAP -> {
            drawOval(
                color = accentColor,
                topLeft = Offset(cx - headHalfW * 1.04f, headTop - h * 0.08f),
                size = Size(headHalfW * 2.08f, h * 0.18f),
            )
            drawCircle(
                color = Color(0xFFF6D365),
                radius = w * 0.045f,
                center = Offset(cx, headTop - h * 0.03f),
            )
        }
    }
}

@Composable
fun CelebrityCartoonMiniChip(
    profile: CelebrityProfile,
    modifier: Modifier = Modifier,
) {
    val accent = Color(profile.accentHex)
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
    ) {
        Row(
            modifier = Modifier
                .border(1.dp, accent.copy(alpha = 0.45f), RoundedCornerShape(14.dp))
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            CelebrityCartoonAvatar(profile = profile, size = 54.dp)
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = profile.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "${profile.flagEmoji} ${profile.country} · ${profile.roleTag}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "${profile.archetype.emoji} ${profile.favoriteBrand}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
fun CelebrityCartoonPortraitStrip(
    profiles: List<CelebrityProfile>,
    modifier: Modifier = Modifier,
) {
    if (profiles.isEmpty()) return
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(profiles, key = { it.id }) { profile ->
            CelebrityCartoonMiniChip(profile = profile)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CelebrityLoungeGallerySheet(
    onSelectCelebrity: (CelebrityProfile) -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var query by remember { mutableStateOf("") }
    // 0 = All, 1 = Worldwide International, 2 = Local USA
    var scopeFilter by remember { mutableIntStateOf(0) }

    val filteredProfiles = remember(query, scopeFilter) {
        val base = CigarCelebrityDirectory.searchProfiles(
            query = query,
            onlyInternational = scopeFilter == 1,
        )
        if (scopeFilter == 2) base.filter { !it.isInternational } else base
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "🎭 Celebrity Aficionado Wall",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = "Browse ${CigarCelebrityDirectory.allCelebrityProfiles.size} local & worldwide cigar icons with custom-matched cartoon portraits. Tap any icon to explore their favorite cigars.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text("Search celebrity, country, or brand…") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { query = "" }) {
                            Icon(Icons.Filled.Clear, contentDescription = "Clear search")
                        }
                    }
                },
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("celebrity_wall_search_input"),
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                val intlCount = remember { CigarCelebrityDirectory.allCelebrityProfiles.count { it.isInternational } }
                val localCount = remember { CigarCelebrityDirectory.allCelebrityProfiles.count { !it.isInternational } }
                FilterChip(
                    selected = scopeFilter == 0,
                    onClick = { scopeFilter = 0 },
                    label = { Text("✨ All (${CigarCelebrityDirectory.allCelebrityProfiles.size})") },
                )
                FilterChip(
                    selected = scopeFilter == 1,
                    onClick = { scopeFilter = 1 },
                    label = { Text("🌍 Global ($intlCount)") },
                )
                FilterChip(
                    selected = scopeFilter == 2,
                    onClick = { scopeFilter = 2 },
                    label = { Text("🇺🇸 Local ($localCount)") },
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(440.dp),
                contentPadding = PaddingValues(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(filteredProfiles, key = { it.id }) { profile ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium)
                            .clickable {
                                onSelectCelebrity(profile)
                                onDismiss()
                            }
                            .testTag("celeb_wall_card_${profile.id}"),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                        ),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            CelebrityCartoonAvatar(profile = profile, size = 66.dp)
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(3.dp),
                            ) {
                                Text(
                                    text = "${profile.name} ${profile.flagEmoji}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                )
                                Text(
                                    text = "${profile.country} · ${profile.archetype.emoji} ${profile.roleTag} · Fav: ${profile.favoriteBrand}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary,
                                )
                                Text(
                                    text = profile.note,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 3,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
