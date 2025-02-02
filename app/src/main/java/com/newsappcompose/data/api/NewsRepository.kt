package com.newsappcompose.data.api

import androidx.paging.PagingData
import com.newsappcompose.data.model.article.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun everything(
        apiKey: String
    ): Flow<PagingData<Article>>
}