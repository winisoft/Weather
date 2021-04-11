buildscript {

    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        classpath(Dependencies.Android.gradlePlugin)
        classpath(Dependencies.Kotlin.gradlePlugin)
        classpath(Dependencies.Hilt.plugin)
        classpath(Dependencies.Jetpack.Navigation.navigationSafeArgsPlugin)
        classpath(Dependencies.CodeQuality.detektPlugin)
        classpath(Dependencies.CodeQuality.ktlintPlugin)
        classpath(Dependencies.CodeQuality.jacoco)
    }
}

subprojects {
    repositories {
        mavenCentral()
        jcenter()
        gradlePluginPortal()
        google()
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
    }
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
        gradlePluginPortal()
        google()
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version "1.9.1"
}

tasks["detekt"].setProperty("jvmTarget", JavaVersion.VERSION_1_8.toString())

allprojects {

    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

tasks {
    val clean by creating {
        delete(rootProject.buildDir)
    }
}