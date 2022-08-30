plugins {
    java
    // Habit Gradle plugin
    id("io.wttech.habit")
}

project.group = "io.wttech.habit.example"

val habitVersion: String by project

repositories {
    mavenCentral()
}

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    // Habit JUnit 5 plugin
    testImplementation(libs.habit.junit)
    // JUnit 5
    testImplementation(libs.junit)
    // logback
    testImplementation(libs.logback)
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging.showStandardStreams = true
}
