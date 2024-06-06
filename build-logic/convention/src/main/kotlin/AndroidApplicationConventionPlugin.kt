import soup.compose.material.motion.buildlogic.configureAndroid
import soup.compose.material.motion.buildlogic.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }
            configureAndroid()
        }
    }
}
