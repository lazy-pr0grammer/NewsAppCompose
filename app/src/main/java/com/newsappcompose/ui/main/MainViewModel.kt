package com.newsappcompose.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.newsappcompose.data.model.article.Article
import com.newsappcompose.data.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetNewsUseCase
) : ViewModel() {

    private var _refreshing = MutableStateFlow(false)
    private var _exception = MutableStateFlow<Exception?>(null)
    private var _newsPagingData = MutableStateFlow<PagingData<Article>>(PagingData.empty())

    val mainState = combine(_refreshing, _exception) { refreshing, exception ->
        MainState(
            refreshing = refreshing,
            exception = exception,
            newsPagingData = _newsPagingData
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainState()
    )

    init {
        fetch()
    }

    private fun fetch() {
        viewModelScope.launch {
            useCase.invoke()
                ?.distinctUntilChanged()
                ?.cachedIn(viewModelScope)
                ?.collectLatest {
                    _newsPagingData.value = it
                }
        }
    }
}