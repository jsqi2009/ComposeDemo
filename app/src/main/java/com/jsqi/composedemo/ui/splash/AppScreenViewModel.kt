package com.jsqi.composedemo.ui.splash

import com.common.lib_base.base.BaseViewModel
import com.jsqi.composedemo.local.CacheManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * author : Jason
 * date   : 2026/1/7 14:12
 * desc   :
 */
@HiltViewModel
class AppScreenViewModel @Inject constructor(): BaseViewModel<Unit>() {

    private val _isFirstUse = MutableStateFlow(CacheManager.isFirstUse())
    val isFirstUse: StateFlow<Boolean> = _isFirstUse

    fun emitFirstUse(firstUse: Boolean) {
        _isFirstUse.value = firstUse
        CacheManager.saveFirstUse(firstUse)
    }
}