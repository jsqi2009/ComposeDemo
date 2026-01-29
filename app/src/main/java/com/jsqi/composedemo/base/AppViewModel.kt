package com.jsqi.composedemo.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.common.lib_base.base.BaseAppViewModel
import com.jsqi.composedemo.bean.CollectData
import com.jsqi.composedemo.bean.User
import com.jsqi.composedemo.local.UserManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * author : Jason
 * date   : 2026/1/7 13:37
 * desc   :
 */
class AppViewModel: BaseAppViewModel() {

    /** 全局用户 */
    private val _user = MutableStateFlow(UserManager.getUser())
    val user: StateFlow<User?> = _user

    /** 分享添加文章 */
    private val _shareArticleEvent = MutableLiveData<Boolean>()
    val shareArticleEvent: LiveData<Boolean> = _shareArticleEvent

    /** 文章收藏 */
    private val _collectEvent = MutableLiveData<CollectData>()
    val collectEvent: LiveData<CollectData> = _collectEvent

    /** emit全局用户 */
    fun emitUser(user: User?) {
        _user.value = user
    }

    /** 发送分享文章成功的消息 */
    fun emitShareArticleSuccess() {
        _shareArticleEvent.value = true
    }

    /** 发送收藏/取消收藏成功的消息 */
    fun emitCollectEvent(collectData: CollectData) {
        _collectEvent.value = collectData
    }
}