rootProject.name = "desktopleetcode"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

val githubProperties = java.util.Properties().apply {
    val propertiesFile = File(settingsDir, "github.properties")
    if (propertiesFile.exists()) {
        propertiesFile.inputStream().use { load(it) }
    }
}

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven("https://maven.pkg.github.com/Qawaz/compose-code-editor") {
            credentials {
                username = System.getenv("GPR_USER")
                    ?: githubProperties.getProperty("gpr.usr")
                            ?: ""
                password = System.getenv("GPR_API_KEY")
                    ?: githubProperties.getProperty("gpr.key")
                            ?: ""
            }
        }
    }
}

include(":composeApp")
include(":data")
include(":domain")