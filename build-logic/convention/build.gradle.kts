plugins {
    `kotlin-dsl`
}

group = "soup.compose.material.motion.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.android.pluginGradle)
    implementation(libs.kotlin.pluginGradle)
    implementation(libs.compose.compiler.pluginGradle)
    implementation(libs.metalava.pluginGradle)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "mmc.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "mmc.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("kotlinAndroid") {
            id = "mmc.kotlin.android"
            implementationClass = "KotlinAndroidConventionPlugin"
        }
        register("kotlinMultiplatform") {
            id = "mmc.kotlin.multiplatform"
            implementationClass = "KotlinMultiplatformConventionPlugin"
        }
        register("compose") {
            id = "mmc.compose"
            implementationClass = "ComposeMultiplatformConventionPlugin"
        }
        register("metalava") {
            id = "mmc.metalava"
            implementationClass = "MetalavaConventionPlugin"
        }
    }
}
