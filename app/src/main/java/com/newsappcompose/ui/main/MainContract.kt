package com.newsappcompose.ui.main

import androidx.paging.PagingData
import com.newsappcompose.data.model.article.Article
import kotlinx.coroutines.flow.StateFlow

data class MainState(
    val refreshing: Boolean? = false,
    val exception: Exception? = null,
    val newsPagingData: StateFlow<PagingData<Article>>? = null
)

data class MainActions(
    val onRefreshAction: () -> Unit = {},
)