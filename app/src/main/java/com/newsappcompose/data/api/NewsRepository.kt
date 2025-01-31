package com.newsappcompose.data.api

import androidx.paging.PagingData
import com.newsappcompose.data.model.article.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun everything(
        query: String,
        apiKey: String,
        page: Int,
        pageSize: Int
    ): Flow<PagingData<Article>>
}