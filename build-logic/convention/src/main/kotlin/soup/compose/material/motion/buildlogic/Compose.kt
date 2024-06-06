package soup.compose.material.motion.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

fun Project.configureCompose() {
    pluginManager.apply("org.jetbrains.compose")
    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

    extensions.configure<ComposeCompilerGradlePluginExtension> {
        enableStrongSkippingMode = false
    }
}
