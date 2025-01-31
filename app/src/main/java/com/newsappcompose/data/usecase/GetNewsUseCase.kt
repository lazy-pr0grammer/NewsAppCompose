package com.newsappcompose.data.usecase

import androidx.paging.PagingData
import com.newsappcompose.data.model.article.Article
import com.newsappcompose.data.repository.NewsRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepositoryImpl
){
    operator fun invoke(): Flow<PagingData<Article>> {
        return /*newsRepository.everything(com.newsappcompose.)*/ TODO()
    }
}