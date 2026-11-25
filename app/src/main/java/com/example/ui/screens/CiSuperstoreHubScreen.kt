package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.catalog.CiSuperstoreDirectory
import com.example.model.EmailAlertSubscription
import com.example.ui.components.CiSuperstoreCard
import com.example.ui.components.CiSuperstoreQuickMap
import com.example.ui.components.EventsEmailAlertSignupCard
import com.example.ui.components.openExternalUrl

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CiSuperstoreHubScreen(
    emailSubscription: EmailAlertSubscription,
    onSaveEmailSubscription: (email: String, topics: Set<String>, preferredStoreId: String) -> Unit,
    onUnsubscribeEmail: () -> Unit,
    onShowMessage: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var selectedState by rememberSaveable { mutableStateOf<String?>(null) }
    var selectedStoreId by rememberSaveable { mutableStateOf(CiSuperstoreDirectory.superstores.first().id) }

    val filteredStores = remember(selectedState) {
        if (selectedState == null) CiSuperstoreDirectory.superstores
        else CiSuperstoreDirectory.superstores.filter { it.state == selectedState }
    }

    val activeStore = remember(selectedStoreId, filteredStores) {
        filteredStores.find { it.id == selectedStoreId } ?: filteredStores.first()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🏪 CI Superstores & Links", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.minimumInteractiveComponentSize(),
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.cd_back),
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // 1. Official Website & Online Google Reviews
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("ci_official_links_card"),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f),
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(
                            text = "🌐 Cigars International Online & Reviews",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = "Shop online, browse official retail lounges, or read & write Google reviews for CI online shopping.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )

                        Button(
                            onClick = {
                                openExternalUrl(context, CiSuperstoreDirectory.OFFICIAL_WEBSITE_URL, onShowMessage)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("ci_website_button"),
                        ) {
                            Icon(Icons.Filled.Public, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Visit CigarsInternational.com")
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(Icons.AutoMirrored.Filled.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            OutlinedButton(
                                onClick = {
                                    openExternalUrl(context, CiSuperstoreDirectory.ONLINE_SHOPPING_GOOGLE_REVIEW_URL, onShowMessage)
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(46.dp)
                                    .testTag("ci_online_google_review_button"),
                            ) {
                                Icon(Icons.Filled.RateReview, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("⭐ Review Online", maxLines = 1)
                            }
                            OutlinedButton(
                                onClick = {
                                    openExternalUrl(context, CiSuperstoreDirectory.ONLINE_GOOGLE_CUSTOMER_RATINGS_URL, onShowMessage)
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(46.dp)
                                    .testTag("ci_google_store_ratings_button"),
                            ) {
                                Text("🛍️ Google Ratings", maxLines = 1)
                            }
                        }
                    }
                }
            }

            // 2. Social Media Channels
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("ci_social_links_card"),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(
                            text = "📱 Follow Cigars International",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            CiSuperstoreDirectory.socialChannels.forEach { channel ->
                                OutlinedButton(
                                    onClick = { openExternalUrl(context, channel.url, onShowMessage) },
                                    modifier = Modifier
                                        .height(44.dp)
                                        .testTag("ci_social_${channel.id}"),
                                ) {
                                    Text("${channel.emoji} ${channel.label}")
                                }
                            }
                        }
                    }
                }
            }

            // 3. Interactive Quick Map of all 15 Superstores
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "🗺️ Quick Superstore Map",
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                            Text(
                                text = "All ${CiSuperstoreDirectory.superstores.size} CI Superstores · 21+ to visit",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                        OutlinedButton(
                            onClick = {
                                openExternalUrl(context, CiSuperstoreDirectory.ALL_STORES_GOOGLE_MAPS_URL, onShowMessage)
                            },
                            modifier = Modifier.testTag("ci_all_stores_google_maps_button"),
                        ) {
                            Icon(Icons.Filled.Map, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Google Maps")
                        }
                    }

                    CiSuperstoreQuickMap(
                        stores = filteredStores,
                        selectedStoreId = activeStore.id,
                        onSelectStore = { store -> selectedStoreId = store.id },
                    )

                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        item {
                            FilterChip(
                                selected = selectedState == null,
                                onClick = { selectedState = null },
                                label = { Text("✨ All (${CiSuperstoreDirectory.superstores.size})") },
                            )
                        }
                        items(CiSuperstoreDirectory.availableStates) { st ->
                            val count = remember(st) { CiSuperstoreDirectory.superstores.count { it.state == st } }
                            FilterChip(
                                selected = selectedState == st,
                                onClick = {
                                    selectedState = if (selectedState == st) null else st
                                    val firstInState = CiSuperstoreDirectory.superstores.firstOrNull {
                                        selectedState == null || it.state == selectedState
                                    }
                                    if (firstInState != null) selectedStoreId = firstInState.id
                                },
                                label = { Text("$st ($count)") },
                                modifier = Modifier.testTag("ci_state_filter_$st"),
                            )
                        }
                    }
                }
            }

            // Highlighted Selected Store First
            item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "📍 Selected Pin",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    CiSuperstoreCard(
                        store = activeStore,
                        selected = true,
                        onSelect = {},
                        onOpenUrl = { url -> openExternalUrl(context, url, onShowMessage) },
                    )
                }
            }

            item {
                Text(
                    text = "🏪 All ${if (selectedState != null) "$selectedState " else ""}Locations (${filteredStores.size})",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }

            items(filteredStores.filter { it.id != activeStore.id }, key = { it.id }) { store ->
                CiSuperstoreCard(
                    store = store,
                    selected = store.id == activeStore.id,
                    onSelect = { selectedStoreId = store.id },
                    onOpenUrl = { url -> openExternalUrl(context, url, onShowMessage) },
                )
            }

            // 4. Email Alerts for Events & Specials
            item {
                EventsEmailAlertSignupCard(
                    subscription = emailSubscription,
                    onSaveSubscription = { email, topics, _ ->
                        onSaveEmailSubscription(email, topics, activeStore.id)
                    },
                    onUnsubscribe = onUnsubscribeEmail,
                    onShowMessage = onShowMessage,
                )
            }
        }
    }
}
