package com.jsqi.composedemo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.jsqi.composedemo.R
import com.jsqi.composedemo.ui.theme.AppTheme

/**
 * author : Jason
 * date   : 2026/1/28 13:43
 * desc   :
 */
@Composable
fun BasicInputField(
    value: String,
    showValue: Boolean,
    showClear: Boolean = false,
    isPassword: Boolean = true,
    placeholder: String? = "",
    onToggleValueVisibility: () -> Unit,
    onClear: () -> Unit,
    onValueChange: (String) -> Unit,
) {

    val appColors = AppTheme.colors
    val fontSizes = AppTheme.fontSizes

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = appColors.cardBg,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            visualTransformation = if (showValue)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            keyboardOptions =
                if (isPassword) {
                    KeyboardOptions(keyboardType = KeyboardType.Password)
                } else {
                    KeyboardOptions(keyboardType = KeyboardType.Unspecified)
                },
            singleLine = true,
            modifier = Modifier.weight(1f),
            textStyle = LocalTextStyle.current.copy(
                color = appColors.primaryText,
                fontSize = fontSizes.body
            ),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    placeholder?.let {
                        Text(
                            text = it,
                            color = appColors.secondaryText
                        )
                    }
                }
                innerTextField()
            }
        )
        if (isPassword) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = onToggleValueVisibility
            ) {
                Icon(
                    painter = painterResource(
                        id = if (showValue) R.drawable.ic_eye_on else R.drawable.ic_eye_off
                    ),
                    contentDescription = if (showValue) "Hide password" else "Show password",
                    tint = appColors.iconColor
                )
            }
        }
        if (showClear && value.isNotEmpty()) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = onClear
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_clear
                    ),
                    contentDescription = if (showValue) "Hide password" else "Show password",
                    tint = appColors.iconColor
                )
            }
        }
    }
}