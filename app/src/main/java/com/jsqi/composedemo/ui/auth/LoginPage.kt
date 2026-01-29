package com.jsqi.composedemo.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.common.lib_base.ui.widgets.CoilImage
import com.common.lib_base.ui.widgets.LoadingDialog
import com.common.lib_base.ui.widgets.TitleBar
import com.jsqi.composedemo.R
import com.jsqi.composedemo.components.BasicInputField
import com.jsqi.composedemo.ui.main.MainPage
import com.jsqi.composedemo.ui.navigation.Route

/**
 * author : Jason
 * date   : 2026/1/28 13:20
 * desc   :
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    navHostController: NavHostController,
    loginViewModel: LoginPageViewModel = hiltViewModel()
) {

    var username by rememberSaveable { mutableStateOf("jason801") }
    var password by rememberSaveable { mutableStateOf("123456") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var showLoadingDialog by remember { mutableStateOf(false) }

    if (showLoadingDialog) {
        LoadingDialog(loadingText = "登录中...") { showLoadingDialog = false }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TitleBar(title = stringResource(id = R.string.login)) { navHostController.popBackStack() }
        CoilImage(
            model = R.drawable.ic_about_logo,
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 48.dp)
                .size(100.dp)
        )
        BasicInputField(
            value = username,
            showValue = true,
            onValueChange = { username = it },
            placeholder = stringResource(id = R.string.user_name),
            isPassword = false,
            onToggleValueVisibility = { passwordVisibility = !passwordVisibility },
            onClear = { username = "" }
        )

        BasicInputField(
            value = password,
            showValue = true,
            onValueChange = { password = it },
            placeholder = stringResource(id = R.string.user_name),
            isPassword = false,
            onToggleValueVisibility = { passwordVisibility = !passwordVisibility },
            onClear = { password = "" }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp),
            enabled = username.trim().isNotEmpty() && password.trim().isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(0.6f),
                disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(0.8f)
            ),
            onClick = {
                // 执行登录操作
                showLoadingDialog = true
                loginViewModel.login(
                    username,
                    password,
                    errorBlock = {
                        showLoadingDialog = false
                    }) {
                    // 登录成功后的操作
                    showLoadingDialog = false
                    navHostController.navigate(Route.MAIN){
                        popUpTo(0){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }) {
            Text(text = stringResource(id = R.string.login))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp, top = 10.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = stringResource(id = R.string.register),
                modifier = Modifier.clickable {
                    navHostController.navigate(Route.REGISTER)
                },
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

}
