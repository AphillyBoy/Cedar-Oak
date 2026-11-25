package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.catalog.CigarAccoladesCatalog
import com.example.model.Cigar
import com.example.model.CigarAccolade
import com.example.model.WrapperShade
import com.example.ui.theme.StarGold
import kotlin.math.abs

private data class BandPalette(
    val primary: Color,
    val secondary: Color,
    val trimGold: Color,
    val textOnBand: Color,
    val monogram: String,
)

private fun bandPaletteForCigar(cigar: Cigar): BandPalette {
    val brand = cigar.brand
    val gold = Color(0xFFE6BE6A)
    val cream = Color(0xFFF9F1E2)
    val darkInk = Color(0xFF1A120B)

    val monogram = brand
        .split(" ", "-", "/")
        .filter { it.isNotBlank() }
        .take(2)
        .joinToString("") { it.first().uppercase() }
        .ifBlank { "CO" }

    return when {
        brand.equals("Cohiba", ignoreCase = true) -> BandPalette(
            primary = Color(0xFF1A1614),
            secondary = Color(0xFFD9A326),
            trimGold = gold,
            textOnBand = cream,
            monogram = "CH",
        )
        brand.equals("Padrón", ignoreCase = true) -> BandPalette(
            primary = Color(0xFF5A2E1A),
            secondary = Color(0xFF3A1B0E),
            trimGold = gold,
            textOnBand = cream,
            monogram = "P",
        )
        brand.equals("Arturo Fuente", ignoreCase = true) -> BandPalette(
            primary = Color(0xFF8B1E1E),
            secondary = Color(0xFF1C3B2B),
            trimGold = gold,
            textOnBand = cream,
            monogram = "AF",
        )
        brand.equals("Davidoff", ignoreCase = true) -> BandPalette(
            primary = Color(0xFFF5EFE6),
            secondary = Color(0xFFE8DCC8),
            trimGold = Color(0xFFC99B3B),
            textOnBand = darkInk,
            monogram = "D",
        )
        brand.equals("Montecristo", ignoreCase = true) -> BandPalette(
            primary = Color(0xFF6B3E1E),
            secondary = Color(0xFF42230E),
            trimGold = gold,
            textOnBand = cream,
            monogram = "MC",
        )
        brand.equals("Romeo y Julieta", ignoreCase = true) -> BandPalette(
            primary = Color(0xFF9B1C20),
            secondary = Color(0xFF691114),
            trimGold = gold,
            textOnBand = cream,
            monogram = "RJ",
        )
        brand.equals("Partagás", ignoreCase = true) -> BandPalette(
            primary = Color(0xFF8C1D18),
            secondary = Color(0xFF4B0E0C),
            trimGold = gold,
            textOnBand = cream,
            monogram = "P",
        )
        brand.equals("My Father", ignoreCase = true) -> BandPalette(
            primary = Color(0xFF7B241C),
            secondary = Color(0xFF281A14),
            trimGold = gold,
            textOnBand = cream,
            monogram = "MF",
        )
        brand.equals("Oliva", ignoreCase = true) -> BandPalette(
            primary = Color(0xFF6A1B1A),
            secondary = Color(0xFF2C1810),
            trimGold = gold,
            textOnBand = cream,
            monogram = "OV",
        )
        brand.equals("E.P. Carrillo", ignoreCase = true) -> BandPalette(
            primary = Color(0xFF1B3A5C),
            secondary = Color(0xFF0E2238),
            trimGold = gold,
            textOnBand = cream,
            monogram = "EPC",
        )
        brand.equals("Alec Bradley", ignoreCase = true) -> BandPalette(
            primary = Color(0xFF242426),
            secondary = Color(0xFF5D211B),
            trimGold = gold,
            textOnBand = cream,
            monogram = "AB",
        )
        brand.equals("CAO", ignoreCase = true) -> BandPalette(
            primary = Color(0xFF1E4D36),
            secondary = Color(0xFF15191A),
            trimGold = gold,
            textOnBand = cream,
            monogram = "CAO",
        )
        brand.equals("Macanudo", ignoreCase = true) -> BandPalette(
            primary = Color(0xFFF2E6D0),
            secondary = Color(0xFF8B5A2B),
            trimGold = Color(0xFFB8862D),
            textOnBand = darkInk,
            monogram = "M",
        )
        else -> {
            val paletteChoices = listOf(
                Color(0xFF7A2021) to Color(0xFF3E1011),
                Color(0xFF1F4236) to Color(0xFF10241D),
                Color(0xFF1E3552) to Color(0xFF101D2E),
                Color(0xFF5D3418) to Color(0xFF331B0B),
                Color(0xFF472549) to Color(0xFF251226),
                Color(0xFF262628) to Color(0xFF121214),
            )
            val idx = abs(brand.hashCode()) % paletteChoices.size
            val (p, s) = paletteChoices[idx]
            BandPalette(
                primary = p,
                secondary = s,
                trimGold = gold,
                textOnBand = cream,
                monogram = monogram,
            )
        }
    }
}

private enum class VitolaSilhouette {
    TORPEDO,
    FIGURADO,
    LANCERO,
    BOX_PRESSED,
    GORDO,
    STANDARD,
}

private fun silhouetteForCigar(cigar: Cigar): VitolaSilhouette {
    val v = "${cigar.vitola} ${cigar.name}".lowercase()
    return when {
        v.contains("torpedo") || v.contains("belicoso") || v.contains("piramide") || v.contains("pyramid") || v.contains("campana") -> VitolaSilhouette.TORPEDO
        v.contains("figurado") || v.contains("perfecto") || v.contains("salomon") || v.contains("bull") || v.contains("short story") || v.contains("work of art") -> VitolaSilhouette.FIGURADO
        v.contains("lancero") || v.contains("panatela") || v.contains("lonsdale") || v.contains("fundadores") -> VitolaSilhouette.LANCERO
        v.contains("box-press") || v.contains("prensado") || v.contains("1964") || v.contains("1926") || v.contains("judge") -> VitolaSilhouette.BOX_PRESSED
        v.contains("gordo") || v.contains("60") || v.contains("660") || v.contains("nub") || v.contains("asylum") -> VitolaSilhouette.GORDO
        else -> VitolaSilhouette.STANDARD
    }
}

private fun wrapperGradientColors(shade: WrapperShade): List<Color> = when (shade) {
    WrapperShade.CLARO -> listOf(Color(0xFFE6C892), Color(0xFFCFA769), Color(0xFFA87D44))
    WrapperShade.COLORADO_CLARO -> listOf(Color(0xFFC98F54), Color(0xFFA56A36), Color(0xFF7B4A21))
    WrapperShade.COLORADO -> listOf(Color(0xFFA95C33), Color(0xFF83401F), Color(0xFF5A2811))
    WrapperShade.MADURO -> listOf(Color(0xFF6B3E26), Color(0xFF4B2816), Color(0xFF2D160B))
    WrapperShade.OSCURO -> listOf(Color(0xFF462B1E), Color(0xFF2A1810), Color(0xFF170C07))
}

private fun DrawScope.drawHandRolledCigar(
    cigar: Cigar,
    palette: BandPalette,
    verticalPaddingFactor: Float = 0.24f,
) {
    val silhouette = silhouetteForCigar(cigar)
    val wrapperColors = wrapperGradientColors(cigar.wrapperShade)

    val leftPad = size.width * 0.04f
    val rightPad = size.width * 0.04f
    val totalW = size.width - leftPad - rightPad
    val cigarH = when (silhouette) {
        VitolaSilhouette.LANCERO -> size.height * 0.36f
        VitolaSilhouette.GORDO -> size.height * 0.62f
        else -> size.height * (1f - verticalPaddingFactor * 2f)
    }.coerceIn(14.dp.toPx(), size.height * 0.72f)

    val topY = (size.height - cigarH) / 2f
    val bottomY = topY + cigarH
    val midY = size.height / 2f

    // Soft studio drop shadow
    drawRoundRect(
        color = Color.Black.copy(alpha = 0.42f),
        topLeft = Offset(leftPad + 3.dp.toPx(), topY + 5.dp.toPx()),
        size = Size(totalW, cigarH),
        cornerRadius = CornerRadius(cigarH / 2f, cigarH / 2f),
    )

    val bodyPath = Path().apply {
        when (silhouette) {
            VitolaSilhouette.TORPEDO -> {
                val tipX = leftPad
                val shoulderX = leftPad + totalW * 0.17f
                val footX = leftPad + totalW
                moveTo(tipX, midY)
                quadraticTo(leftPad + totalW * 0.06f, topY, shoulderX, topY)
                lineTo(footX - 3.dp.toPx(), topY)
                lineTo(footX, topY + 2.dp.toPx())
                lineTo(footX, bottomY - 2.dp.toPx())
                lineTo(footX - 3.dp.toPx(), bottomY)
                lineTo(shoulderX, bottomY)
                quadraticTo(leftPad + totalW * 0.06f, bottomY, tipX, midY)
                close()
            }
            VitolaSilhouette.FIGURADO -> {
                val tipX = leftPad
                val leftBelly = leftPad + totalW * 0.22f
                val rightBelly = leftPad + totalW * 0.78f
                val footX = leftPad + totalW
                moveTo(tipX, midY)
                quadraticTo(leftPad + totalW * 0.08f, topY, leftBelly, topY)
                lineTo(rightBelly, topY)
                quadraticTo(leftPad + totalW * 0.92f, topY, footX, midY - cigarH * 0.24f)
                lineTo(footX, midY + cigarH * 0.24f)
                quadraticTo(leftPad + totalW * 0.92f, bottomY, rightBelly, bottomY)
                lineTo(leftBelly, bottomY)
                quadraticTo(leftPad + totalW * 0.08f, bottomY, tipX, midY)
                close()
            }
            VitolaSilhouette.BOX_PRESSED -> {
                val capW = cigarH * 0.45f
                moveTo(leftPad + capW, topY)
                lineTo(leftPad + totalW, topY)
                lineTo(leftPad + totalW, bottomY)
                lineTo(leftPad + capW, bottomY)
                quadraticTo(leftPad, bottomY, leftPad, midY)
                quadraticTo(leftPad, topY, leftPad + capW, topY)
                close()
            }
            else -> {
                val capW = cigarH * 0.52f
                moveTo(leftPad + capW, topY)
                lineTo(leftPad + totalW, topY)
                lineTo(leftPad + totalW, bottomY)
                lineTo(leftPad + capW, bottomY)
                quadraticTo(leftPad, bottomY, leftPad, midY)
                quadraticTo(leftPad, topY, leftPad + capW, topY)
                close()
            }
        }
    }

    // Wrapper leaf fill
    drawPath(
        path = bodyPath,
        brush = Brush.verticalGradient(
            colors = wrapperColors,
            startY = topY,
            endY = bottomY,
        ),
    )

    // Subtle specular oil sheen along upper third of cigar barrel
    drawLine(
        color = Color.White.copy(alpha = 0.14f),
        start = Offset(leftPad + totalW * 0.15f, topY + cigarH * 0.24f),
        end = Offset(leftPad + totalW * 0.96f, topY + cigarH * 0.24f),
        strokeWidth = (cigarH * 0.12f).coerceAtLeast(1.5.dp.toPx()),
    )

    // Subtle diagonal wrapper leaf seams
    val seamColor = Color.Black.copy(alpha = 0.16f)
    for (i in 1..6) {
        val sx = leftPad + totalW * (0.14f + i * 0.13f)
        drawLine(
            color = seamColor,
            start = Offset(sx, topY + 1.dp.toPx()),
            end = Offset(sx + cigarH * 0.28f, bottomY - 1.dp.toPx()),
            strokeWidth = 0.9.dp.toPx(),
        )
    }

    // Cut foot rim shading on the right edge
    drawRect(
        color = Color(0xFF1F120A).copy(alpha = 0.55f),
        topLeft = Offset(leftPad + totalW - 3.dp.toPx(), topY + 1.dp.toPx()),
        size = Size(3.dp.toPx(), cigarH - 2.dp.toPx()),
    )

    // Cigar outline
    drawPath(
        path = bodyPath,
        color = Color.Black.copy(alpha = 0.35f),
        style = Stroke(width = 1.dp.toPx()),
    )

    // Embossed die-cut cigar band placed at ~26% from head
    val bandX = leftPad + totalW * 0.24f
    val bandW = (totalW * 0.17f).coerceIn(24.dp.toPx(), 46.dp.toPx())
    val bandTop = topY - 1.dp.toPx()
    val bandHeight = cigarH + 2.dp.toPx()

    // Gold trim backing
    drawRoundRect(
        color = palette.trimGold,
        topLeft = Offset(bandX - 1.5.dp.toPx(), bandTop),
        size = Size(bandW + 3.dp.toPx(), bandHeight),
        cornerRadius = CornerRadius(3.dp.toPx(), 3.dp.toPx()),
    )
    // Main colored band face
    drawRoundRect(
        brush = Brush.verticalGradient(
            colors = listOf(palette.primary, palette.secondary, palette.primary),
            startY = bandTop,
            endY = bandTop + bandHeight,
        ),
        topLeft = Offset(bandX, bandTop + 1.2.dp.toPx()),
        size = Size(bandW, bandHeight - 2.4.dp.toPx()),
        cornerRadius = CornerRadius(2.dp.toPx(), 2.dp.toPx()),
    )
    // Die-cut oval crest medallion on center of band
    val crestCenter = Offset(bandX + bandW / 2f, midY)
    val crestR = (cigarH * 0.34f).coerceAtLeast(6.dp.toPx())
    drawCircle(
        color = palette.trimGold,
        radius = crestR + 1.2.dp.toPx(),
        center = crestCenter,
    )
    drawCircle(
        color = palette.secondary,
        radius = crestR,
        center = crestCenter,
    )
    drawCircle(
        color = palette.trimGold.copy(alpha = 0.7f),
        radius = crestR * 0.6f,
        center = crestCenter,
        style = Stroke(width = 0.9.dp.toPx()),
    )
}

@Composable
fun CigarStudioShowcaseCard(
    cigar: Cigar,
    accolade: CigarAccolade,
    modifier: Modifier = Modifier,
) {
    val palette = remember(cigar.brand) { bandPaletteForCigar(cigar) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("cigar_studio_showcase_${cigar.id}"),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color(0xFF19120E)),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.img_cigar_showcase_hero),
                contentDescription = stringResource(R.string.cd_cigar_showcase_hero),
                contentScale = ContentScale.Crop,
                alpha = 0.30f,
                modifier = Modifier.matchParentSize(),
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF16100C).copy(alpha = 0.65f),
                                Color(0xFF120C09).copy(alpha = 0.92f),
                            )
                        )
                    ),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "CIGAR & BAND LABEL",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFE6BE6A),
                    )
                    Text(
                        text = "${cigar.vitola} · ${cigar.dimensions}",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFD8C9BC),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                // Vitola-accurate illustrated cigar with embossed band
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(62.dp),
                ) {
                    drawHandRolledCigar(cigar = cigar, palette = palette, verticalPaddingFactor = 0.22f)
                }

                // Magnified Cigar Band Label Crest
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(palette.primary.copy(alpha = 0.95f))
                        .border(1.5.dp, palette.trimGold, RoundedCornerShape(12.dp))
                        .padding(horizontal = 14.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(palette.secondary)
                            .border(2.dp, palette.trimGold, CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = palette.monogram,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = palette.trimGold,
                            maxLines = 1,
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                    ) {
                        Text(
                            text = cigar.brand.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = palette.trimGold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = cigar.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = palette.textOnBand,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = "Hecho a Mano · ${cigar.origin} · ${cigar.wrapperShade.label}",
                            style = MaterialTheme.typography.bodySmall,
                            color = palette.textOnBand.copy(alpha = 0.78f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    if (accolade.isCigarOfTheYear) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = palette.trimGold,
                        ) {
                            Column(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = "#1 COTY",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1A120B),
                                )
                                Text(
                                    text = accolade.cigarOfTheYearYear.orEmpty(),
                                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                    color = Color(0xFF1A120B),
                                    maxLines = 1,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CigarMiniStripPreview(
    cigar: Cigar,
    modifier: Modifier = Modifier,
) {
    val palette = remember(cigar.brand) { bandPaletteForCigar(cigar) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Canvas(
            modifier = Modifier
                .weight(1f)
                .height(30.dp),
        ) {
            drawHandRolledCigar(cigar = cigar, palette = palette, verticalPaddingFactor = 0.20f)
        }
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(palette.primary)
                .border(1.2.dp, palette.trimGold, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = palette.monogram.take(2),
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                fontWeight = FontWeight.Bold,
                color = palette.trimGold,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CigarAccoladesCard(
    cigar: Cigar,
    accolade: CigarAccolade,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("cigar_accolades_card_${cigar.id}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (accolade.cigarAficionadoRating != null) {
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.16f)
                    } else {
                        MaterialTheme.colorScheme.surfaceContainerHigh
                    },
                ) {
                    Column(
                        modifier = Modifier
                            .width(74.dp)
                            .padding(vertical = 10.dp, horizontal = 6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = accolade.cigarAficionadoRating?.toString() ?: "N/R",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (accolade.cigarAficionadoRating != null) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            },
                        )
                        Text(
                            text = if (accolade.cigarAficionadoRating != null) "CA PTS" else "UNRATED",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Verified,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp),
                        )
                        Text(
                            text = accolade.ratingSourceLabel,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                    if (!accolade.ratingYear.isNullOrBlank()) {
                        Text(
                            text = "Issue / Benchmark: ${accolade.ratingYear}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                    if (!accolade.ratingHighlight.isNullOrBlank()) {
                        Text(
                            text = accolade.ratingHighlight,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }

            if (accolade.isCigarOfTheYear) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = StarGold.copy(alpha = 0.16f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, StarGold.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.EmojiEvents,
                            contentDescription = "Cigar Aficionado Cigar of the Year Winner",
                            tint = StarGold,
                            modifier = Modifier.size(24.dp),
                        )
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                        ) {
                            Text(
                                text = "🏆 Cigar Aficionado #1 Cigar of the Year (${accolade.cigarOfTheYearYear})",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                            Text(
                                text = "Awarded top blind-tasting honors among the magazine's annual Top 25 Cigars of the Year.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }
            }

            if (accolade.hasCelebrityNotes) {
                val matchedProfiles = remember(cigar.id, accolade.celebrityNotes) {
                    com.example.data.catalog.CigarCelebrityDirectory.profilesForNotes(
                        notes = accolade.celebrityNotes,
                        brand = cigar.brand,
                    )
                }
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.size(18.dp),
                        )
                        Text(
                            text = if (accolade.hasInternationalCelebrity) {
                                "🌍 Celebrity Aficionados & Global Icons"
                            } else {
                                "🌟 Celebrity Aficionados & Endorsements"
                            },
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                    if (matchedProfiles.isNotEmpty()) {
                        CelebrityCartoonPortraitStrip(profiles = matchedProfiles)
                    }
                    accolade.celebrityNotes.forEach { note ->
                        Text(
                            text = "• $note",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CigarAficionadoScorePill(
    accolade: CigarAccolade,
    modifier: Modifier = Modifier,
) {
    val label = if (accolade.cigarAficionadoRating != null) {
        val isAvg = accolade.ratingSourceLabel.contains("Avg", ignoreCase = true)
        if (isAvg) "⭐ CA ~${accolade.cigarAficionadoRating}" else "⭐ CA ${accolade.cigarAficionadoRating}"
    } else {
        "CA Unrated"
    }
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
        color = if (accolade.cigarAficionadoRating != null) {
            MaterialTheme.colorScheme.primary.copy(alpha = 0.16f)
        } else {
            MaterialTheme.colorScheme.surfaceContainerHigh
        },
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = if (accolade.cigarAficionadoRating != null) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        )
    }
}

@Composable
fun CigarOfTheYearPill(
    year: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
        color = StarGold.copy(alpha = 0.22f),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = "🏆 #1 COTY $year",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Composable
fun CelebrityEndorsementPill(
    accolade: CigarAccolade,
    modifier: Modifier = Modifier,
) {
    val celebLabel = remember(accolade) { CigarAccoladesCatalog.shortCelebrityLabel(accolade) } ?: return
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
        color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.72f),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = if (accolade.hasInternationalCelebrity) "🌍 $celebLabel" else "🌟 $celebLabel",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
