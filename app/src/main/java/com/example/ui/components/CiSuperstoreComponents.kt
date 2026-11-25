package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Patterns
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.catalog.CiSuperstoreDirectory
import com.example.model.CiSuperstore
import com.example.model.EmailAlertSubscription
import kotlin.math.hypot

fun openExternalUrl(context: Context, url: String, onError: ((String) -> Unit)? = null) {
    runCatching {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }.onFailure {
        onError?.invoke("Unable to open link on this device")
    }
}

/**
 * Interactive quick USA Superstore Map with tappable glowing pins for all 15 Cigars International
 * Superstores across PA, TX, FL, TN, and KY.
 */
@Composable
fun CiSuperstoreQuickMap(
    stores: List<CiSuperstore>,
    selectedStoreId: String,
    onSelectStore: (CiSuperstore) -> Unit,
    modifier: Modifier = Modifier,
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF192431), Color(0xFF111820)),
                ),
            )
            .border(1.dp, primaryColor.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
            .testTag("ci_superstore_quick_map"),
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(stores) {
                    detectTapGestures { tapOffset ->
                        val nearest = stores.minByOrNull { store ->
                            val px = store.mapX * size.width
                            val py = store.mapY * size.height
                            hypot(tapOffset.x - px, tapOffset.y - py)
                        }
                        if (nearest != null) {
                            val px = nearest.mapX * size.width
                            val py = nearest.mapY * size.height
                            if (hypot(tapOffset.x - px, tapOffset.y - py) <= 68f) {
                                onSelectStore(nearest)
                            }
                        }
                    }
                },
        ) {
            val w = size.width
            val h = size.height

            // Subtle nautical/cartographic grid lines
            for (i in 1..4) {
                drawLine(
                    color = Color.White.copy(alpha = 0.05f),
                    start = Offset(0f, h * i / 5f),
                    end = Offset(w, h * i / 5f),
                    strokeWidth = 1f,
                )
                drawLine(
                    color = Color.White.copy(alpha = 0.05f),
                    start = Offset(w * i / 5f, 0f),
                    end = Offset(w * i / 5f, h),
                    strokeWidth = 1f,
                )
            }

            // Stylized Continental US silhouette so regional store clusters read immediately
            val usContour = Path().apply {
                moveTo(w * 0.08f, h * 0.24f) // WA
                lineTo(w * 0.26f, h * 0.20f) // MT
                lineTo(w * 0.56f, h * 0.20f) // MN/Great Lakes
                lineTo(w * 0.72f, h * 0.26f) // MI/OH
                lineTo(w * 0.86f, h * 0.22f) // NY/PA
                lineTo(w * 0.94f, h * 0.16f) // ME
                lineTo(w * 0.90f, h * 0.34f) // Mid-Atlantic
                lineTo(w * 0.82f, h * 0.56f) // Carolinas/GA
                lineTo(w * 0.83f, h * 0.88f) // FL peninsula
                lineTo(w * 0.74f, h * 0.88f) // FL Gulf
                lineTo(w * 0.68f, h * 0.70f) // Gulf Coast
                lineTo(w * 0.48f, h * 0.73f) // LA
                lineTo(w * 0.36f, h * 0.86f) // South TX
                lineTo(w * 0.22f, h * 0.68f) // West TX / NM
                lineTo(w * 0.09f, h * 0.58f) // CA South
                lineTo(w * 0.06f, h * 0.36f) // CA/OR
                close()
            }
            drawPath(usContour, color = Color(0xFF283747))
            drawPath(usContour, color = Color(0xFF56708A), style = Stroke(width = 2f))

            // Draw connecting hub lines from PA flagship to other CI regions
            val flagship = stores.firstOrNull { it.id == "ci_hamburg_pa" }
            if (flagship != null) {
                val fx = flagship.mapX * w
                val fy = flagship.mapY * h
                stores.filter { it.state != "PA" }.forEach { other ->
                    drawLine(
                        color = primaryColor.copy(alpha = 0.18f),
                        start = Offset(fx, fy),
                        end = Offset(other.mapX * w, other.mapY * h),
                        strokeWidth = 1.5f,
                    )
                }
            }

            // Draw store pins
            stores.forEach { store ->
                val px = store.mapX * w
                val py = store.mapY * h
                val isSelected = store.id == selectedStoreId

                if (isSelected) {
                    drawCircle(
                        color = primaryColor.copy(alpha = 0.34f),
                        radius = 22f,
                        center = Offset(px, py),
                    )
                }
                drawCircle(
                    color = if (isSelected) Color(0xFFFFD166) else if (store.isFlagship) primaryColor else tertiaryColor,
                    radius = if (isSelected) 11f else if (store.isFlagship) 8.5f else 6.5f,
                    center = Offset(px, py),
                )
                drawCircle(
                    color = Color(0xFF140F0C),
                    radius = if (isSelected) 4f else 2.5f,
                    center = Offset(px, py),
                )
            }
        }

        // State quick-legend labels
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.Black.copy(alpha = 0.55f),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(10.dp),
        ) {
            Text(
                text = "🗺️ Tap any pin · ${stores.size} CI Superstores (PA · TX · FL · TN · KY)",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFF5ECE4),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            )
        }
    }
}

@Composable
fun CiSuperstoreCard(
    store: CiSuperstore,
    selected: Boolean,
    onSelect: () -> Unit,
    onOpenUrl: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onSelect)
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                shape = MaterialTheme.shapes.medium,
            )
            .testTag("ci_store_card_${store.id}"),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) {
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
            } else {
                MaterialTheme.colorScheme.surfaceContainer
            },
        ),
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.16f),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Storefront,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(18.dp),
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        Text(
                            text = store.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f, fill = false),
                        )
                        if (store.isFlagship) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = MaterialTheme.colorScheme.primary,
                            ) {
                                Text(
                                    text = "⭐ Flagship",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                )
                            }
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(14.dp),
                        )
                        Text(
                            text = store.address,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }

            Text(
                text = store.highlight,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                OutlinedButton(
                    onClick = { onOpenUrl(CiSuperstoreDirectory.googleMapsDirectionsUrl(store)) },
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .testTag("ci_store_map_${store.id}"),
                ) {
                    Icon(Icons.Filled.Directions, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("🗺️ Map", maxLines = 1)
                }
                Button(
                    onClick = { onOpenUrl(CiSuperstoreDirectory.googleStoreReviewUrl(store)) },
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .testTag("ci_store_review_${store.id}"),
                ) {
                    Icon(Icons.Filled.RateReview, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("⭐ Google Review", maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EventsEmailAlertSignupCard(
    subscription: EmailAlertSubscription,
    onSaveSubscription: (email: String, topics: Set<String>, preferredStoreId: String) -> Unit,
    onUnsubscribe: () -> Unit,
    onShowMessage: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var emailInput by remember(subscription.email) { mutableStateOf(subscription.email) }
    var selectedTopics by remember(subscription.topics) {
        mutableStateOf(
            subscription.topics.ifEmpty {
                setOf(
                    CiSuperstoreDirectory.alertTopics[0],
                    CiSuperstoreDirectory.alertTopics[1],
                )
            }
        )
    }
    var selectedStoreId by remember(subscription.preferredStoreId) {
        mutableStateOf(subscription.preferredStoreId)
    }
    var emailError by remember { mutableStateOf<String?>(null) }
    var isEditing by remember(subscription.isSubscribed) { mutableStateOf(!subscription.isSubscribed) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                shape = MaterialTheme.shapes.medium,
            )
            .testTag("events_email_signup_card"),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.16f),
                ) {
                    Icon(
                        imageVector = Icons.Filled.NotificationsActive,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(20.dp),
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "📬 Events & Specials Alerts",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = "Plug in your email for upcoming lounge events, brand tastings & exclusive specials.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            if (subscription.isSubscribed && !isEditing) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp),
                            )
                            Text(
                                text = "Subscribed: ${subscription.email}",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                        val storeName = CiSuperstoreDirectory.superstores
                            .find { it.id == subscription.preferredStoreId }
                            ?.let { "${it.city}, ${it.state}" }
                            ?: "All Superstores & Online"
                        Text(
                            text = "🏪 Store alerts: $storeName",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        if (subscription.topics.isNotEmpty()) {
                            Text(
                                text = subscription.topics.joinToString(" · "),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    OutlinedButton(
                        onClick = { isEditing = true },
                        modifier = Modifier
                            .weight(1f)
                            .height(46.dp)
                            .testTag("email_edit_prefs_button"),
                    ) {
                        Text("✏️ Update")
                    }
                    TextButton(
                        onClick = {
                            onUnsubscribe()
                            emailInput = ""
                            isEditing = true
                            onShowMessage("Unsubscribed from event & special alerts")
                        },
                        modifier = Modifier
                            .height(46.dp)
                            .testTag("email_unsubscribe_button"),
                    ) {
                        Text("Unsubscribe")
                    }
                }
            } else {
                OutlinedTextField(
                    value = emailInput,
                    onValueChange = {
                        emailInput = it
                        emailError = null
                    },
                    label = { Text("Your Email Address") },
                    placeholder = { Text("you@example.com") },
                    leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) },
                    isError = emailError != null,
                    supportingText = emailError?.let { err -> { Text(err) } },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("events_email_input"),
                )

                Text(
                    text = "🎯 Notify me about:",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    CiSuperstoreDirectory.alertTopics.forEach { topic ->
                        val picked = topic in selectedTopics
                        FilterChip(
                            selected = picked,
                            onClick = {
                                selectedTopics = if (picked) selectedTopics - topic else selectedTopics + topic
                            },
                            label = { Text(topic) },
                        )
                    }
                }

                Button(
                    onClick = {
                        val trimmed = emailInput.trim()
                        if (trimmed.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(trimmed).matches()) {
                            emailError = "Please enter a valid email address."
                        } else {
                            val finalTopics = selectedTopics.ifEmpty { setOf(CiSuperstoreDirectory.alertTopics.first()) }
                            onSaveSubscription(trimmed, finalTopics, selectedStoreId)
                            isEditing = false
                            onShowMessage("📬 Subscribed $trimmed for events & specials!")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("events_email_submit_button"),
                ) {
                    Icon(Icons.Filled.NotificationsActive, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Notify Me of Events & Specials")
                }
            }

            OutlinedButton(
                onClick = {
                    openExternalUrl(context, CiSuperstoreDirectory.OFFICIAL_EMAIL_SIGNUP_URL, onShowMessage)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .testTag("ci_official_email_signup_button"),
            ) {
                Icon(Icons.AutoMirrored.Filled.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("🌐 Also Join CigarsInternational.com Email List")
            }
        }
    }
}
