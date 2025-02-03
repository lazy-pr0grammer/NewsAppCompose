package com.newsappcompose.ui.more

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DetailsRoute(coordinator: DetailsCoordinator = rememberDetailsCoordinator()) {
    val uiState by coordinator.state.collectAsStateWithLifecycle()
    val uiActions = rememberDetailsActions(coordinator)

    DetailsScreen(uiState, uiActions)
}

@Composable
fun rememberDetailsActions(coordinator: DetailsCoordinator): DetailsActions {
    return remember(coordinator) {
        DetailsActions(
            onBackAction = {}
        )
    }
}