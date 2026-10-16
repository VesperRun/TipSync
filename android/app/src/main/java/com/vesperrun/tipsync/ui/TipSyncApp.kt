package com.vesperrun.tipsync.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vesperrun.tipsync.data.GigRepository
import com.vesperrun.tipsync.ui.screens.GigDetailScreen
import com.vesperrun.tipsync.ui.screens.GigListScreen
import com.vesperrun.tipsync.ui.screens.HomeScreen
import com.vesperrun.tipsync.ui.screens.ProfileScreen

@Composable
fun TipSyncApp(repository: GigRepository) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        repository.ensureSeeded()
    }

    val openGig: (String) -> Unit = { id ->
        navController.navigate("gig/$id")
    }

    Scaffold(
        bottomBar = {
            val route = navController.currentBackStackEntry?.destination?.route
            if (route?.startsWith("gig/") != true) {
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
                HomeScreen(repository, onGigClick = openGig)
            }
            composable(TopRoute.Inbox.route) {
                GigListScreen(
                    repository = repository,
                    title = "Inbox",
                    gigsFlow = { repository.observeInbox() },
                    onGigClick = openGig,
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
            composable("gig/{id}") { entry ->
                val id = entry.arguments?.getString("id") ?: return@composable
                GigDetailScreen(gigId = id, repository = repository)
            }
        }
    }
}
