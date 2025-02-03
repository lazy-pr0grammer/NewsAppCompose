package com.newsappcompose.ui.more

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.newsappcompose.ui.route.Details
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    private val _requestUrl = MutableStateFlow<String?>(null)
    private val _requestKey = MutableStateFlow<String?>(null)
    private val _requestSource = MutableStateFlow<String?>(null)

    val state = combine(_isLoading, _requestUrl, _requestKey, _requestSource) { isLoading, requestUrl, requestKey, requestSource ->
        DetailsState(
            isLoading,
            requestUrl,
            requestKey,
            requestSource
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DetailsState()
    )

    init {
        viewModelScope.launch {
            _requestUrl.value = savedStateHandle.toRoute<Details>().url
            _requestKey.value = savedStateHandle.toRoute<Details>().key
            _requestSource.value = savedStateHandle.toRoute<Details>().source
        }
    }

    fun isLoading(boolean: Boolean) {
        viewModelScope.launch {
            _isLoading.value = boolean
        }
    }
}