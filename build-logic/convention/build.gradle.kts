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
        register("androidCompose") {
            id = "mmc.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
    }
}
