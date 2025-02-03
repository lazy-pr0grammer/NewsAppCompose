package com.newsappcompose.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.newsappcompose.ui.route.Details

class MainCoordinator(viewModel: MainViewModel, private val controller: NavHostController) {
    val state = viewModel.mainState

    fun navigateToWeb(url: String, key: String, source: String) = controller.navigate(Details(url, key, source))
}

@Composable
fun rememberMainCoordinator(
    viewModel: MainViewModel = hiltViewModel(),
    controller: NavHostController
): MainCoordinator {
    return remember(viewModel, controller) {
        MainCoordinator(viewModel, controller)
    }
}