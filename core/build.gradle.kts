plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.vanniktech.maven.publish")
    id("org.jetbrains.dokka")
    id("me.tylerbwong.gradle.metalava")
}

kotlin {
    android()
    jvm("desktop")
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(compose.animation)
                implementation(compose.runtime)
                implementation(compose.ui)
            }
        }
        val androidMain by getting
        val androidInstrumentedTest by getting
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
        val iosMain by getting {
            dependsOn(commonMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
    }
    explicitApiWarning()
}

android {
    namespace = "soup.compose.material.motion"

    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
        }
        named("androidTest") {
            manifest.srcFile("src/androidAndroidTest/AndroidManifest.xml")
            res.srcDirs("src/androidAndroidTest/res")
        }
    }
    buildFeatures {
        compose = true
        buildConfig = false
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    lint {
        textReport = true
        textOutput = File("stdout")
        checkReleaseBuilds = false
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    packagingOptions {
        resources.excludes += "/META-INF/AL2.0"
        resources.excludes += "/META-INF/LGPL2.1"
    }
}

metalava {
    sourcePaths.setFrom("src/commonMain")
    filename.set("api/current.api")
    reportLintsAsErrors.set(true)
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.compose.animation)
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui.ui)

    // ======================
    // Test dependencies
    // ======================

    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.compose.foundation)
    androidTestImplementation(libs.compose.ui.testJunit4)
    debugImplementation(libs.compose.ui.testManifest)
}
