rootProject.name = "gradle-example"

pluginManagement {
    val habitVersion: String by settings

    plugins {
        id("io.wttech.habit") version habitVersion
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            val habitVersion: String by settings
            val junitVersion = version("junit", "5.8.2")
            val logbackVersion = version("logback", "1.2.9")
            library("junit", "org.junit.jupiter", "junit-jupiter").versionRef(junitVersion)
            library("habit-junit", "io.wttech.habit", "junit5").version(habitVersion)
            library("logback", "ch.qos.logback", "logback-classic").versionRef(logbackVersion)
        }
    }
}
