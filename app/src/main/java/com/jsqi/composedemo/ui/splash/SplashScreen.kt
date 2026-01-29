package com.jsqi.composedemo.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.common.lib_base.ui.widgets.Banner
import com.jsqi.composedemo.R

/**
 * author : Jason
 * date   : 2026/1/7 14:11
 * desc   :
 */
@Composable
fun SplashScreen(
    navHostController: NavHostController,
    onFinish: () -> Unit
) {
    val imageList = listOf(R.drawable.login_top, R.drawable.login_top, R.drawable.login_top)
    val pagerState = rememberPagerState { imageList.size }

    Box(contentAlignment = Alignment.BottomCenter) {
        Banner(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            pagerState = pagerState,
            images = listOf(R.drawable.login_top, R.drawable.login_top, R.drawable.login_top),
            autoScroll = false,
            indicatorSize = 10,
            indicatorModifier = Modifier.padding(bottom = 50.dp)
        )
        if (pagerState.currentPage == imageList.size - 1) {
            Button(
                modifier = Modifier.padding(bottom = 90.dp),
                onClick = {
                    onFinish()  // 更新首次使用状态
                    // 根据登录状态导航到对应页面
                    val destination = if (com.jsqi.composedemo.utils.CacheConfig.isLogin) {
                        com.jsqi.composedemo.ui.navigation.Route.HOME  // 已登录，导航到首页
                    } else {
                        com.jsqi.composedemo.ui.navigation.Route.LOGIN  // 未登录，导航到登录页
                    }
                    navHostController.navigate(destination) {
                        // 清除 Splash 页面，避免返回
                        popUpTo(com.jsqi.composedemo.ui.navigation.Route.SPLASH) { inclusive = true }
                    }
                }
            ) {
                Text(text = stringResource(id = R.string.enter_immediately))
            }
        }
    }
}