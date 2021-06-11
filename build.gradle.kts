import com.diffplug.gradle.spotless.SpotlessExtension

buildscript {
    repositories {
        mavenLocal().mavenContent {
            includeModule("org.jetbrains.compose", "compose-gradle-plugin")
        }
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    dependencies {
        classpath(Libs.androidGradlePlugin)
        classpath(Libs.kotlinPlugin)
        classpath("org.jetbrains.compose:compose-gradle-plugin:0.4.0")
        classpath("com.diffplug.spotless:spotless-plugin-gradle:5.12.4")
    }
}

allprojects {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

subprojects {
    apply(plugin = "com.diffplug.spotless")

    configure<SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")

            ktlint("0.41.0")
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
        }
    }
}
