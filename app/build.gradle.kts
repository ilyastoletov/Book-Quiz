plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.dev.bookquiz"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dev.bookquiz"
        minSdk = 24
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Dependencies.Core.ktx)
    implementation(Dependencies.Core.kotlinBom)
    implementation(Dependencies.Core.lifecycleRuntime)
    implementation(Dependencies.Compose.activityCompose)
    implementation(Dependencies.Compose.lifecycleCompose)
    implementation(Dependencies.Compose.composeBom)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.uiGraphics)
    implementation(Dependencies.Compose.uiToolingPreview)
    implementation(Dependencies.Compose.material3)

    implementation(Dependencies.Compose.navigation)
    implementation(Dependencies.Compose.coil)

    implementation(Dependencies.Hilt.hilt)
    implementation(Dependencies.Hilt.navigationCompose)
    kapt(Dependencies.Hilt.kaptCompiler)

    debugImplementation(Dependencies.Compose.uiTooling)
    debugImplementation(Dependencies.Compose.testManifest)
}