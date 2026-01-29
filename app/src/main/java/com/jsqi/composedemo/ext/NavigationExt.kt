package com.jsqi.composedemo.ext

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions


fun NavController.navigateWithArgs(route: String, args: Bundle? = null, finishCurrent: Boolean = false) {
    val options: NavOptions? = if (finishCurrent) {
        currentBackStackEntry?.destination?.id?.let { currentId ->
            navOptions {
                popUpTo(currentId) {
                    inclusive = true
                }
            }
        }
    } else null

    graph.findNode(route)?.let {
        navigate(it.id, args, options)
    }
}

fun NavController.navigateWithNoArgs(route: String, finishCurrent: Boolean = false) {
    val options: NavOptions? = if (finishCurrent) {
        currentBackStackEntry?.destination?.id?.let { currentId ->
            navOptions {
                popUpTo(currentId) {
                    inclusive = true
                }
            }
        }
    } else null

    graph.findNode(route)?.let {
        navigate(it.id, null, options)
    }
}