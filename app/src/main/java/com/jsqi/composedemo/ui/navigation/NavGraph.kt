package com.jsqi.composedemo.ui.navigation

import android.os.Build
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jsqi.composedemo.bean.Article
import com.jsqi.composedemo.ui.auth.LoginPage
import com.jsqi.composedemo.ui.main.MainPage
import com.jsqi.composedemo.ui.main.home.HomePage
import com.jsqi.composedemo.ui.splash.SplashScreen

/**
 * 统一的导航图，管理所有路由
 * @param navHostController 导航控制器
 * @param startDestination 初始路由，由 AppScreen 根据应用状态决定
 */
@Composable
fun NavGraph(
    navHostController: NavHostController,
    startDestination: String,
    paddingValues: PaddingValues? = null
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = paddingValues?.let { Modifier.padding(it) } ?: Modifier
    ) {
        // 首次使用引导页
        composable(Route.SPLASH) {
            SplashScreen(
                navHostController = navHostController,
                onFinish = {
                    // Splash 完成后的导航逻辑在 SplashScreen 内部处理
                }
            )
        }

        // 登录页
        composable(Route.LOGIN) {
            LoginPage(navHostController = navHostController)
        }

        // 主页面（包含 BottomBar）
        // Route.MAIN 作为入口，自动重定向到 HOME，确保 BottomBar 显示
        composable(Route.MAIN) {
            androidx.compose.runtime.LaunchedEffect(Unit) {
                navHostController.navigate(Route.HOME) {
                    // 清除 MAIN 路由，避免返回栈中有多余的路由
                    popUpTo(Route.MAIN) { inclusive = true }
                }
            }
        }

        // 底部导航栏的各个 Tab 页面
        // 这些路由都显示 MainPage（包含 BottomBar），MainPage 内部根据当前路由显示对应内容
        // 使用 saveState 和 restoreState 来保持页面状态，避免切换时的闪烁
        composable(
            route = Route.HOME,
        ) {
            MainPage(navHostController)
        }
        composable(
            route = Route.PROJECT,
        ) {
            MainPage(navHostController)
        }
        composable(
            route = Route.SQUARE,
        ) {
            MainPage(navHostController)
        }
        composable(
            route = Route.WECHAT,
        ) {
            MainPage(navHostController)
        }
        composable(
            route = Route.MINE,
        ) {
            MainPage(navHostController)
        }

        /*composable(Route.MAIN) {
            MainPage(navHostController)
        }
        composable(Route.HOME) {
            HomePage(onSearch = {
                navHostController.navigate(Route.Search)
            }, onBannerClick = {
                navHostController.navigate(
                    Route.WEB,
                    bundleOf(
                        "webType" to WebType.Url(name = it.title, link = it.url)
                    )
                )
            }) {
                navToWeb(navHostController, it)
            }
        }
        composable(Route.PROJECT) {
            ProjectPage {
                navToWeb(navHostController, it)
            }
        }
        composable(Route.SYSTEM_DETAILS) {
            it.arguments?.apply {
                val structure = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelable("structure", Structure::class.java)
                } else {
                    getParcelable("structure")
                }
                val pageIndex = getInt("pageIndex")
                structure?.let { it1 ->
                    SystemDetailsPage(navHostController, it1, pageIndex) { article ->
                        navToWeb(navHostController, article)
                    }
                }
            }
        }
        composable(Route.SQUARE) {
            SquarePage(navHostController,
                onStructureClick = { structure, pageIndex ->
                    navHostController.navigate(
                        Route.SYSTEM_DETAILS,
                        bundleOf("structure" to structure, "pageIndex" to pageIndex)
                    )
                },
                onNavigationClick = { navToWeb(navHostController, it) },
                onArticleClick = { navToWeb(navHostController, it) })
        }
        composable(Route.WECHAT) {
            WechatPage {
                navToWeb(navHostController, it)
            }
        }
        composable(Route.MINE) {
            MinePage(navHostController = navHostController)
        }
        composable(Route.LOGIN) {
            LoginPage(navHostController = navHostController)
        }
        composable(Route.REGISTER) {
            RegisterPage(navHostController = navHostController)
        }
        composable(Route.MY_COLLECT) {
            CollectPage(navHostController = navHostController, onCollectUrlClick = {
                navHostController.navigate(
                    Route.WEB,
                    bundleOf(
                        "webType" to WebType.Url(it.id, it.name, it.link),
                        "collectedFlag" to "1"
                    )
                )
            }) {
                navHostController.navigate(
                    Route.WEB,
                    bundleOf(
                        "webType" to WebType.OnSiteArticle(it.id, it.link),
                        "collectedFlag" to "1"
                    )
                )
            }
        }
        composable(Route.SETTING) {
            SettingPage(navHostController = navHostController)
        }
        composable(Route.INTEGRAL_RANK) {
            IntegralRankPage(navHostController = navHostController)
        }
        composable(Route.INTEGRAL_RANK_RECORD) {
            IntegralRecordPage(navHostController = navHostController)
        }
        composable(Route.SHARE_LIST) {
            MyArticlePage(navHostController = navHostController) {
                navHostController.navigate(
                    Route.WEB,
                    bundleOf(
                        "webType" to WebType.OnSiteArticle(it.id, it.link),
                        "collectedFlag" to "1"
                    )
                )
            }
        }
        composable(Route.ADD_ARTICLE) {
            AddArticlePage(navHostController = navHostController)
        }
        composable(Route.WEB) {
            it.arguments?.apply {
                val webType: WebType? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelable("webType", WebType::class.java)
                } else {
                    getParcelable("webType")
                }
                val collectedFlag: String? = getString("collectedFlag")
                webType?.let { it1 ->
                    WebPage(
                        webType = it1,
                        collectedFlag = collectedFlag,
                        navHostController = navHostController
                    )
                }
            }
        }
        composable(Route.Search) {
            SearchPage(navHostController = navHostController) {
                navHostController.navigate(
                    Route.SEARCH_RECORD,
                    bundleOf("searchKey" to it)
                )
            }
        }
        composable(Route.SEARCH_RECORD) {
            it.arguments?.getString("searchKey")
                ?.let { searchKey ->
                    SearchResultPage(
                        navHostController = navHostController,
                        searchKey
                    ) { article ->
                        navHostController.navigate(Route.WEB, bundleOf("url" to article.link))
                    }
                }
        }*/
    }
}

/**
 * 跳转到网页
 */
private fun navToWeb(
    navHostController: NavHostController,
    it: Article
) {
    /*navHostController.navigate(
        Route.WEB,
        bundleOf(
            "webType" to WebType.OnSiteArticle(it.id, it.link),
            "collectedFlag" to if (it.collect) "1" else "0"
        )
    )*/
}

object Route {
    const val SPLASH = "splash"
    const val MAIN = "main"
    const val HOME = "home"
    const val PROJECT = "project"
    const val SQUARE = "square"
    const val WECHAT = "wechat"
    const val MINE = "mine"
    const val MY_COLLECT = "myCollect"
    const val SETTING = "setting"
    const val WEB = "web"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val SHARE_LIST = "share_list"
    const val ADD_ARTICLE = "add_article"
    const val INTEGRAL_RANK = "integral_rank"
    const val INTEGRAL_RANK_RECORD = "integral_rank_record"
    const val Search = "search"
    const val SEARCH_RECORD = "search_record"
    const val SYSTEM_DETAILS = "system_details"
}

