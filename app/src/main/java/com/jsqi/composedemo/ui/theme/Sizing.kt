package com.jsqi.composedemo.ui.theme

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class FontSize(
    val bigTitle: TextUnit,
    val subtitle: TextUnit,
    val body: TextUnit,
    var caption: TextUnit,
    val label: TextUnit,
    val button: TextUnit,
    val font_14: TextUnit,
    val font_15: TextUnit,
    val font_16: TextUnit,
    val font_18: TextUnit,
    val font_19: TextUnit,
    val font_20: TextUnit,
    val font_24: TextUnit,
    val font_12: TextUnit,
    val font_13: TextUnit,
    val font_17: TextUnit,
    val font_28: TextUnit,
    val font_36: TextUnit,
    val font_30: TextUnit,
)

val AppFontSizes = FontSize(
    bigTitle = 36.sp,
    subtitle = 28.sp,
    body = 18.sp,
    caption = 14.sp,
    label = 12.sp,
    button = 18.sp,
    font_14 = 14.sp,
    font_16 = 16.sp,
    font_18 = 18.sp,
    font_19 = 19.sp,
    font_20 = 20.sp,
    font_24 = 24.sp,
    font_15 = 15.sp,
    font_12 = 12.sp,
    font_13 = 13.sp,
    font_17 = 17.sp,
    font_28 = 28.sp,
    font_36 = 36.sp,
    font_30 = 30.sp,

)