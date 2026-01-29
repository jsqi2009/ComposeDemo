package com.jsqi.composedemo.ui.auth

import com.common.lib_base.base.BaseViewModel
import com.jsqi.composedemo.MyApp
import com.jsqi.composedemo.data.DataRepository
import com.jsqi.composedemo.local.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * author : Jason
 * date   : 2026/1/28 13:22
 * desc   :
 */
@HiltViewModel
class LoginPageViewModel @Inject constructor() : BaseViewModel<String>() {


    fun login(
        userName: String,
        pwd: String,
        errorBlock: () -> Unit = {},
        successCall: () -> Unit = {}
    ) {
        launch({
            handleRequest(DataRepository.login(userName, pwd), errorBlock = {
                errorBlock()
                false
            }) {
                UserManager.saveLastUserName(userName)
                UserManager.saveUser(it.data)
                MyApp.appViewModel.emitUser(it.data)
                successCall.invoke()
            }
        })
    }
}