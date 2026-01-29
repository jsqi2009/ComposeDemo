package com.jsqi.composedemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsqi.composedemo.bean.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * author : Jason
 *  date   : 2025/12/10 16:45
 *  desc   :
 */
class MainViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    var uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(2000)
            _uiState.update { it.copy(
                isLoading = false,
                data = "Hello World"
            ) }
        }
    }
}