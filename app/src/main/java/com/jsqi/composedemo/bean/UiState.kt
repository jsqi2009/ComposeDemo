package com.jsqi.composedemo.bean

/**
 * author : Jason
 *  date   : 2025/12/10 16:47
 *  desc   :
 */
data class UiState(
    var isLoading: Boolean = false,
    var data: String = "",
    var error: String = ""
)