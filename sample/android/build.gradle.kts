plugins {
    id("mmc.android.application")
    id("mmc.kotlin.android")
    id("mmc.compose")
}

android {
    namespace = "soup.compose.material.motion.sample"
    defaultConfig {
        applicationId = "soup.material.compose.sample"
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            signingConfig = signingConfigs["debug"]
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    packaging {
        resources.excludes += setOf(
            "/META-INF/AL2.0",
            "/META-INF/LGPL2.1",
        )
    }
}

dependencies {
    implementation(projects.core)
    implementation(projects.sample.shared)
    implementation(libs.androidx.activity.compose)
}
