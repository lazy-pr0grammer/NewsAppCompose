package com.newsappcompose.ui.more

data class DetailsState(
    val isLoading: Boolean? = false,
)

data class DetailsActions(
    val onBackAction: () -> Unit = {},
)