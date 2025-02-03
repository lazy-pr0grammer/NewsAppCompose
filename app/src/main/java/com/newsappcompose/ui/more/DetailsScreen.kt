package com.newsappcompose.ui.more

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.newsappcompose.ui.more.components.AppTopBar
import com.newsappcompose.ui.more.components.DetailsWebView

@Composable
fun DetailsScreen(state: DetailsState, actions: DetailsActions) {
    Scaffold(
        topBar = {
            state.requestKey?.let { AppTopBar(it) }
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier.padding(it)) {
            state.requestUrl?.let {
                DetailsWebView(url = it)
            }
        }
    }
}