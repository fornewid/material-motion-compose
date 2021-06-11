import dependencies.Libs
import dependencies.Versions

plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

android {
    compileSdk = Versions.compileSdk
    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        kotlinCompilerExtensionVersion = Libs.Compose.version
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    packagingOptions {
        // Multiple dependencies bring these files in. Exclude them to enable
        // our test APK to build (has no effect on our AARs)
        exclude("/META-INF/AL2.0")
        exclude("/META-INF/LGPL2.1")
    }
}

dependencies {
    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Compose.foundation)
    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.ui_util)

    androidTestImplementation(Libs.Compose.ui_test_junit4)
    androidTestImplementation(Libs.Compose.ui_test_manifest)
    androidTestImplementation(Libs.AndroidX.Test.rules)
    androidTestImplementation(Libs.AndroidX.Test.runner)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.fornewid"
                artifactId = "material-motion-compose"
                version = "0.4.7"
            }
        }
    }
}
