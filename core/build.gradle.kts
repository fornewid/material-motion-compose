plugins {
    id("mmc.android.library")
    id("mmc.kotlin.multiplatform")
    id("mmc.compose")
    id("mmc.metalava")
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.jetbrains.dokka)
}

kotlin {
    explicitApi()
    sourceSets {
        commonMain.dependencies {
            implementation(compose.animation)
            implementation(compose.ui)
            implementation(libs.androidx.annotation)
        }
        androidInstrumentedTest.dependencies {
            implementation(libs.androidx.test.core)
            implementation(libs.androidx.test.rules)
            implementation(libs.androidx.test.runner)
            implementation(libs.compose.foundation)
            implementation(libs.compose.ui.testJunit4)
            implementation(libs.compose.ui.testManifest)
            implementation(libs.androidx.tracing)
        }
    }
}

android {
    namespace = "soup.compose.material.motion"
}
