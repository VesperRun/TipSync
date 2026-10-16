package com.vesperrun.tipsync.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vesperrun.tipsync.data.CastLineEntity
import com.vesperrun.tipsync.data.GigEntity
import com.vesperrun.tipsync.data.GigRepository
import com.vesperrun.tipsync.ui.formatCallTime
import kotlinx.coroutines.launch

@Composable
fun GigDetailScreen(
    gigId: String,
    repository: GigRepository,
) {
    var gig by remember { mutableStateOf<GigEntity?>(null) }
    val cast by repository.observeCast(gigId).collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    LaunchedEffect(gigId) {
        gig = repository.gigById(gigId)
    }

    val current = gig
    if (current == null) {
        Text("Loading…", modifier = Modifier.padding(16.dp))
        return
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(current.venue, style = MaterialTheme.typography.headlineMedium)
        Text(
            "${current.city} · ${current.status}",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(formatCallTime(current.callTimeIso))
        Text("Role: ${current.role}")

        Text("Income", style = MaterialTheme.typography.titleMedium)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Guarantee $${current.guarantee}")
            FilterChip(
                selected = current.guaranteeReceived,
                onClick = {
                    scope.launch {
                        repository.toggleGuaranteeReceived(current)
                        gig = repository.gigById(gigId)
                    }
                },
                label = { Text(if (current.guaranteeReceived) "Received" else "Not received") },
            )
        }
        Text("Tips $${current.tips}")
        if (current.tipMethods.isNotBlank()) {
            Text("Methods: ${current.tipMethods.replace(",", ", ")}")
        }

        Text("Cast ledger", style = MaterialTheme.typography.titleMedium)
        cast.forEach { line ->
            CastRow(line) {
                scope.launch { repository.toggleCastPaid(line) }
            }
        }
        if (cast.isEmpty()) {
            Text("No cast lines yet.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun CastRow(
    line: CastLineEntity,
    onTogglePaid: () -> Unit,
) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(line.name, style = MaterialTheme.typography.bodyLarge)
            Text(
                "${line.role} · $${line.amount}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        FilterChip(
            selected = line.paid,
            onClick = onTogglePaid,
            label = { Text(if (line.paid) "Paid" else "Owed") },
        )
    }
}
