package com.newsappcompose.ui.main


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.newsappcompose.ui.base.components.SearchBar
import com.newsappcompose.ui.base.components.TopAppBar
import com.newsappcompose.ui.main.components.NewsContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(state: MainState, actions: MainActions) {

    var query by remember {
        mutableStateOf("")
    }

    var isSearchActive by rememberSaveable {
        mutableStateOf(false)
    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val newsArticles = state.newsPagingData?.collectAsLazyPagingItems()

    val filteredArticles = remember(query, newsArticles) {
        newsArticles?.itemSnapshotList?.items?.filter {
            it.title?.contains(query, ignoreCase = true) == true ||
                    it.author?.contains(query, ignoreCase = true) == true
        }
    }


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
                    onQueryChange = {
                        query = it
                    },
                    onActiveChanged = {
                        //isSearchActive = it
                    },
                    isSearchActive = isSearchActive,
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
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
                        val articles = if (query.isNotEmpty()) filteredArticles else newsArticles?.itemSnapshotList?.items

                        articles?.let { data ->
                            items(data.size) {
                                NewsContent(data[it]) {
                                    actions.onOpenDetailsAction(
                                        data[it].url ?: "",
                                        data[it].title ?: "",
                                        data[it].author ?: ""
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}