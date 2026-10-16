package com.vesperrun.tipsync.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vesperrun.tipsync.data.GigRepository
import com.vesperrun.tipsync.ui.components.GigCard

@Composable
fun GigListScreen(
    repository: GigRepository,
    title: String,
    gigsFlow: () -> kotlinx.coroutines.flow.Flow<List<com.vesperrun.tipsync.data.GigEntity>>,
    onGigClick: (String) -> Unit,
    footerHint: String? = null,
) {
    val gigs by gigsFlow().collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Text(title, style = MaterialTheme.typography.headlineMedium)
        }
        items(gigs, key = { it.id }) { gig ->
            GigCard(gig = gig, onClick = { onGigClick(gig.id) })
        }
        if (gigs.isEmpty()) {
            item {
                Text("Nothing here yet.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        if (footerHint != null) {
            item {
                Text(
                    footerHint,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 16.dp),
                )
            }
        }
    }
}
