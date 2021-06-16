package dependencies

object Versions {
    const val minSdk = 21
    const val targetSdk = 30
    const val compileSdk = 30
    const val buildTools = "30.0.3"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0-beta04"
    const val material = "com.google.android.material:material:1.3.0"

    object Kotlin {
        private const val version = "1.5.10"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object Compose {
        const val version = "1.0.0-beta09"
        const val activity = "androidx.activity:activity-compose:1.3.0-beta02"
        const val constraintlayout =
            "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha07"
        const val navigation = "androidx.navigation:navigation-compose:2.4.0-alpha03"
        const val runtime = "androidx.compose.runtime:runtime:$version"
        const val runtime_livedata = "androidx.compose.runtime:runtime-livedata:$version"
        const val foundation = "androidx.compose.foundation:foundation:$version"
        const val foundation_layout = "androidx.compose.foundation:foundation-layout:$version"
        const val ui = "androidx.compose.ui:ui:$version"
        const val ui_test_junit4 = "androidx.compose.ui:ui-test-junit4:$version"
        const val ui_test_manifest = "androidx.compose.ui:ui-test-manifest:$version"
        const val ui_tooling = "androidx.compose.ui:ui-tooling:$version"
        const val ui_util = "androidx.compose.ui:ui-util:$version"
        const val material = "androidx.compose.material:material:$version"
        const val material_icons_extended =
            "androidx.compose.material:material-icons-extended:$version"
        const val animation = "androidx.compose.animation:animation:$version"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.0"
        const val core_ktx = "androidx.core:core-ktx:1.5.0"
        const val lifecycle_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"

        object Test {
            private const val version = "1.3.0"
            const val rules = "androidx.test:rules:$version"
            const val runner = "androidx.test:runner:$version"

            const val junit = "androidx.test.ext:junit:1.1.2"
            const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
        }
    }

    object Test {
        const val junit = "junit:junit:4.13.2"
    }
}
