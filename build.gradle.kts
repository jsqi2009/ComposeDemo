// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.hiltAndroid) apply false
}

// 强制使用兼容的 JavaPoet 版本来解决版本冲突
configurations.all {
    resolutionStrategy {
        force("com.squareup:javapoet:1.13.0")
    }
}