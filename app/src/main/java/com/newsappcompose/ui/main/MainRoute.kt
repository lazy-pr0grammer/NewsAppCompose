package com.newsappcompose.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MainRoute(coordinator: MainCoordinator = rememberMainCoordinator()) {
    val uiState by coordinator.state.collectAsStateWithLifecycle()
    val uiActions = rememberMainActions(coordinator)

    MainScreen(uiState, uiActions)
}

@Composable
fun rememberMainActions(coordinator: MainCoordinator): MainActions {
    return remember(coordinator) {
        MainActions(
            onRefreshAction = {}
        )
    }
}