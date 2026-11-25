package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.catalog.PairingCatalog
import com.example.model.PairingReview
import com.example.model.PairingStrategy
import com.example.model.PairingSuggestion
import com.example.model.SavedPairing
import com.example.ui.components.AverageRatingBadge
import com.example.ui.components.BeveragePickerSheet
import com.example.ui.components.BeverageProofBadge
import com.example.ui.components.CigarMatchGuideSheet
import com.example.ui.components.CigarPickerSheet
import com.example.ui.components.FlavorNoteTag
import com.example.ui.components.FlavorRadarChart
import com.example.ui.components.MatchScorePill
import com.example.ui.components.PairingDbNotesSheet
import com.example.ui.components.PairingDbSearchAndFilters
import com.example.ui.components.PairingDbStatsHeader
import com.example.ui.components.PairingLogicBreakdownCard
import com.example.ui.components.PairingStrategySelectorRow
import com.example.ui.components.ReviewItemCard
import com.example.ui.components.SavedPairingDbCard

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SynergyLabScreen(
    selectedCigarId: String,
    selectedBeverageId: String,
    pairingStrategy: PairingStrategy,
    labSuggestion: PairingSuggestion?,
    savedPairings: List<SavedPairing>,
    savedPairingIds: Set<String>,
    userReviews: List<PairingReview>,
    allReviews: List<PairingReview>,
    onSelectCigar: (String) -> Unit,
    onSelectBeverage: (String) -> Unit,
    onPairingStrategyChange: (PairingStrategy) -> Unit,
    onOpenPairing: (cigarId: String, beverageId: String) -> Unit,
    onToggleSavePairing: (cigarId: String, beverageId: String) -> Unit,
    onToggleSavedFavorite: (pairingId: String) -> Unit,
    onLogPairingSession: (pairingId: String) -> Unit,
    onUpdateSavedNotes: (pairingId: String, notes: String, occasion: String) -> Unit,
    onDeleteSavedPairing: (pairingId: String) -> Unit,
    onOpenRateComposer: (cigarId: String, beverageId: String, existingReview: PairingReview?) -> Unit,
    onDeleteReview: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    var activeTab by rememberSaveable { mutableIntStateOf(0) }
    var showCigarPicker by remember { mutableStateOf(false) }
    var showBeveragePicker by remember { mutableStateOf(false) }
    var showMatchGuideSheet by remember { mutableStateOf(false) }

    // Pairing Room DB tab state
    var dbQuery by rememberSaveable { mutableStateOf("") }
    var dbOnlyFavorites by rememberSaveable { mutableStateOf(false) }
    var dbOccasionFilter by rememberSaveable { mutableStateOf<String?>(null) }
    var editingSavedPairing by remember { mutableStateOf<SavedPairing?>(null) }

    val filteredSavedPairings = remember(savedPairings, dbQuery, dbOnlyFavorites, dbOccasionFilter) {
        val q = dbQuery.trim().lowercase()
        savedPairings.filter { saved ->
            val matchesFav = !dbOnlyFavorites || saved.isFavorite
            val matchesOccasion = dbOccasionFilter == null || saved.occasionTag == dbOccasionFilter
            val matchesQuery = q.isEmpty() ||
                saved.cigarName.lowercase().contains(q) ||
                saved.beverageName.lowercase().contains(q) ||
                saved.occasionTag.lowercase().contains(q) ||
                saved.pairingStyle.lowercase().contains(q) ||
                saved.personalNotes.lowercase().contains(q)
            matchesFav && matchesOccasion && matchesQuery
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "⚗️ Synergy Lab & Pairing DB",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = "Compare ${PairingCatalog.cigars.size} cigars × ${PairingCatalog.beverages.size} drinks, manage your Room Pairing DB (${savedPairings.size} saved), or log reviews.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        item {
            SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                val tabs = listOf("⚗️ Lab", "💾 Pairing DB", "⭐ Reviews")
                tabs.forEachIndexed { idx, title ->
                    SegmentedButton(
                        selected = activeTab == idx,
                        onClick = { activeTab = idx },
                        shape = SegmentedButtonDefaults.itemShape(index = idx, count = tabs.size),
                        modifier = Modifier.testTag("synergy_tab_$idx"),
                    ) {
                        Text(
                            text = title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }

        if (activeTab == 0) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "1. Select Cigar",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    TextButton(onClick = { showCigarPicker = true }) {
                        Icon(Icons.Filled.Search, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Search ${PairingCatalog.cigars.size}")
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(PairingCatalog.cigars, key = { it.id }) { cigar ->
                        val selected = cigar.id == selectedCigarId
                        Surface(
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.small)
                                .clickable { onSelectCigar(cigar.id) }
                                .border(
                                    width = if (selected) 1.5.dp else 1.dp,
                                    color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                                    shape = MaterialTheme.shapes.small,
                                ),
                            color = if (selected) {
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f)
                            } else {
                                MaterialTheme.colorScheme.surfaceContainerLow
                            },
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(CircleShape)
                                        .background(cigar.wrapperShade.swatchColor),
                                )
                                Text(
                                    text = "${cigar.brand} ${cigar.name}",
                                    style = MaterialTheme.typography.labelLarge,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "2. Select Beverage",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    TextButton(onClick = { showBeveragePicker = true }) {
                        Icon(Icons.Filled.Search, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Search ${PairingCatalog.beverages.size}")
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(PairingCatalog.beverages, key = { it.id }) { bev ->
                        val selected = bev.id == selectedBeverageId
                        Surface(
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.small)
                                .clickable { onSelectBeverage(bev.id) }
                                .border(
                                    width = if (selected) 1.5.dp else 1.dp,
                                    color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                                    shape = MaterialTheme.shapes.small,
                                ),
                            color = if (selected) {
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f)
                            } else {
                                MaterialTheme.colorScheme.surfaceContainerLow
                            },
                        ) {
                            Column(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(2.dp),
                            ) {
                                Text(
                                    text = bev.name,
                                    style = MaterialTheme.typography.labelLarge,
                                    maxLines = 1,
                                )
                                Text(
                                    text = if (bev.isAlcoholic) bev.abvLabel else "Zero-Proof · ${bev.category.label}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (bev.isAlcoholic) {
                                        MaterialTheme.colorScheme.secondary
                                    } else {
                                        MaterialTheme.colorScheme.tertiary
                                    },
                                )
                            }
                        }
                    }
                }
            }

            item {
                PairingStrategySelectorRow(
                    selectedStrategy = pairingStrategy,
                    onStrategySelected = onPairingStrategyChange,
                )
            }

            if (labSuggestion != null) {
                val isSavedInDb = labSuggestion.pairingId in savedPairingIds
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                FlowRow(
                                    modifier = Modifier.weight(1f),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(6.dp),
                                ) {
                                    MatchScorePill(score = labSuggestion.matchScore)
                                    BeverageProofBadge(beverage = labSuggestion.beverage)
                                }
                                AverageRatingBadge(
                                    averageRating = labSuggestion.averageRating,
                                    reviewCount = labSuggestion.reviewCount,
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.CompareArrows,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                )
                                Text(
                                    text = "${labSuggestion.cigar.name} × ${labSuggestion.beverage.name}",
                                    style = MaterialTheme.typography.titleLarge,
                                )
                            }

                            FlavorRadarChart(
                                primaryAxes = labSuggestion.cigar.flavorAxes,
                                primaryLabel = labSuggestion.cigar.name,
                                primaryColor = MaterialTheme.colorScheme.primary,
                                secondaryAxes = labSuggestion.beverage.flavorAxes,
                                secondaryLabel = labSuggestion.beverage.name,
                                secondaryColor = if (labSuggestion.beverage.isAlcoholic) {
                                    MaterialTheme.colorScheme.secondary
                                } else {
                                    MaterialTheme.colorScheme.tertiary
                                },
                            )

                            Text(
                                text = labSuggestion.whyItWorks,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )

                            if (labSuggestion.bridgeNotes.isNotEmpty()) {
                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalArrangement = Arrangement.spacedBy(6.dp),
                                ) {
                                    labSuggestion.bridgeNotes.forEach { bridge ->
                                        FlavorNoteTag(text = "Bridge: $bridge")
                                    }
                                }
                            }

                            Text(
                                text = "Serving Ritual: ${labSuggestion.servingTip}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary,
                            )

                            Button(
                                onClick = {
                                    onToggleSavePairing(
                                        labSuggestion.cigar.id,
                                        labSuggestion.beverage.id,
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .testTag("lab_save_db_button"),
                            ) {
                                Icon(
                                    imageVector = if (isSavedInDb) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp),
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(if (isSavedInDb) "💾 Saved in Pairing DB (Tap to Remove)" else "💾 Save to Pairing Room DB")
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                OutlinedButton(
                                    onClick = {
                                        onOpenRateComposer(
                                            labSuggestion.cigar.id,
                                            labSuggestion.beverage.id,
                                            null,
                                        )
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(48.dp)
                                        .testTag("lab_rate_button"),
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.RateReview,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp),
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Rate")
                                }
                                OutlinedButton(
                                    onClick = {
                                        onOpenPairing(
                                            labSuggestion.cigar.id,
                                            labSuggestion.beverage.id,
                                        )
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(48.dp),
                                ) {
                                    Text("Full Details")
                                }
                            }

                            TextButton(
                                onClick = { showMatchGuideSheet = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("lab_cigar_match_guide_button"),
                            ) {
                                Icon(Icons.Filled.SwapHoriz, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Cigar Out of Stock? Open Match Guide")
                            }
                        }
                    }
                }

                labSuggestion.breakdown?.let { breakdown ->
                    item {
                        PairingLogicBreakdownCard(breakdown = breakdown)
                    }
                }
            }
        } else if (activeTab == 1) {
            item {
                PairingDbStatsHeader(savedPairings = savedPairings)
            }

            item {
                PairingDbSearchAndFilters(
                    query = dbQuery,
                    onlyFavorites = dbOnlyFavorites,
                    selectedOccasion = dbOccasionFilter,
                    onQueryChange = { dbQuery = it },
                    onToggleFavorites = { dbOnlyFavorites = !dbOnlyFavorites },
                    onSelectOccasion = { dbOccasionFilter = it },
                )
            }

            item {
                Text(
                    text = "Showing ${filteredSavedPairings.size} of ${savedPairings.size} saved pairings",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            if (filteredSavedPairings.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            Text(
                                text = "No matching saved pairings",
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Text(
                                text = "Use the Comparator or tap the bookmark button on any pairing profile to save custom combinations into your Room database.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }
            } else {
                items(filteredSavedPairings, key = { "db_${it.pairingId}" }) { saved ->
                    SavedPairingDbCard(
                        saved = saved,
                        onOpenPairing = { onOpenPairing(saved.cigarId, saved.beverageId) },
                        onToggleFavorite = { onToggleSavedFavorite(saved.pairingId) },
                        onLogSession = { onLogPairingSession(saved.pairingId) },
                        onEditNotes = { editingSavedPairing = saved },
                        onDelete = { onDeleteSavedPairing(saved.pairingId) },
                    )
                }
            }
        } else {
            item {
                Text(
                    text = "Your Submitted Reviews (${userReviews.size})",
                    style = MaterialTheme.typography.titleLarge,
                )
            }

            if (userReviews.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            Text(
                                text = "No personal reviews yet",
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Text(
                                text = "Open any pairing or use the Flavor Comparator to leave a 1–5 star rating and tasting review.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }
            } else {
                items(userReviews, key = { "user_${it.id}" }) { review ->
                    val cigar = PairingCatalog.cigarById(review.cigarId)
                    val bev = PairingCatalog.beverageById(review.beverageId)
                    val header = if (cigar != null && bev != null) {
                        "${cigar.name} × ${bev.name}"
                    } else null
                    ReviewItemCard(
                        review = review,
                        pairingHeader = header,
                        onEdit = {
                            onOpenRateComposer(review.cigarId, review.beverageId, review)
                        },
                        onDelete = { onDeleteReview(review.id) },
                        onClick = { onOpenPairing(review.cigarId, review.beverageId) },
                    )
                }
            }

            item {
                Text(
                    text = "Recent Lounge Reviews (${allReviews.size})",
                    style = MaterialTheme.typography.titleLarge,
                )
            }

            items(allReviews.take(15), key = { "all_${it.id}" }) { review ->
                val cigar = PairingCatalog.cigarById(review.cigarId)
                val bev = PairingCatalog.beverageById(review.beverageId)
                val header = if (cigar != null && bev != null) {
                    "${cigar.name} × ${bev.name}"
                } else null
                ReviewItemCard(
                    review = review,
                    pairingHeader = header,
                    onEdit = if (review.isUserOwn) {
                        { onOpenRateComposer(review.cigarId, review.beverageId, review) }
                    } else null,
                    onDelete = if (review.isUserOwn) {
                        { onDeleteReview(review.id) }
                    } else null,
                    onClick = { onOpenPairing(review.cigarId, review.beverageId) },
                )
            }
        }
    }

    if (showCigarPicker) {
        CigarPickerSheet(
            selectedCigarId = selectedCigarId,
            onSelectCigar = onSelectCigar,
            onDismiss = { showCigarPicker = false },
        )
    }

    if (showBeveragePicker) {
        BeveragePickerSheet(
            selectedBeverageId = selectedBeverageId,
            onSelectBeverage = onSelectBeverage,
            onDismiss = { showBeveragePicker = false },
        )
    }

    if (showMatchGuideSheet && labSuggestion != null) {
        CigarMatchGuideSheet(
            targetCigar = labSuggestion.cigar,
            pairedBeverage = labSuggestion.beverage,
            onSelectSubstituteCigar = onSelectCigar,
            onDismiss = { showMatchGuideSheet = false },
        )
    }

    editingSavedPairing?.let { entry ->
        PairingDbNotesSheet(
            cigarName = entry.cigarName,
            beverageName = entry.beverageName,
            initialNotes = entry.personalNotes,
            initialOccasion = entry.occasionTag,
            onDismiss = { editingSavedPairing = null },
            onSave = { notes, occasion ->
                onUpdateSavedNotes(entry.pairingId, notes, occasion)
                editingSavedPairing = null
            },
        )
    }
}
