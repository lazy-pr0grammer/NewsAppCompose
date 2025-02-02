package com.newsappcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.newsappcompose.data.api.NewsApis
import com.newsappcompose.data.api.NewsRepository
import com.newsappcompose.data.model.article.Article
import com.newsappcompose.data.repository.source.NewsDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApis: NewsApis,
) : NewsRepository {
    override fun everything(
        apiKey: String
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsDataSource(apiKey, newsApis)
            }
        ).flow
    }
}