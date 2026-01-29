package com.jsqi.composedemo.ext

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 * Safely get Activity from Context
 * @return Activity instance if found, null otherwise
 */
fun Context.getActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    return if (context is Activity) context else null
}





