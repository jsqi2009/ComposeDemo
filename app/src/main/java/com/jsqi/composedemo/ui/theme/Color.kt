package com.jsqi.composedemo.ui.theme

import androidx.compose.ui.graphics.Color

data class AppColors(
    val primaryText: Color,
    val secondaryText: Color,
    val thirdText: Color,
    val grayText: Color,
    val buttonText: Color,
    val linkText: Color,
    val border: Color,
    val background: Color,
    val surface: Color,
    val error: Color,
    val success: Color,
    val grayBackground: Color,
    val grayButton: Color,
    val iconColor: Color,
    val cardBg: Color,
    val switchChecked: Color,
    val messageBannerBg: Color,
    val warnOrange: Color,
    val iconSecondaryColor: Color,
    val alertRed: Color,
    val themeColor: Color,
    val secondButtonColor: Color,
    val dividerColor: Color,
    val borderColor: Color,
    val white: Color,
    val inputText: Color,
    val toastBg: Color,


    )

// Light Theme Colors
val LightAppColors = AppColors(
    primaryText = Color(0xFF1A1A1A),
    secondaryText = Color(0xFF7A7B7D),
    grayText = Color(0xFF5B5B5B),
    buttonText = Color(0xFF1B1B1B),
    linkText = Color(0xFF1D61E7),
    border = Color(0xFFEDF1F3),
    background = Color(0xFFF6F7FB),
    surface = Color(0xFFFAFAFA),
    error = Color(0xFFFF5246),
    success = Color(0xFF02B57B),
    grayBackground = Color(0xFFEEEEEE),
    grayButton = Color(0xFFE2E1DC),
    iconColor = Color(0xFF49494B),
    cardBg = Color(0xFFFFFFFF),
    switchChecked = Color(0xFF008258),
    messageBannerBg = Color(0x1AE57017),
    warnOrange = Color(0xFFE57017),
    iconSecondaryColor = Color(0xFFC6C8CC),
    alertRed = Color(0xFFCC1414),
    themeColor = Color(0xFF008258),
    thirdText = Color(0xFF646466),
    secondButtonColor = Color(0xFF333333),
    dividerColor = Color(0xFFEDEFF2),
    borderColor = Color(0xFFDEDFE3),
    white = Color(0xFFFFFFFF),
    inputText = Color(0xFFA8AAAD),
    toastBg = Color(0xFF3D3D3D),
)

// Dark Theme Colors
val DarkAppColors = AppColors(
    border = Color(0xFF2C2E30),
    background = Color(0xFF1A1A1A),
    surface = Color(0xFF2C2E30),
    error = Color(0xFFFF7066),
    success = Color(0xFF3DCCA3),
    primaryText = Color(0xFFFFFFFF),
    secondaryText = Color(0x80FFFFFF),
    grayText = Color(0xFFA4A4A4),
    buttonText = Color(0xFFE4E4E4),
    linkText = Color(0xFF6BA4FF),
    grayBackground = Color(0xFFEEEEEE),
    grayButton = Color(0xFFE2E1DC),
    iconColor = Color(0xB2FFFFFF),
    cardBg = Color(0xFF242424),
    switchChecked = Color(0xFF008258),
    messageBannerBg = Color(0x1AE57017),
    warnOrange = Color(0xFFE57017),
    iconSecondaryColor = Color(0xFFFFFFFF).copy(alpha = 0.15f), //15%
    alertRed = Color(0xFFCC1414),
    themeColor = Color(0xFF008258),
    thirdText = Color(0xFFFFFFFF).copy(alpha = 0.5f),
    secondButtonColor = Color(0xFF242424),
    dividerColor = Color(0xFFFFFFFF).copy(alpha = 0.05f),
    borderColor = Color(0xFFFFFFFF).copy(alpha = 0.15f),
    white = Color(0xFFFFFFFF),
    inputText = Color(0xFFFFFFFF).copy(alpha = 0.3f),
    toastBg = Color(0xFF3D3D3D),


    )