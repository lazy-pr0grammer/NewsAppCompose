package com.newsappcompose.data.model

import com.google.gson.annotations.SerializedName
import com.newsappcompose.data.model.article.Article

data class NewsResponse(
    @SerializedName("articles")
    val articles: List<Article?>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("message")
    val message: String?,
)