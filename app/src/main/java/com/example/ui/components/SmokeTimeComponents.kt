package com.example.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.pairing.SmokeTimeAdvisor
import com.example.model.Cigar
import com.example.model.SmokeTimeOfDay

private fun timeAccentColor(time: SmokeTimeOfDay): Color = when (time) {
    SmokeTimeOfDay.MORNING -> Color(0xFFE09F3E)
    SmokeTimeOfDay.MIDDAY -> Color(0xFF4EA8DE)
    SmokeTimeOfDay.AFTERNOON -> Color(0xFFE07A5F)
    SmokeTimeOfDay.EVENING -> Color(0xFF9D7FEA)
    SmokeTimeOfDay.LATE_NIGHT -> Color(0xFFD9A326)
}

@Composable
fun SmokeTimePill(
    cigar: Cigar,
    modifier: Modifier = Modifier,
    showWindow: Boolean = false,
) {
    val rec = remember(cigar.id) { SmokeTimeAdvisor.recommendFor(cigar) }
    val accent = timeAccentColor(rec.primaryTime)

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
        color = accent.copy(alpha = 0.16f),
    ) {
        Text(
            text = if (showWindow) {
                "${rec.primaryTime.emoji} ${rec.primaryTime.label} (${rec.windowLabel})"
            } else {
                "${rec.primaryTime.emoji} ${rec.primaryTime.label}"
            },
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        )
    }
}

@Composable
fun SmokeTimeGuidanceCard(
    cigar: Cigar,
    modifier: Modifier = Modifier,
) {
    val rec = remember(cigar.id) { SmokeTimeAdvisor.recommendFor(cigar) }
    val accent = timeAccentColor(rec.primaryTime)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("smoke_time_card_${cigar.id}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, accent.copy(alpha = 0.35f), MaterialTheme.shapes.medium)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = accent.copy(alpha = 0.20f),
                    ) {
                        Text(
                            text = rec.primaryTime.emoji,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        )
                    }
                    Column {
                        Text(
                            text = "Suggested Time of Day",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Text(
                            text = rec.primaryTime.label,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                ) {
                    Text(
                        text = "🕐 ${rec.windowLabel}",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 9.dp, vertical = 5.dp),
                    )
                }
            }

            Text(
                text = rec.rationale,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = rec.idealDrinkHint,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    rec.secondaryTime?.let { alt ->
                        Text(
                            text = "Also shines during: ${alt.emoji} ${alt.label} (${alt.windowRange})",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }
        }
    }
}
