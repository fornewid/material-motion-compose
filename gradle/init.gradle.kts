val ktlintVersion = "0.49.0"

initscript {
    val spotlessVersion = "6.22.0"

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("com.diffplug.spotless:spotless-plugin-gradle:$spotlessVersion")
    }
}

allprojects {
    if (this == rootProject) {
        return@allprojects
    }
    apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
    extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            targetExclude("bin/**/*.kt")
            ktlint(ktlintVersion).userData(mapOf("android" to "true"))
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
        }
    }
}
