package com.example.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.pairing.PairingQuizAnswers
import com.example.data.pairing.PairingQuizEngine
import com.example.data.pairing.QuizDrinkPreference
import com.example.data.pairing.QuizFlavorMood
import com.example.data.pairing.QuizSessionLength
import com.example.data.pairing.SmokeTimeAdvisor
import com.example.model.Beverage
import com.example.model.Cigar
import com.example.model.PairingRatingSummary
import com.example.model.QuizMatchPick
import com.example.model.SmokeTimeOfDay
import com.example.model.StrengthLevel
import com.example.ui.components.BeverageProofBadge
import com.example.ui.components.CigarMatchGuideSheet
import com.example.ui.components.MatchScorePill
import com.example.ui.components.SmokeTimePill
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PairingQuizScreen(
    ratingsByPairingId: Map<String, PairingRatingSummary>,
    savedPairingIds: Set<String>,
    onBack: () -> Unit,
    onOpenPairing: (cigarId: String, beverageId: String) -> Unit,
    onToggleSavePairing: (cigarId: String, beverageId: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val detectedNowTime = remember { SmokeTimeAdvisor.currentTimeOfDay() }
    var stepIndex by rememberSaveable { mutableIntStateOf(0) } // 0..4 questions, 5 = results
    var selectedTimeOrdinal by rememberSaveable { mutableIntStateOf(detectedNowTime.ordinal) }
    var selectedStrengthOrdinal by rememberSaveable { mutableIntStateOf(StrengthLevel.MEDIUM_FULL.ordinal) }
    var selectedMoodOrdinal by rememberSaveable { mutableIntStateOf(QuizFlavorMood.COCOA_ESPRESSO.ordinal) }
    var selectedDrinkOrdinal by rememberSaveable { mutableIntStateOf(QuizDrinkPreference.WHISKEY.ordinal) }
    var selectedLengthOrdinal by rememberSaveable { mutableIntStateOf(QuizSessionLength.CLASSIC.ordinal) }

    var substituteTarget by remember { mutableStateOf<Pair<Cigar, Beverage>?>(null) }

    val answers = remember(
        selectedTimeOrdinal,
        selectedStrengthOrdinal,
        selectedMoodOrdinal,
        selectedDrinkOrdinal,
        selectedLengthOrdinal,
    ) {
        PairingQuizAnswers(
            timeOfDay = SmokeTimeOfDay.entries[selectedTimeOrdinal],
            preferredStrength = StrengthLevel.entries[selectedStrengthOrdinal],
            flavorMood = QuizFlavorMood.entries[selectedMoodOrdinal],
            drinkPreference = QuizDrinkPreference.entries[selectedDrinkOrdinal],
            sessionLength = QuizSessionLength.entries[selectedLengthOrdinal],
        )
    }

    val quizResult = remember(answers, ratingsByPairingId, stepIndex) {
        if (stepIndex >= 5) PairingQuizEngine.evaluateQuiz(answers, ratingsByPairingId) else null
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🎯 Sommelier Pairing Quiz", style = MaterialTheme.typography.titleLarge) },
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
                actions = {
                    TextButton(
                        onClick = {
                            selectedTimeOrdinal = Random.nextInt(SmokeTimeOfDay.entries.size)
                            selectedStrengthOrdinal = Random.nextInt(StrengthLevel.entries.size)
                            selectedMoodOrdinal = Random.nextInt(QuizFlavorMood.entries.size)
                            selectedDrinkOrdinal = Random.nextInt(QuizDrinkPreference.entries.size)
                            selectedLengthOrdinal = Random.nextInt(QuizSessionLength.entries.size)
                            stepIndex = 5
                        },
                        modifier = Modifier.testTag("quiz_wildcard_button"),
                    ) {
                        Icon(Icons.Filled.Shuffle, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Wildcard")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        if (stepIndex < 5) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Question ${stepIndex + 1} of 5",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary,
                            )
                            Text(
                                text = "${((stepIndex + 1) * 20)}%",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                        LinearProgressIndicator(
                            progress = { (stepIndex + 1) / 5f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                        )
                    }
                }

                when (stepIndex) {
                    0 -> {
                        item {
                            QuizStepHeader(
                                emoji = "🕐",
                                title = "When are you lighting up?",
                                subtitle = "We match wrapper body and pacing to your ideal time of day.",
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f),
                            ) {
                                Text(
                                    text = "📍 Detected right now: ${detectedNowTime.emoji} ${detectedNowTime.label} (${detectedNowTime.windowRange})",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                )
                            }
                        }
                        itemsIndexed(SmokeTimeOfDay.entries) { idx, time ->
                            QuizOptionCard(
                                emoji = time.emoji,
                                title = time.label,
                                subtitle = "Ideal window: ${time.windowRange}",
                                selected = selectedTimeOrdinal == idx,
                                onClick = { selectedTimeOrdinal = idx },
                                testTag = "quiz_time_${time.name}",
                            )
                        }
                    }

                    1 -> {
                        item {
                            QuizStepHeader(
                                emoji = "🔥",
                                title = "What strength feels right today?",
                                subtitle = "Pick the body intensity you want across all three thirds.",
                            )
                        }
                        itemsIndexed(StrengthLevel.entries) { idx, strength ->
                            val desc = when (strength) {
                                StrengthLevel.MILD -> "Silky, creamy, gentle draw with toasted cedar & almond."
                                StrengthLevel.MEDIUM -> "Balanced daily classic with caramel, nut & mild spice."
                                StrengthLevel.MEDIUM_FULL -> "Rich complexity, warm pepper, dark roast & deep leather."
                                StrengthLevel.FULL -> "Bold ligero powerhouse packed with espresso, cocoa & earth."
                            }
                            QuizOptionCard(
                                emoji = strength.emoji,
                                title = strength.label,
                                subtitle = desc,
                                selected = selectedStrengthOrdinal == idx,
                                onClick = { selectedStrengthOrdinal = idx },
                                testTag = "quiz_strength_${strength.name}",
                            )
                        }
                    }

                    2 -> {
                        item {
                            QuizStepHeader(
                                emoji = "👅",
                                title = "Pick your dream flavor mood",
                                subtitle = "We align both cigar leaf and pour along your favorite tasting notes.",
                            )
                        }
                        itemsIndexed(QuizFlavorMood.entries) { idx, mood ->
                            QuizOptionCard(
                                emoji = mood.emoji,
                                title = mood.label,
                                subtitle = mood.subtitle,
                                selected = selectedMoodOrdinal == idx,
                                onClick = { selectedMoodOrdinal = idx },
                                testTag = "quiz_mood_${mood.name}",
                            )
                        }
                    }

                    3 -> {
                        item {
                            QuizStepHeader(
                                emoji = "🥃",
                                title = "What's in your glass?",
                                subtitle = "Choose alcoholic spirits, cellar pours, or zero-proof pairings.",
                            )
                        }
                        itemsIndexed(QuizDrinkPreference.entries) { idx, pref ->
                            QuizOptionCard(
                                emoji = pref.emoji,
                                title = pref.label,
                                subtitle = pref.subtitle,
                                selected = selectedDrinkOrdinal == idx,
                                onClick = { selectedDrinkOrdinal = idx },
                                testTag = "quiz_drink_${pref.name}",
                            )
                        }
                    }

                    4 -> {
                        item {
                            QuizStepHeader(
                                emoji = "⏱️",
                                title = "How much time do you have?",
                                subtitle = "We calibrate vitola length so you can enjoy every third unhurried.",
                            )
                        }
                        itemsIndexed(QuizSessionLength.entries) { idx, len ->
                            QuizOptionCard(
                                emoji = len.emoji,
                                title = len.label,
                                subtitle = len.subtitle,
                                selected = selectedLengthOrdinal == idx,
                                onClick = { selectedLengthOrdinal = idx },
                                testTag = "quiz_length_${len.name}",
                            )
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        if (stepIndex > 0) {
                            OutlinedButton(
                                onClick = { stepIndex -= 1 },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp)
                                    .testTag("quiz_prev_button"),
                            ) {
                                Text("Back")
                            }
                        }
                        Button(
                            onClick = { stepIndex += 1 },
                            modifier = Modifier
                                .weight(1.4f)
                                .height(50.dp)
                                .testTag("quiz_next_button"),
                        ) {
                            Text(if (stepIndex == 4) "✨ Reveal My Pairings" else "Next Step")
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                            )
                        }
                    }
                }
            }
        } else if (quizResult != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("quiz_result_archetype_card"),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f)),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.45f), MaterialTheme.shapes.medium)
                                .padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text(
                                text = "${quizResult.archetypeEmoji} YOUR SOMMELIER PROFILE",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                            )
                            Text(
                                text = quizResult.archetypeTitle,
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                            Text(
                                text = quizResult.archetypeSummary,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp),
                            ) {
                                QuizSummaryChip("${answers.timeOfDay.emoji} ${answers.timeOfDay.shortLabel}")
                                QuizSummaryChip("${answers.preferredStrength.emoji} ${answers.preferredStrength.label}")
                                QuizSummaryChip("${answers.flavorMood.emoji} ${answers.flavorMood.label}")
                                QuizSummaryChip("${answers.sessionLength.emoji} ${answers.sessionLength.label}")
                            }
                        }
                    }
                }

                item {
                    Text(
                        text = "🏆 Top ${quizResult.topPicks.size} Pairings Built for You",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }

                itemsIndexed(quizResult.topPicks, key = { _, pick -> pick.suggestion.pairingId }) { index, pick ->
                    QuizResultPickCard(
                        rank = index + 1,
                        pick = pick,
                        isSavedInDb = pick.suggestion.pairingId in savedPairingIds,
                        onOpenPairing = { onOpenPairing(pick.suggestion.cigar.id, pick.suggestion.beverage.id) },
                        onToggleSave = { onToggleSavePairing(pick.suggestion.cigar.id, pick.suggestion.beverage.id) },
                        onOpenMatchGuide = { substituteTarget = pick.suggestion.cigar to pick.suggestion.beverage },
                    )
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        OutlinedButton(
                            onClick = { stepIndex = 0 },
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .testTag("quiz_retake_button"),
                        ) {
                            Icon(Icons.Filled.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Retake Quiz")
                        }
                        Button(
                            onClick = onBack,
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp),
                        ) {
                            Text("Done")
                        }
                    }
                }
            }
        }
    }

    substituteTarget?.let { (cigar, bev) ->
        CigarMatchGuideSheet(
            targetCigar = cigar,
            pairedBeverage = bev,
            onSelectSubstituteCigar = { subCigarId ->
                substituteTarget = null
                onOpenPairing(subCigarId, bev.id)
            },
            onDismiss = { substituteTarget = null },
        )
    }
}

@Composable
private fun QuizStepHeader(
    emoji: String,
    title: String,
    subtitle: String,
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = emoji, fontSize = 36.sp)
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun QuizOptionCard(
    emoji: String,
    title: String,
    subtitle: String,
    selected: Boolean,
    onClick: () -> Unit,
    testTag: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onClick)
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                shape = MaterialTheme.shapes.medium,
            )
            .testTag(testTag),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) {
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
            } else {
                MaterialTheme.colorScheme.surfaceContainer
            },
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(text = emoji, fontSize = 26.sp)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            if (selected) {
                Text(
                    text = "✅",
                    fontSize = 18.sp,
                )
            }
        }
    }
}

@Composable
private fun QuizSummaryChip(text: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun QuizResultPickCard(
    rank: Int,
    pick: QuizMatchPick,
    isSavedInDb: Boolean,
    onOpenPairing: () -> Unit,
    onToggleSave: () -> Unit,
    onOpenMatchGuide: () -> Unit,
) {
    val s = pick.suggestion
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onOpenPairing)
            .testTag("quiz_result_card_${s.pairingId}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primary,
                ) {
                    Text(
                        text = "#$rank · 🎯 ${pick.quizFitScore}% FIT",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    )
                }
                MatchScorePill(score = s.matchScore)
                SmokeTimePill(cigar = s.cigar)
                BeverageProofBadge(beverage = s.beverage)
            }

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = "${s.cigar.brand} ${s.cigar.name}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = "paired with ${s.beverage.category.emoji} ${s.beverage.name}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
            }

            Text(
                text = pick.fitReason,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TextButton(
                    onClick = onToggleSave,
                    modifier = Modifier.testTag("quiz_save_db_${s.pairingId}"),
                ) {
                    Icon(
                        imageVector = if (isSavedInDb) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(if (isSavedInDb) "Saved 💾" else "Save DB")
                }
                TextButton(
                    onClick = onOpenMatchGuide,
                    modifier = Modifier.testTag("quiz_match_guide_${s.pairingId}"),
                ) {
                    Icon(Icons.Filled.SwapHoriz, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Sold Out?")
                }
                TextButton(onClick = onOpenPairing) {
                    Text("Explore →")
                }
            }
        }
    }
}
