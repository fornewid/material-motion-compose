package soup.compose.material.motion.buildlogic

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureAndroid() {
    android {
        compileSdkVersion(Versions.compileSdk)

        defaultConfig {
            minSdk = Versions.minSdk
            targetSdk = Versions.targetSdk

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }
}

private fun Project.android(action: BaseExtension.() -> Unit) {
    extensions.configure(action)
}
