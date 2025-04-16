import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)
}


kotlin {
    jvm("desktop") {
        withJava()
        compilerOptions{
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.logger.slf4j)
            implementation(libs.compose.code.editor)
            implementation(libs.navigation.compose)
            implementation(libs.kotlinx.serialization.json.jvm)
            implementation(libs.material.icons.extended)
            implementation(project(":data"))
            implementation(project(":domain"))
        }

        val desktopMain by getting{
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutines.swing)
            }
        }
    }
}


compose.desktop {
    application {
        mainClass = "com.sushkpavel.leetcode.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.sushkpavel.leetcode"
            packageVersion = "1.0.0"
        }
    }
}
