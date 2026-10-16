package com.vesperrun.tipsync.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vesperrun.tipsync.data.GigRepository
import com.vesperrun.tipsync.ui.components.SectionCard
@Composable
fun UnpaidScreen(
    repository: GigRepository,
    onGigClick: (String) -> Unit,
) {
    val guarantees by repository.observeGuaranteesUnreceived().collectAsState(initial = emptyList())
    val castOwed by repository.observeCastLinesOwed().collectAsState(initial = emptyList())
    val gigs by repository.observeGigs().collectAsState(initial = emptyList())
    val gigMap = remember(gigs) { gigs.associateBy { it.id } }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Text("Unpaid", style = MaterialTheme.typography.headlineMedium)
            Text(
                "Guarantees not received and cast still owed — local ledger only.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
            )
        }
        if (guarantees.isNotEmpty()) {
            item {
                Text("Guarantees", style = MaterialTheme.typography.titleMedium)
            }
            items(guarantees, key = { it.id }) { gig ->
                SectionCard(title = gig.venue) {
                    Text("${gig.city} · $${gig.guarantee} not marked received")
                    Text(
                        "Open gig",
                        color = MaterialTheme.colorScheme.primary,
                        modifier =
                            Modifier
                                .padding(top = 8.dp)
                                .clickable { onGigClick(gig.id) },
                    )
                }
            }
        }
        if (castOwed.isNotEmpty()) {
            item {
                Text("Cast owed", style = MaterialTheme.typography.titleMedium)
            }
            items(castOwed, key = { it.id }) { line ->
                val gig = gigMap[line.gigId]
                SectionCard(title = gig?.venue ?: "Show") {
                    Text("${line.name} · ${line.role} · $${line.amount}")
                    Text(
                        "Open gig",
                        color = MaterialTheme.colorScheme.primary,
                        modifier =
                            Modifier
                                .padding(top = 8.dp)
                                .clickable { onGigClick(line.gigId) },
                    )
                }
            }
        }
        if (guarantees.isEmpty() && castOwed.isEmpty()) {
            item {
                Text("All caught up.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
