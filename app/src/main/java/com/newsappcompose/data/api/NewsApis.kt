package com.newsappcompose.data.api

import com.newsappcompose.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApis {
    @GET("everything")
    suspend fun everything(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("sortBy") sortBy: String = "latest",
        @Query("pageSize") pageSize: Int
    ): NewsResponse
}