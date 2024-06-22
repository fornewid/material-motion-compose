import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("mmc.compose")
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "sample"
        browser {
            commonWebpackConfig {
                outputFileName = "sample.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }

        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.sample.shared)
            implementation(projects.core)

            implementation(compose.ui)
            implementation(compose.material)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.jetbrains.navigation.compose)
        }
    }
}
