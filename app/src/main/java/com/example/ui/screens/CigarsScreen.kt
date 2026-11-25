package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.catalog.CigarAccoladesCatalog
import com.example.data.catalog.CigarCelebrityDirectory
import com.example.data.catalog.PairingCatalog
import com.example.data.history.CigarHistoryCatalog
import com.example.data.pairing.SmokeTimeAdvisor
import com.example.model.Cigar
import com.example.model.SmokeTimeOfDay
import com.example.model.StrengthLevel
import com.example.model.WrapperShade
import com.example.ui.components.BrandHeritageBannerCard
import com.example.ui.components.CelebrityCartoonAvatar
import com.example.ui.components.CelebrityEndorsementPill
import com.example.ui.components.CelebrityLoungeGallerySheet
import com.example.ui.components.CigarAficionadoScorePill
import com.example.ui.components.CigarMiniStripPreview
import com.example.ui.components.CigarOfTheYearPill
import com.example.ui.components.FlavorNoteTag
import com.example.ui.components.SmokeTimePill
import com.example.ui.components.StrengthDotsMeter
import com.example.ui.components.WrapperSwatchPill

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CigarsScreen(
    searchQuery: String,
    strengthFilter: StrengthLevel?,
    onSearchQueryChange: (String) -> Unit,
    onStrengthFilterChange: (StrengthLevel?) -> Unit,
    onOpenCigar: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var wrapperFilter by remember { mutableStateOf<WrapperShade?>(null) }
    var brandFilter by remember { mutableStateOf<String?>(null) }
    var timeOfDayFilter by remember { mutableStateOf<SmokeTimeOfDay?>(null) }
    var onlyCoty by remember { mutableStateOf(false) }
    var onlyCelebrity by remember { mutableStateOf(false) }
    var onlyInternational by remember { mutableStateOf(false) }
    var showCelebrityWall by remember { mutableStateOf(false) }
    val allBrands = remember {
        PairingCatalog.cigars.map { it.brand }.distinct().sorted()
    }

    val filteredCigars = remember(
        searchQuery,
        strengthFilter,
        wrapperFilter,
        brandFilter,
        timeOfDayFilter,
        onlyCoty,
        onlyCelebrity,
        onlyInternational,
    ) {
        PairingCatalog.cigars.filter { cigar ->
            val accolade = CigarAccoladesCatalog.forCigar(cigar)
            val matchesStrength = strengthFilter == null || cigar.strength == strengthFilter
            val matchesWrapper = wrapperFilter == null || cigar.wrapperShade == wrapperFilter
            val matchesBrand = brandFilter == null || cigar.brand == brandFilter
            val matchesTime = SmokeTimeAdvisor.matchesTimeFilter(cigar, timeOfDayFilter)
            val matchesCoty = !onlyCoty || accolade.isCigarOfTheYear
            val matchesCelebrity = !onlyCelebrity || accolade.hasCelebrityNotes
            val matchesInternational = !onlyInternational || accolade.hasInternationalCelebrity
            val q = searchQuery.trim().lowercase()
            val matchesQuery = q.isEmpty() ||
                cigar.name.lowercase().contains(q) ||
                cigar.brand.lowercase().contains(q) ||
                cigar.wrapper.lowercase().contains(q) ||
                cigar.origin.lowercase().contains(q) ||
                cigar.tastingNotes.any { it.lowercase().contains(q) } ||
                (accolade.isCigarOfTheYear && (q.contains("cigar of the year") || q.contains("coty"))) ||
                (accolade.hasInternationalCelebrity && q.contains("international")) ||
                accolade.celebrityNotes.any { it.lowercase().contains(q) }
            matchesStrength && matchesWrapper && matchesBrand && matchesTime && matchesCoty && matchesCelebrity && matchesInternational && matchesQuery
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(158.dp)
                    .clip(MaterialTheme.shapes.large),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_cigar_band_studio),
                    contentDescription = stringResource(R.string.cd_cigar_band_studio),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, Color(0xFF120D0B).copy(alpha = 0.88f))
                            )
                        ),
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = "The Humidor (${PairingCatalog.cigars.size} Cigars)",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color(0xFFF5ECE4),
                    )
                    Text(
                        text = "Cigar Aficionado ratings, #1 Cigar of the Year winners, celebrity picks & band labels across ${allBrands.size} brands.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFD8C9BC),
                    )
                }
            }
        }

        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = { Text("Search cigar, brand, origin, celebrity, or COTY…") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(Icons.Filled.Clear, contentDescription = "Clear search")
                        }
                    }
                },
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("cigar_search_input"),
            )
        }

        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item {
                    FilterChip(
                        selected = false,
                        onClick = { showCelebrityWall = true },
                        label = { Text("🎭 Celebrity Wall (${CigarCelebrityDirectory.allCelebrityProfiles.size})") },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.65f),
                        ),
                        modifier = Modifier.testTag("open_celebrity_wall_button"),
                    )
                }
                item {
                    FilterChip(
                        selected = onlyCoty,
                        onClick = { onlyCoty = !onlyCoty },
                        label = { Text("🏆 Cigar of the Year") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        ),
                        modifier = Modifier.testTag("filter_cigar_of_the_year"),
                    )
                }
                item {
                    FilterChip(
                        selected = onlyCelebrity,
                        onClick = { onlyCelebrity = !onlyCelebrity },
                        label = { Text("🌟 Celebrity Picks") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        ),
                        modifier = Modifier.testTag("filter_celebrity_picks"),
                    )
                }
                item {
                    FilterChip(
                        selected = onlyInternational,
                        onClick = { onlyInternational = !onlyInternational },
                        label = { Text("🌍 International Icons") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        modifier = Modifier.testTag("filter_international_celebrities"),
                    )
                }
            }
        }

        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item {
                    FilterChip(
                        selected = timeOfDayFilter == null,
                        onClick = { timeOfDayFilter = null },
                        label = { Text("🕐 All Times") },
                    )
                }
                items(SmokeTimeOfDay.entries) { time ->
                    FilterChip(
                        selected = timeOfDayFilter == time,
                        onClick = {
                            timeOfDayFilter = if (timeOfDayFilter == time) null else time
                        },
                        label = { Text("${time.emoji} ${time.shortLabel}") },
                        modifier = Modifier.testTag("filter_time_${time.name}"),
                    )
                }
            }
        }

        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item {
                    FilterChip(
                        selected = brandFilter == null,
                        onClick = { brandFilter = null },
                        label = { Text("All Brands") },
                    )
                }
                items(allBrands) { brand ->
                    FilterChip(
                        selected = brandFilter == brand,
                        onClick = {
                            brandFilter = if (brandFilter == brand) null else brand
                        },
                        label = { Text(brand) },
                    )
                }
            }
        }

        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item {
                    FilterChip(
                        selected = strengthFilter == null,
                        onClick = { onStrengthFilterChange(null) },
                        label = { Text("All Strengths") },
                    )
                }
                items(StrengthLevel.entries) { strength ->
                    FilterChip(
                        selected = strengthFilter == strength,
                        onClick = {
                            onStrengthFilterChange(if (strengthFilter == strength) null else strength)
                        },
                        label = { Text("${strength.emoji} ${strength.label}") },
                    )
                }
            }
        }

        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item {
                    FilterChip(
                        selected = wrapperFilter == null,
                        onClick = { wrapperFilter = null },
                        label = { Text("All Wrappers") },
                    )
                }
                items(WrapperShade.entries) { shade ->
                    FilterChip(
                        selected = wrapperFilter == shade,
                        onClick = {
                            wrapperFilter = if (wrapperFilter == shade) null else shade
                        },
                        label = { Text(shade.label) },
                    )
                }
            }
        }

        val activeBrandHistory = brandFilter?.let { CigarHistoryCatalog.companyForBrand(it) }
        if (activeBrandHistory != null) {
            item(key = "brand_heritage_${activeBrandHistory.brand}") {
                BrandHeritageBannerCard(companyHistory = activeBrandHistory)
            }
        }

        item {
            Text(
                text = "Showing ${filteredCigars.size} of ${PairingCatalog.cigars.size} cigars",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        if (filteredCigars.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        Text(
                            text = "No matching cigars",
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            text = "Try clearing the search query or disabling a filter above.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        } else {
            items(filteredCigars, key = { it.id }) { cigar ->
                CigarListCard(
                    cigar = cigar,
                    onClick = { onOpenCigar(cigar.id) },
                )
            }
        }
    }

    if (showCelebrityWall) {
        CelebrityLoungeGallerySheet(
            onSelectCelebrity = { profile ->
                brandFilter = null
                onlyCelebrity = false
                onlyInternational = false
                onSearchQueryChange(profile.name.replace("Sir ", ""))
            },
            onDismiss = { showCelebrityWall = false },
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CigarListCard(
    cigar: Cigar,
    onClick: () -> Unit,
) {
    val releaseHistory = remember(cigar.id) {
        CigarHistoryCatalog.releaseForCigar(cigar.id)
    }
    val accolade = remember(cigar.id) {
        CigarAccoladesCatalog.forCigar(cigar)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onClick)
            .testTag("cigar_card_${cigar.id}"),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "${cigar.brand.uppercase()} · ${cigar.origin.uppercase()}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
                StrengthDotsMeter(
                    filledDots = cigar.strength.dots,
                    label = cigar.strength.label,
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(
                    text = cigar.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = "${cigar.vitola} (${cigar.dimensions}) · ~${cigar.smokeTimeMinutes} min",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            CigarMiniStripPreview(cigar = cigar)

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                CigarAficionadoScorePill(accolade = accolade)
                SmokeTimePill(cigar = cigar)
                if (accolade.isCigarOfTheYear) {
                    CigarOfTheYearPill(year = accolade.cigarOfTheYearYear.orEmpty())
                }
                if (accolade.hasCelebrityNotes) {
                    CelebrityEndorsementPill(accolade = accolade)
                }
            }

            val topCelebProfiles = remember(cigar.id, accolade.celebrityNotes) {
                CigarCelebrityDirectory.profilesForNotes(accolade.celebrityNotes, cigar.brand).take(3)
            }
            if (topCelebProfiles.isNotEmpty()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    topCelebProfiles.forEach { profile ->
                        CelebrityCartoonAvatar(profile = profile, size = 36.dp)
                    }
                    Text(
                        text = topCelebProfiles.joinToString(" · ") { "${it.flagEmoji} ${it.name}" },
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f),
                    )
                }
            }

            WrapperSwatchPill(
                wrapperShade = cigar.wrapperShade,
                wrapperName = cigar.wrapper,
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                cigar.tastingNotes.take(3).forEach { note ->
                    FlavorNoteTag(text = note)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val launchNote = releaseHistory?.let { "Launched ${it.launchYear} · " } ?: ""
                Text(
                    text = "${launchNote}Band, Ratings & 10 Pairings",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.tertiary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "View cigar profile, ratings, history, and pairings",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}
