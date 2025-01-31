package com.newsappcompose.data.repository.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.newsappcompose.data.api.NewsApis
import com.newsappcompose.data.model.article.Article

class NewsDataSource(
    private val apiKey: String,
    private val newsApis: NewsApis
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val response = newsApis.everything(
                query = "bitcoin",
                apiKey = apiKey,
                page = page,
                pageSize = params.loadSize
            )
            val articles = response.articles?.map { it!! }

            LoadResult.Page(
                data = articles ?: emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (articles.isNullOrEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}