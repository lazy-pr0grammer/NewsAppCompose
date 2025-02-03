package com.newsappcompose.ui.more

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(@ApplicationContext context: Context): ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    private val _requestUrl = MutableStateFlow<String?>(null)
    private val _requestKey = MutableStateFlow<String?>(null)

    val state = combine(_isLoading, _requestUrl, _requestKey) {isLoading, requestUrl, requestKey ->
        DetailsState(
            isLoading,
            requestUrl,
            requestKey
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DetailsState()
    )

    fun isLoading(boolean: Boolean) {
        viewModelScope.launch {
            _isLoading.value = boolean
        }
    }
}