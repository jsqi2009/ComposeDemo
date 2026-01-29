plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hiltAndroid)
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.jsqi.composedemo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.jsqi.composedemo"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":lib_base"))

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.accompanist.webview)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    api(libs.material)
    api(libs.mmkv)
    api(libs.retrofit)
    api(libs.converterGson)
    api(libs.persistentCookieJar)
    api(libs.logging.interceptor)
    api(libs.coil)
    api(libs.androidx.runtime.livedata)
    api(libs.androidx.navigation.compose)

    debugImplementation(libs.leakcanary)

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.1-alpha")
}

kapt {
    correctErrorTypes = true
}