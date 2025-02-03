package com.newsappcompose.ui.more

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

class DetailsCoordinator(viewModel: DetailsViewModel) {
    val state = viewModel.state
}

@Composable
fun rememberDetailsCoordinator(viewModel: DetailsViewModel = hiltViewModel()): DetailsCoordinator {
    return remember(viewModel) {
        DetailsCoordinator(viewModel)
    }
}