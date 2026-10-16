package com.vesperrun.tipsync.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
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
import com.vesperrun.tipsync.data.GigStatus
import com.vesperrun.tipsync.ui.components.CityTag
import com.vesperrun.tipsync.ui.components.SectionCard
import com.vesperrun.tipsync.ui.components.StatusChip
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

    val showIncome = current.guarantee + current.tips + current.otherIncome
    val castOwed = cast.filter { !it.paid }.sumOf { it.amount }
    val postShow =
        current.status == GigStatus.Completed.label ||
            current.status == GigStatus.Paid.label

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CityTag(current.city)
            StatusChip(current.status)
        }
        Text(current.venue, style = MaterialTheme.typography.headlineMedium)
        Text(formatCallTime(current.callTimeIso), color = MaterialTheme.colorScheme.onSurfaceVariant)

        if (postShow) {
            Text(
                "Post-show — log income and mark cast paid",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }

        SectionCard(title = "Booking") {
            DetailRow("Role", current.role)
            DetailRow("Booker", current.booker.ifBlank { "—" })
            DetailRow("Source", current.source.ifBlank { "—" })
            DetailRow("Notes", current.notes.ifBlank { "—" })
        }

        SectionCard(title = "Show income") {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Guarantee")
                Text("$${current.guarantee}")
            }
            FilterChip(
                selected = current.guaranteeReceived,
                onClick = {
                    scope.launch {
                        repository.toggleGuaranteeReceived(current)
                        gig = repository.gigById(gigId)
                    }
                },
                label = { Text(if (current.guaranteeReceived) "Received" else "Not received") },
                modifier = Modifier.defaultMinSize(minHeight = 48.dp),
            )
            DetailRow(
                "Tips",
                "$${current.tips}" +
                    if (current.tipMethods.isNotBlank()) {
                        " · ${current.tipMethods.replace(",", ", ")}"
                    } else {
                        ""
                    },
            )
            DetailRow("Other", "$${current.otherIncome}")
            HorizontalDivider(Modifier.padding(vertical = 8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total", style = MaterialTheme.typography.titleMedium)
                Text("$showIncome", style = MaterialTheme.typography.titleMedium)
            }
        }

        SectionCard(title = "Cast ledger") {
            if (castOwed > 0) {
                Text(
                    "$$castOwed still owed this show",
                    color = MaterialTheme.colorScheme.primary,
                )
            } else if (cast.isNotEmpty()) {
                Text("Cast paid for this show", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
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
}

@Composable
private fun DetailRow(
    label: String,
    value: String,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value)
    }
}

@Composable
private fun CastRow(
    line: CastLineEntity,
    onTogglePaid: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
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
            modifier = Modifier.defaultMinSize(minHeight = 48.dp),
        )
    }
}
