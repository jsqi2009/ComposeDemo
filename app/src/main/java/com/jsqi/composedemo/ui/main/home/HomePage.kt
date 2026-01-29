package com.jsqi.composedemo.ui.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.common.lib_base.ui.widgets.TitleBar
import com.jsqi.composedemo.R

/**
 * author : Jason
 * date   : 2026/1/7 16:00
 * desc   :
 */
@Composable
fun HomePage(navHostController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TitleBar(title = stringResource(id = R.string.tab_home), menu = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {  }
            )
        })
    }
}