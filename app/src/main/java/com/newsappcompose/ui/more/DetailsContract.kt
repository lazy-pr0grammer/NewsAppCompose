package com.newsappcompose.ui.more

data class DetailsState(
    val isLoading: Boolean? = false,
    val requestUrl: String? = null,
    val requestKey: String? = null,
    val source: String? = null
)

data class DetailsActions(
    val onBackAction: () -> Unit = {},
)