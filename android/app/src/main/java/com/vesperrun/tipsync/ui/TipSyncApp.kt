package com.vesperrun.tipsync.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vesperrun.tipsync.data.GigRepository
import com.vesperrun.tipsync.ui.screens.GigDetailScreen
import com.vesperrun.tipsync.ui.screens.GigListScreen
import com.vesperrun.tipsync.ui.screens.HomeScreen
import com.vesperrun.tipsync.ui.screens.ProfileScreen
import com.vesperrun.tipsync.ui.screens.UnpaidScreen

private object Routes {
    const val Unpaid = "unpaid"
    fun gig(id: String) = "gig/$id"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipSyncApp(repository: GigRepository) {
    val navController = rememberNavController()
    val backStack by navController.currentBackStackEntryAsState()
    val route = backStack?.destination?.route

    LaunchedEffect(Unit) {
        repository.ensureSeeded()
    }

    val openGig: (String) -> Unit = { id -> navController.navigate(Routes.gig(id)) }

    val showBottomBar =
        route != Routes.Unpaid && route?.startsWith("gig/") != true

    val topTitle =
        when {
            route == Routes.Unpaid -> "Unpaid"
            route?.startsWith("gig/") == true -> "Gig"
            else -> null
        }

    Scaffold(
        topBar = {
            if (topTitle != null) {
                TopAppBar(
                    title = { Text(topTitle) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                TipSyncBottomBar(navController)
            }
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = TopRoute.Home.route,
            modifier = Modifier.padding(padding),
        ) {
            composable(TopRoute.Home.route) {
                HomeScreen(
                    repository = repository,
                    onGigClick = openGig,
                    onUnpaidClick = { navController.navigate(Routes.Unpaid) },
                )
            }
            composable(TopRoute.Inbox.route) {
                GigListScreen(
                    repository = repository,
                    title = "Inbox",
                    gigsFlow = { repository.observeInbox() },
                    onGigClick = openGig,
                    footerHint = "Open a gig to review. Promote to Booked ships in Phase 1.",
                )
            }
            composable(TopRoute.Gigs.route) {
                GigListScreen(
                    repository = repository,
                    title = "Gigs",
                    gigsFlow = { repository.observeGigs() },
                    onGigClick = openGig,
                )
            }
            composable(TopRoute.Profile.route) {
                ProfileScreen()
            }
            composable(Routes.Unpaid) {
                UnpaidScreen(repository = repository, onGigClick = openGig)
            }
            composable("gig/{id}") { entry ->
                val id = entry.arguments?.getString("id") ?: return@composable
                GigDetailScreen(gigId = id, repository = repository)
            }
        }
    }
}
