repositories {
    mavenCentral()
    google()
}

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

gradlePlugin {
    plugins {
        register("explicit-api-mode") {
            id = "explicit-api-mode"
            implementationClass = "ExplicitApiModePlugin"
        }
    }
}

dependencies {
    implementation(libs.android.pluginGradle)
    implementation(libs.kotlin.pluginGradle)
}
