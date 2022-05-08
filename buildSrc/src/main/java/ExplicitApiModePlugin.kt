import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Temporary workaround to enable explicitApiMode in Android module:
 * https://youtrack.jetbrains.com/issue/KT-37652
 */
class ExplicitApiModePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks
            .matching { it is KotlinCompile && it.name.contains("test", ignoreCase = true).not() }
            .configureEach {
                if (!project.hasProperty("kotlin.optOutExplicitApi")) {
                    val kotlinCompile = this as KotlinCompile
                    if (EXPLICIT_API !in kotlinCompile.kotlinOptions.freeCompilerArgs) {
                        kotlinCompile.kotlinOptions.freeCompilerArgs += EXPLICIT_API
                    }
                }
            }
    }

    companion object {
        private const val EXPLICIT_API = "-Xexplicit-api=strict"
    }
}
