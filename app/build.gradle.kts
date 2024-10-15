import org.apache.tools.ant.util.JavaEnvUtils.VERSION_1_8

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.hrap.app.asm_ph45671"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hrap.app.asm_ph45671"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildToolsVersion = "33.0.1"
    ndkVersion = "26.1.10909125"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.benchmark.macro)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
//    val nav_version = "2.8.1"
    implementation("androidx.navigation:navigation-compose:2.8.1")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("androidx.navigation:navigation-compose:2.7.2") // Phiên bản mới nhất
    implementation("androidx.activity:activity-compose:1.7.2") // Đảm bảo sử dụng phiên bản mới nhất
    implementation ("androidx.compose.runtime:runtime-livedata:1.5.1")
    implementation ("androidx.compose.ui:ui:1.3.0") // Thay thế phiên bản phù hợp
    implementation ("androidx.compose.material:material:1.3.0")
    implementation ("androidx.compose.material:material-icons-extended:1.3.0")
}