package com.newsappcompose.ui.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.newsappcompose.ui.base.components.SearchBar
import com.newsappcompose.ui.base.components.TopAppBar
import com.newsappcompose.ui.main.MainRoute
import com.newsappcompose.ui.theme.NewsAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NewsAppComposeTheme {
                var isSearchActive by rememberSaveable { mutableStateOf(false) }
                val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

                Scaffold(
                    topBar = {
                        Column {
                            TopAppBar(
                                scrollBehavior = scrollBehavior,
                                onBackAction = {
                                    isSearchActive = false
                                }
                            )

                            SearchBar(
                                onQueryChange = {},
                                onActiveChanged = {},
                                isSearchActive = isSearchActive,
                            )
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        MainRoute()
                    }
                }
            }
        }
    }
}