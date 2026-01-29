package com.jsqi.composedemo

import android.content.Context
import com.common.lib_base.BaseApp
import com.jsqi.composedemo.base.AppViewModel
import dagger.hilt.android.HiltAndroidApp
import kotlin.collections.get

/**
 * author : Jason
 * date   : 2026/1/7 13:41
 * desc   :
 */
@HiltAndroidApp
class MyApp: BaseApp () {

    companion object {
        lateinit var appViewModel: AppViewModel
        @Volatile
        lateinit var instance: MyApp
            private set

        val context: Context
            get() = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appViewModel = getAppViewModelProvider()[AppViewModel::class.java]

        // bugly初始化
    }
}