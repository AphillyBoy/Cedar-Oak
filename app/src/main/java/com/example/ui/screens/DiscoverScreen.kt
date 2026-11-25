package com.example.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.catalog.CiSuperstoreDirectory
import com.example.data.catalog.CigarAccoladesCatalog
import com.example.data.catalog.CigarCelebrityDirectory
import com.example.data.catalog.PairingCatalog
import com.example.data.pairing.SmokeTimeAdvisor
import com.example.model.BeverageFilter
import com.example.model.EmailAlertSubscription
import com.example.model.PairingStrategy
import com.example.model.PairingSuggestion
import com.example.ui.components.BeveragePickerSheet
import com.example.ui.components.CelebrityCartoonMiniChip
import com.example.ui.components.CelebrityLoungeGallerySheet
import com.example.ui.components.CigarMatchGuideSheet
import com.example.ui.components.CigarPickerSheet
import com.example.ui.components.EventsEmailAlertSignupCard
import com.example.ui.components.LoungeFunFactsCard
import com.example.ui.components.PairingStrategySelectorRow
import com.example.ui.components.PairingSuggestionCard
import com.example.ui.components.SmokeTimePill
import com.example.ui.theme.StarGold
import com.example.ui.viewmodel.MatcherDirection

@Composable
fun DiscoverScreen(
    matcherDirection: MatcherDirection,
    selectedCigarId: String,
    selectedBeverageId: String,
    beverageFilter: BeverageFilter,
    pairingStrategy: PairingStrategy,
    quickMatches: List<PairingSuggestion>,
    featuredPairings: List<PairingSuggestion>,
    emailSubscription: EmailAlertSubscription,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onDirectionChange: (MatcherDirection) -> Unit,
    onSelectCigar: (String) -> Unit,
    onSelectBeverage: (String) -> Unit,
    onBeverageFilterChange: (BeverageFilter) -> Unit,
    onPairingStrategyChange: (PairingStrategy) -> Unit,
    onOpenQuiz: () -> Unit,
    onOpenSuperstoreHub: () -> Unit,
    onOpenCigar: (cigarId: String) -> Unit,
    onExploreCelebrityBrand: (brandOrName: String) -> Unit,
    onOpenPairing: (cigarId: String, beverageId: String) -> Unit,
    onQuickRate: (cigarId: String, beverageId: String) -> Unit,
    onSaveEmailSubscription: (email: String, topics: Set<String>, preferredStoreId: String) -> Unit,
    onUnsubscribeEmail: () -> Unit,
    onShowMessage: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showCigarPicker by remember { mutableStateOf(false) }
    var showBeveragePicker by remember { mutableStateOf(false) }
    var showMatchGuide by remember { mutableStateOf(false) }
    var showCelebrityWall by remember { mutableStateOf(false) }

    val currentTimeWindow = remember { SmokeTimeAdvisor.currentTimeOfDay() }
    val rightNowCigars = remember(currentTimeWindow) {
        SmokeTimeAdvisor.topCigarsForTime(currentTimeWindow, limit = 8)
    }
    val spotlightCelebrities = remember { CigarCelebrityDirectory.featuredSpotlightProfiles() }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        item {
            LoungeHeroHeader(
                isDarkTheme = isDarkTheme,
                onToggleTheme = onToggleTheme,
            )
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .clickable(onClick = onOpenQuiz)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.45f),
                        shape = MaterialTheme.shapes.medium,
                    )
                    .testTag("discover_pairing_quiz_card"),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.42f),
                ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.22f),
                    ) {
                        Text(
                            text = "🎯",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                    ) {
                        Text(
                            text = "Take the Sommelier Pairing Quiz",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = "5 quick questions on time of day 🕐, strength 🔥, flavors & pour 🥃 for instant custom picks.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primary,
                    ) {
                        Text(
                            text = "Start →",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        )
                    }
                }
            }
        }

        // Live Time-of-Day Smart Picks ("Best Smokes for Right Now")
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = "${currentTimeWindow.emoji} Best Smokes for ${currentTimeWindow.label}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Text(
                        text = "🕐 Tailored for right now (${currentTimeWindow.windowRange}) · tap any cigar for pairings & time notes.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.testTag("discover_right_now_carousel"),
                ) {
                    items(rightNowCigars, key = { "now_${it.id}" }) { cigar ->
                        val accolade = remember(cigar.id) { CigarAccoladesCatalog.forCigar(cigar) }
                        Card(
                            modifier = Modifier
                                .width(204.dp)
                                .clip(MaterialTheme.shapes.medium)
                                .clickable { onOpenCigar(cigar.id) }
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.outlineVariant,
                                    shape = MaterialTheme.shapes.medium,
                                )
                                .testTag("right_now_cigar_${cigar.id}"),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            ),
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp),
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    SmokeTimePill(cigar = cigar)
                                    accolade.cigarAficionadoRating?.let { score ->
                                        Surface(
                                            shape = RoundedCornerShape(6.dp),
                                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.16f),
                                        ) {
                                            Text(
                                                text = if (accolade.isCigarOfTheYear) "🏆 $score" else "⭐ $score",
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.primary,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            )
                                        }
                                    }
                                }
                                Text(
                                    text = cigar.brand,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                                Text(
                                    text = cigar.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(9.dp)
                                            .clip(CircleShape)
                                            .background(cigar.wrapperShade.swatchColor),
                                    )
                                    Text(
                                        text = "${cigar.strength.emoji} ${cigar.strength.label} · ⏱️ ${cigar.smokeTimeMinutes}m",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Celebrity Cartoon Lounge Spotlight (Local + Worldwide)
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                    ) {
                        Text(
                            text = "🎭 Celebrity Cartoon Lounge",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                        Text(
                            text = "🌍 ${CigarCelebrityDirectory.allCelebrityProfiles.size} local & worldwide icons · tap to explore their cigars.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    TextButton(
                        onClick = { showCelebrityWall = true },
                        modifier = Modifier.testTag("discover_open_celebrity_wall_button"),
                    ) {
                        Text("All ${CigarCelebrityDirectory.allCelebrityProfiles.size} →")
                    }
                }

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.testTag("discover_celebrity_spotlight_row"),
                ) {
                    items(spotlightCelebrities, key = { "spot_${it.id}" }) { profile ->
                        CelebrityCartoonMiniChip(
                            profile = profile,
                            modifier = Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .clickable { onExploreCelebrityBrand(profile.favoriteBrand) }
                                .testTag("discover_celeb_chip_${profile.id}"),
                        )
                    }
                }
            }
        }

        // Cigars International Superstore Map, Website, Socials & Google Reviews Hub Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .clickable(onClick = onOpenSuperstoreHub)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.45f),
                        shape = MaterialTheme.shapes.medium,
                    )
                    .testTag("discover_ci_superstores_card"),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.35f),
                ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.22f),
                    ) {
                        Text(
                            text = "🗺️",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                    ) {
                        Text(
                            text = "CI Superstore Map, Socials & Reviews",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = "Quick map to all ${CiSuperstoreDirectory.superstores.size} Superstores, official website, Instagram/FB/X/YT & Google review links.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.tertiary,
                    ) {
                        Text(
                            text = "Open →",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        )
                    }
                }
            }
        }

        // Interactive Cigar & Drink Fun Facts Card
        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                LoungeFunFactsCard()
            }
        }

        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.AutoAwesome,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp),
                    )
                    Text(
                        text = "✨ Instant Sommelier Matcher",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }

                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    MatcherDirection.entries.forEachIndexed { index, dir ->
                        SegmentedButton(
                            selected = matcherDirection == dir,
                            onClick = { onDirectionChange(dir) },
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = MatcherDirection.entries.size,
                            ),
                            modifier = Modifier.testTag("direction_${dir.name}"),
                        ) {
                            Text(
                                text = dir.label,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }

                if (matcherDirection == MatcherDirection.CIGAR_TO_DRINK) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        val activeCigar = PairingCatalog.cigarById(selectedCigarId)
                        Text(
                            text = activeCigar?.let { "${it.brand} ${it.name}" } ?: "Select Cigar",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f),
                        )
                        TextButton(
                            onClick = { showMatchGuide = true },
                            modifier = Modifier.testTag("discover_match_guide_button"),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.SwapHoriz,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Sold Out?")
                        }
                        TextButton(
                            onClick = { showCigarPicker = true },
                            modifier = Modifier.testTag("browse_all_cigars_button"),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("All ${PairingCatalog.cigars.size}")
                        }
                    }

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        items(PairingCatalog.cigars, key = { it.id }) { cigar ->
                            val selected = cigar.id == selectedCigarId
                            Card(
                                modifier = Modifier
                                    .width(172.dp)
                                    .clip(MaterialTheme.shapes.medium)
                                    .clickable { onSelectCigar(cigar.id) }
                                    .border(
                                        width = if (selected) 1.5.dp else 1.dp,
                                        color = if (selected) {
                                            MaterialTheme.colorScheme.primary
                                        } else {
                                            MaterialTheme.colorScheme.outlineVariant
                                        },
                                        shape = MaterialTheme.shapes.medium,
                                    )
                                    .testTag("matcher_cigar_${cigar.id}"),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (selected) {
                                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                                    } else {
                                        MaterialTheme.colorScheme.surfaceContainerLow
                                    },
                                ),
                            ) {
                                Column(
                                    modifier = Modifier.padding(12.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp),
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(10.dp)
                                                .clip(CircleShape)
                                                .background(cigar.wrapperShade.swatchColor),
                                        )
                                        Text(
                                            text = cigar.strength.label,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.primary,
                                        )
                                    }
                                    Text(
                                        text = cigar.brand,
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                    Text(
                                        text = cigar.name,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                            }
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        BeverageFilter.entries.forEach { filter ->
                            FilterChip(
                                selected = beverageFilter == filter,
                                onClick = { onBeverageFilterChange(filter) },
                                label = { Text("${filter.emoji} ${filter.label}") },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = if (filter == BeverageFilter.NON_ALCOHOLIC) {
                                        MaterialTheme.colorScheme.tertiaryContainer
                                    } else {
                                        MaterialTheme.colorScheme.primaryContainer
                                    },
                                ),
                                modifier = Modifier.testTag("discover_filter_${filter.name}"),
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        val activeBev = PairingCatalog.beverageById(selectedBeverageId)
                        Text(
                            text = activeBev?.name ?: "Select Beverage",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f),
                        )
                        TextButton(
                            onClick = { showBeveragePicker = true },
                            modifier = Modifier.testTag("browse_all_beverages_button"),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("All ${PairingCatalog.beverages.size} Drinks")
                        }
                    }

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        items(PairingCatalog.beverages, key = { it.id }) { bev ->
                            val selected = bev.id == selectedBeverageId
                            Card(
                                modifier = Modifier
                                    .width(184.dp)
                                    .clip(MaterialTheme.shapes.medium)
                                    .clickable { onSelectBeverage(bev.id) }
                                    .border(
                                        width = if (selected) 1.5.dp else 1.dp,
                                        color = if (selected) {
                                            MaterialTheme.colorScheme.primary
                                        } else {
                                            MaterialTheme.colorScheme.outlineVariant
                                        },
                                        shape = MaterialTheme.shapes.medium,
                                    )
                                    .testTag("matcher_beverage_${bev.id}"),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (selected) {
                                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                                    } else {
                                        MaterialTheme.colorScheme.surfaceContainerLow
                                    },
                                ),
                            ) {
                                Column(
                                    modifier = Modifier.padding(12.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp),
                                ) {
                                    Text(
                                        text = if (bev.isAlcoholic) bev.abvLabel else "Zero-Proof",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = if (bev.isAlcoholic) {
                                            MaterialTheme.colorScheme.primary
                                        } else {
                                            MaterialTheme.colorScheme.tertiary
                                        },
                                    )
                                    Text(
                                        text = bev.category.label,
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                    Text(
                                        text = bev.name,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                            }
                        }
                    }
                }

                PairingStrategySelectorRow(
                    selectedStrategy = pairingStrategy,
                    onStrategySelected = onPairingStrategyChange,
                )
            }
        }

        items(quickMatches, key = { "quick_${it.pairingId}" }) { suggestion ->
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                PairingSuggestionCard(
                    suggestion = suggestion,
                    highlightBeverageFirst = matcherDirection == MatcherDirection.CIGAR_TO_DRINK,
                    onOpenPairing = { onOpenPairing(suggestion.cigar.id, suggestion.beverage.id) },
                    onQuickRate = { onQuickRate(suggestion.cigar.id, suggestion.beverage.id) },
                )
            }
        }

        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = StarGold,
                        modifier = Modifier.size(20.dp),
                    )
                    Text(
                        text = "🏆 Top-Rated Community Pairings",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
                Text(
                    text = "⭐ Ranked by live 1–5 star ratings across alcoholic & zero-proof picks.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        items(featuredPairings.take(8), key = { "featured_${it.pairingId}" }) { suggestion ->
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                PairingSuggestionCard(
                    suggestion = suggestion,
                    onOpenPairing = { onOpenPairing(suggestion.cigar.id, suggestion.beverage.id) },
                    onQuickRate = { onQuickRate(suggestion.cigar.id, suggestion.beverage.id) },
                )
            }
        }

        // Events & Specials Email Alert Signup right on the Home screen
        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                EventsEmailAlertSignupCard(
                    subscription = emailSubscription,
                    onSaveSubscription = onSaveEmailSubscription,
                    onUnsubscribe = onUnsubscribeEmail,
                    onShowMessage = onShowMessage,
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

    if (showMatchGuide) {
        val activeCigar = PairingCatalog.cigarById(selectedCigarId)
        if (activeCigar != null) {
            CigarMatchGuideSheet(
                targetCigar = activeCigar,
                pairedBeverage = null,
                onSelectSubstituteCigar = onSelectCigar,
                onDismiss = { showMatchGuide = false },
            )
        }
    }

    if (showCelebrityWall) {
        CelebrityLoungeGallerySheet(
            onSelectCelebrity = { profile ->
                showCelebrityWall = false
                onExploreCelebrityBrand(profile.favoriteBrand)
            },
            onDismiss = { showCelebrityWall = false },
        )
    }
}

@Composable
private fun LoungeHeroHeader(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp)
            .clip(MaterialTheme.shapes.large),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_lounge_hero),
            contentDescription = stringResource(R.string.cd_lounge_hero),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.25f),
                            Color(0xFF120D0B).copy(alpha = 0.88f),
                        ),
                    )
                ),
        )
        IconButton(
            onClick = onToggleTheme,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .minimumInteractiveComponentSize()
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.45f))
                .testTag("theme_toggle_button"),
        ) {
            Icon(
                imageVector = if (isDarkTheme) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                contentDescription = if (isDarkTheme) "Switch to parchment light mode" else "Switch to dark lounge mode",
                tint = Color(0xFFFFE0B2),
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = Color(0xFFE2A856).copy(alpha = 0.22f),
            ) {
                Text(
                    text = "${PairingCatalog.cigars.size} CIGARS · ${PairingCatalog.beverages.size} BEVERAGES",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFE0B2),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                )
            }
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayMedium,
                color = Color(0xFFF5ECE4),
            )
            Text(
                text = "Spirits, cocktails, wines, beers, zero-proof pours & ratings.",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFD8C9BC),
            )
        }
    }
}
