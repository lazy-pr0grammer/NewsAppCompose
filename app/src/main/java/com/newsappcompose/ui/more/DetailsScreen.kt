package com.newsappcompose.ui.more

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.newsappcompose.ui.more.components.AppTopBar
import com.newsappcompose.ui.more.components.DetailsWebView

@Composable
fun DetailsScreen(state: DetailsState, actions: DetailsActions) {

    var showProgress by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            Column {
                state.requestKey?.let {
                    AppTopBar(it, state.source ?: "", actions.onBackAction)
                }
                if (showProgress) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier.padding(it)) {
            state.requestUrl?.let { url ->
                DetailsWebView(
                    url = url,
                    onStart = {
                        showProgress = true
                    },
                    onFinish = {
                        showProgress = false
                    }
                )
            }
        }
    }
}