package com.newsappcompose.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

class MainCoordinator(viewModel: MainViewModel) {
    val state = viewModel.mainState
}

@Composable
fun rememberMainCoordinator(viewModel: MainViewModel = hiltViewModel()): MainCoordinator {
    return remember(viewModel) {
        MainCoordinator(viewModel)
    }
}