package com.vesperrun.tipsync.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vesperrun.tipsync.data.GigRepository
import com.vesperrun.tipsync.data.GigStatus
import com.vesperrun.tipsync.ui.components.GigCard

@Composable
fun HomeScreen(
    repository: GigRepository,
    onGigClick: (String) -> Unit,
    onUnpaidClick: () -> Unit,
) {
    val gigs by repository.observeGigs().collectAsState(initial = emptyList())
    val castOwed by repository.observeCastOwedTotal().collectAsState(initial = 0)

    val thisWeek =
        gigs.filter { gig ->
            gig.status == GigStatus.Booked.label ||
                gig.status == GigStatus.ShowDay.label ||
                gig.status == GigStatus.Completed.label
        }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Text("Home", style = MaterialTheme.typography.headlineMedium)
        }
        if (castOwed > 0) {
            item {
                Card(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .clickable(onClick = onUnpaidClick),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                ) {
                    Text(
                        "$$castOwed cast still owed across shows",
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        }
        item {
            Text(
                "This week",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp),
            )
        }
        items(thisWeek, key = { it.id }) { gig ->
            GigCard(gig = gig, onClick = { onGigClick(gig.id) })
        }
        if (thisWeek.isEmpty()) {
            item {
                Text(
                    "No shows on the books yet.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
