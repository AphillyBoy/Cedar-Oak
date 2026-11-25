package com.example.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.pairing.CigarSubstituteEngine
import com.example.data.pairing.SubstituteFilter
import com.example.model.Beverage
import com.example.model.Cigar
import com.example.model.CigarSubstituteMatch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CigarMatchGuideCard(
    targetCigar: Cigar,
    pairedBeverage: Beverage? = null,
    onSelectSubstituteCigar: (cigarId: String) -> Unit,
    onOpenFullGuide: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var activeFilter by remember(targetCigar.id) {
        mutableStateOf(
            if (CigarSubstituteEngine.isCuban(targetCigar)) {
                SubstituteFilter.NON_CUBAN
            } else {
                SubstituteFilter.ALL
            }
        )
    }

    val substitutes = remember(targetCigar.id, pairedBeverage?.id, activeFilter) {
        CigarSubstituteEngine.findSubstitutes(
            target = targetCigar,
            pairedBeverage = pairedBeverage,
            filter = activeFilter,
            limit = 4,
        )
    }

    val tobacconistTip = remember(targetCigar.id, pairedBeverage?.id) {
        CigarSubstituteEngine.buildTobacconistAskTip(targetCigar, pairedBeverage)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("cigar_match_guide_card_${targetCigar.id}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Storefront,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp),
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "🏪 Unavailable at the Shop? Cigar Match Guide",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = if (pairedBeverage != null) {
                            "Closest in-stock backups matched to ${targetCigar.name} & re-scored for ${pairedBeverage.name}."
                        } else {
                            "Closest point-of-purchase alternatives across distinct brands if ${targetCigar.name} is sold out."
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

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
                        text = "🗣️ WHAT TO ASK YOUR TOBACCONIST",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Text(
                        text = tobacconistTip,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(SubstituteFilter.entries) { filter ->
                    FilterChip(
                        selected = activeFilter == filter,
                        onClick = { activeFilter = filter },
                        label = { Text(filter.label) },
                    )
                }
            }

            substitutes.forEach { match ->
                SubstituteMatchItemCard(
                    match = match,
                    pairedBeverage = pairedBeverage,
                    onClick = { onSelectSubstituteCigar(match.substitute.id) },
                )
            }

            OutlinedButton(
                onClick = onOpenFullGuide,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("open_full_cigar_match_guide"),
            ) {
                Icon(Icons.Filled.SwapHoriz, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Search Shop Shelf & Compare All Matches")
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SubstituteMatchItemCard(
    match: CigarSubstituteMatch,
    pairedBeverage: Beverage?,
    onClick: () -> Unit,
) {
    val cigar = match.substitute
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .testTag("substitute_item_${cigar.id}"),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                FlowRow(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.16f),
                    ) {
                        Text(
                            text = "🎯 ${match.similarityScore}% SIMILAR",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.72f),
                    ) {
                        Text(
                            text = match.availabilityBadge,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        )
                    }
                    SmokeTimePill(cigar = cigar)
                    if (match.pairingScoreWithBeverage != null && pairedBeverage != null) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.secondaryContainer,
                        ) {
                            Text(
                                text = "${pairedBeverage.category.emoji} ${match.pairingScoreWithBeverage}% w/ Drink",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            )
                        }
                    }
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Open substitute cigar",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp),
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = "${cigar.brand} ${cigar.name}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = "${cigar.origin} · ${cigar.wrapper} · ${cigar.strength.emoji} ${cigar.strength.label} · ⏱️ ~${cigar.smokeTimeMinutes} min",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Text(
                text = match.matchReason,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.88f),
            )

            if (match.sharedNotes.isNotEmpty()) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    match.sharedNotes.forEach { note ->
                        FlavorNoteTag(text = note)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CigarMatchGuideSheet(
    targetCigar: Cigar,
    pairedBeverage: Beverage? = null,
    onSelectSubstituteCigar: (cigarId: String) -> Unit,
    onDismiss: () -> Unit,
) {
    var shelfQuery by remember { mutableStateOf("") }
    var activeFilter by remember(targetCigar.id) {
        mutableStateOf(
            if (CigarSubstituteEngine.isCuban(targetCigar)) {
                SubstituteFilter.NON_CUBAN
            } else {
                SubstituteFilter.ALL
            }
        )
    }

    val matches = remember(targetCigar.id, pairedBeverage?.id, activeFilter, shelfQuery) {
        CigarSubstituteEngine.findSubstitutes(
            target = targetCigar,
            pairedBeverage = pairedBeverage,
            filter = activeFilter,
            shelfQuery = shelfQuery,
            limit = 20,
        )
    }

    val tobacconistTip = remember(targetCigar.id, pairedBeverage?.id) {
        CigarSubstituteEngine.buildTobacconistAskTip(targetCigar, pairedBeverage)
    }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 620.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(
                    text = "🏪 Point-of-Purchase Cigar Match Guide",
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = "Alternatives for ${targetCigar.brand} ${targetCigar.name}" +
                        (pairedBeverage?.let { " with ${it.name}" } ?: ""),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                )
            }

            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = tobacconistTip,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(12.dp),
                )
            }

            OutlinedTextField(
                value = shelfQuery,
                onValueChange = { shelfQuery = it },
                placeholder = { Text("Type a brand or cigar on the shop shelf to compare…") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                trailingIcon = {
                    if (shelfQuery.isNotEmpty()) {
                        IconButton(onClick = { shelfQuery = "" }) {
                            Icon(Icons.Filled.Clear, contentDescription = "Clear shelf search")
                        }
                    }
                },
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("match_guide_shelf_search"),
            )

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(SubstituteFilter.entries) { filter ->
                    FilterChip(
                        selected = activeFilter == filter,
                        onClick = { activeFilter = filter },
                        label = { Text(filter.label) },
                    )
                }
            }

            LazyColumn(
                contentPadding = PaddingValues(bottom = 28.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.weight(1f, fill = false),
            ) {
                items(matches, key = { it.substitute.id }) { match ->
                    SubstituteMatchItemCard(
                        match = match,
                        pairedBeverage = pairedBeverage,
                        onClick = {
                            onDismiss()
                            onSelectSubstituteCigar(match.substitute.id)
                        },
                    )
                }
            }
        }
    }
}
