package com.jsqi.composedemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.common.lib_base.base.BaseActivity
import com.common.lib_base.utils.ToastUtil
import com.jsqi.composedemo.MainViewModel
import com.jsqi.composedemo.R
import com.jsqi.composedemo.bean.UiState
import com.jsqi.composedemo.ui.splash.AppScreen
import com.jsqi.composedemo.ui.theme.ComposeDemoTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var navHostController: NavHostController
    /** 上一次点击返回键的时间 */
    private var lastBackMills: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navHostController = rememberNavController()
            AppScreen(navHostController)
        }

        // 返回处理
        onBackPressedDispatcher.addCallback(this, mBackPressedCallback)
    }

    /** 返回监听回调 */
    private val mBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - lastBackMills > 2000) {
                lastBackMills = System.currentTimeMillis()
                ToastUtil.showShort(
                    this@MainActivity,
                    getString(R.string.toast_double_back_exit)
                )
            } else {
                finish()
            }
        }
    }
}