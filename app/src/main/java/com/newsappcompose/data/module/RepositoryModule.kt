package com.newsappcompose.data.module

import com.newsappcompose.data.api.NewsApis
import com.newsappcompose.data.repository.NewsRepositoryImpl
import com.newsappcompose.data.usecase.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideNewsRepository(newsApis: NewsApis): NewsRepositoryImpl {
        return NewsRepositoryImpl(newsApis)
    }

    @Provides
    @Singleton
    fun provideGetNewsCase(repositoryImpl: NewsRepositoryImpl) = GetNewsUseCase(repositoryImpl)
}