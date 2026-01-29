package com.jsqi.composedemo.utils

import com.jsqi.composedemo.bean.User

object CacheConfig {

    /** theme color，0 follow system 1 light 2 night */
    var themeModel by Cache(0)
    // current home id
    var currentHomeId: Long by Cache(0)
    var isLogin by Cache(false)

    var userInfo: User by Cache(User())

}