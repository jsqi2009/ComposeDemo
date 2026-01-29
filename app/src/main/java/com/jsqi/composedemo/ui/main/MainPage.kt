package com.jsqi.composedemo.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jsqi.composedemo.R
import com.jsqi.composedemo.ui.main.home.HomePage
import com.jsqi.composedemo.ui.navigation.Route

/**
 * 主页面，包含 BottomBar 和内容区域
 * 注意：这里不再包含 NavGraph，而是根据当前路由显示对应内容
 * NavGraph 在 AppScreen 中统一管理
 */
@Composable
fun MainPage(navHostController: NavHostController) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination

    // 使用 remember 缓存 navBarItems，避免每次重组都创建新列表
    val navBarItems = remember {
        listOf(
        NavBarItem.Home,
        NavBarItem.Project,
        NavBarItem.Square,
        NavBarItem.Wechat,
        NavBarItem.Mine
    )
    }

    // 判断是否应该显示 BottomBar
    val shouldShowBottomBar = remember(destination?.route) {
        destination?.route == Route.MAIN || 
        destination?.hierarchy?.any {
            navBarItems.map { navBarItem -> navBarItem.route }
                .contains(it.route)
        } == true
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            // 使用 key 来保持 BottomBar 的稳定性，避免不必要的重组
            if (shouldShowBottomBar) {
                androidx.compose.runtime.key("main_bottom_bar") {
                BottomBar(navHostController, destination)
                }
            }
        }
    ) { paddingValues ->
        // 使用 key 来保持内容区域的稳定性，根据路由切换内容
        Box(modifier = Modifier.padding(paddingValues)) {
            val currentRoute = destination?.route ?: Route.HOME
            androidx.compose.runtime.key(currentRoute) {
                when (currentRoute) {
                    Route.MAIN -> HomePage(navHostController = navHostController)
                    Route.HOME -> HomePage(navHostController = navHostController)
                    Route.PROJECT -> HomePage(navHostController = navHostController)
                    Route.SQUARE -> HomePage(navHostController = navHostController)
                    Route.WECHAT -> HomePage(navHostController = navHostController)
                    Route.MINE -> HomePage(navHostController = navHostController)
                    // 默认显示首页
                    else -> HomePage(navHostController = navHostController)
                }
            }
        }
    }
}

val navBarItems = listOf(
    NavBarItem.Home,
    NavBarItem.Project,
    NavBarItem.Square,
    NavBarItem.Wechat,
    NavBarItem.Mine
)

@Composable
fun BottomBar(navController: NavController, navDestination: NavDestination?) {
    // 使用 remember 缓存 navBarItems，避免每次重组都创建新列表
    val items = remember {
        listOf(
            NavBarItem.Home,
            NavBarItem.Project,
            NavBarItem.Square,
            NavBarItem.Wechat,
            NavBarItem.Mine
        )
    }
    
    // 不喜欢material3的NavigationBar效果，故使用的是material的BottomNavigation
    // 注意内部的Text等组件也得搭配material的，不然效果出不来
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primary
    ) {
        items.forEach { item ->
            val isSelected = remember(navDestination?.route, item.route) {
                navDestination?.hierarchy?.any { it.route == item.route } == true
            }
            
            BottomNavigationItem(
                selected = isSelected,
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.route,
                        modifier = Modifier
                            .size(22.dp)
                            .padding(bottom = 4.dp)
                    )
                },
                selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                unselectedContentColor = MaterialTheme.colorScheme.onPrimary.copy(0.3f),
                label = {
                    Text(
                        text = stringResource(id = item.label),
                        fontSize = 12.sp
                    )
                },
                onClick = {
                    // 如果点击的是当前已选中的 Tab，不执行导航，避免闪烁
                    if (!isSelected) {
                        navController.navigate(item.route) {
                            // 找到第一个 Tab 路由作为根路由
                            val firstTabRoute = items.firstOrNull()?.route
                            if (firstTabRoute != null) {
                                popUpTo(firstTabRoute) {
                                    // 跳转时保存页面状态
                                    saveState = true
                                    // 不回退到根路由，保留根路由
                                    inclusive = false
                                }
                            }
                            // 栈顶复用，避免重复点击同一个导航按钮，回退栈中多次创建实例
                            launchSingleTop = true
                            // 回退时恢复页面状态
                            restoreState = true
                        }
                    }
                })
        }
    }
}

sealed class NavBarItem(val label: Int, val icon: Int, val route: String) {
    object Home : NavBarItem(R.string.tab_home, R.drawable.ic_tab_home, Route.HOME)
    object Project : NavBarItem(R.string.tab_project, R.drawable.ic_tab_project, Route.PROJECT)
    object Square : NavBarItem(R.string.tab_square, R.drawable.ic_tab_square, Route.SQUARE)
    object Wechat : NavBarItem(R.string.tab_wechat, R.drawable.ic_tab_wechat, Route.WECHAT)
    object Mine : NavBarItem(R.string.tab_mine, R.drawable.ic_tab_mine, Route.MINE)
}