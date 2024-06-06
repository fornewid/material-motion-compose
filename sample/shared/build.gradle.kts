plugins {
    id("mmc.android.library")
    id("mmc.kotlin.multiplatform")
    id("mmc.compose")
}

android {
    namespace = "soup.compose.material.motion.shared"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)

            implementation(compose.ui)
            implementation(compose.material)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.jetbrains.navigation.compose)
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
    }
}
