package com.newsappcompose.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun MainRoute(
    controller: NavHostController,
    coordinator: MainCoordinator = rememberMainCoordinator(controller = controller)
) {
    val uiState by coordinator.state.collectAsStateWithLifecycle()
    val uiActions = rememberMainActions(coordinator)

    MainScreen(uiState, uiActions)
}

@Composable
fun rememberMainActions(coordinator: MainCoordinator): MainActions {
    return remember(coordinator) {
        MainActions(
            onRefreshAction = {},
            onOpenDetailsAction = coordinator::navigateToWeb
        )
    }
}