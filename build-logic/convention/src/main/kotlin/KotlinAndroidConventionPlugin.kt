import org.gradle.api.Plugin
import org.gradle.api.Project
import soup.compose.material.motion.buildlogic.configureKotlin

class KotlinAndroidConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
            }

            configureKotlin()
        }
    }
}
