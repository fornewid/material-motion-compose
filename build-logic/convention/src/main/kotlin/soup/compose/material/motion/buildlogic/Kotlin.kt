package soup.compose.material.motion.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

fun Project.configureKotlin() {
    tasks.withType<KotlinCompilationTask<*>>().configureEach {
        compilerOptions {
            if (this is KotlinJvmCompilerOptions) {
                jvmTarget.set(JvmTarget.JVM_11)
            }

            freeCompilerArgs.addAll(
                "-opt-in=kotlin.RequiresOptIn",
            )
        }
    }
}
