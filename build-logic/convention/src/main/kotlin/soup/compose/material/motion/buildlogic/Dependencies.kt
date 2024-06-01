package soup.compose.material.motion.buildlogic

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? {
    return add("implementation", dependencyNotation)
}

fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? {
    return add("androidTestImplementation", dependencyNotation)
}

fun DependencyHandler.project(
    path: String,
    configuration: String? = null,
): Dependency {
    return project(
        mapOf(
            "path" to path,
            "configuration" to configuration,
        ).filterValues { it != null }
    )
}
