package com.newsappcompose.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.newsappcompose.ui.main.MainRoute
import com.newsappcompose.ui.more.DetailsRoute
import kotlinx.serialization.Serializable

@Composable
fun RootNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Home
    ) {
        composable<Home> {
            MainRoute(navHostController)
        }

        composable<Details> {
            DetailsRoute()
        }
    }
}


@Serializable
object Home

@Serializable
data class Details(val url: String?, val key: String?, val source: String?)