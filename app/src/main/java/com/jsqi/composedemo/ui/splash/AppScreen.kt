package com.jsqi.composedemo.ui.splash

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jsqi.composedemo.ext.decorFitsSystemWindows
import com.jsqi.composedemo.ui.navigation.NavGraph
import com.jsqi.composedemo.ui.navigation.Route
import com.jsqi.composedemo.ui.theme.ComposeDemoTheme
import com.jsqi.composedemo.utils.CacheConfig

/**
 * 应用入口屏幕，负责：
 * 1. 根据应用状态决定初始路由（首次使用 -> SPLASH，已登录 -> MAIN，未登录 -> LOGIN）
 * 2. 统一管理导航图
 * 3. 处理状态栏样式
 */
@Composable
fun AppScreen(
    navHostController: NavHostController,
    appScreenViewModel: AppScreenViewModel = hiltViewModel()
) {
    val window = (LocalContext.current as? Activity)?.window
    val isFirstUse by appScreenViewModel.isFirstUse.collectAsState()

    // 根据应用状态决定初始路由
    val startDestination = when {
        isFirstUse -> Route.SPLASH  // 首次使用，显示引导页
        CacheConfig.isLogin -> Route.HOME  // 已登录，进入首页（MainPage）
        else -> Route.LOGIN  // 未登录，进入登录页
    }

    // 根据当前状态设置状态栏样式
    LaunchedEffect(isFirstUse) {
        window?.decorFitsSystemWindows(!isFirstUse)
    }

    ComposeDemoTheme(isStatusBarTransparent = isFirstUse) {
        // 统一管理导航图，根据应用状态设置初始路由
        NavGraph(
            navHostController = navHostController,
            startDestination = startDestination
        )
    }
}