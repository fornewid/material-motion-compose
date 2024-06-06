plugins {
    id("mmc.kotlin.multiplatform")
    id("mmc.compose")
}

kotlin {
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(projects.sample.shared)
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "soup.compose.material.motion.sample.MainKt"
    }
}
