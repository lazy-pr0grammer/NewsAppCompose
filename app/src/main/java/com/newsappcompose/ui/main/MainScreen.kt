package com.newsappcompose.ui.main


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.newsappcompose.ui.main.components.NewsContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(state: MainState, actions: MainActions) {

    val newsArticles = state.newsPagingData?.collectAsLazyPagingItems()

    PullToRefreshBox(
        isRefreshing = newsArticles?.loadState?.refresh is LoadState.Loading,
        onRefresh = {
            newsArticles?.refresh()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                newsArticles?.itemCount?.let {
                    items(it) { count ->
                        newsArticles[count]?.let { data ->
                            NewsContent(data)
                        }

                        if (count > -1) {
                            Spacer(Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}