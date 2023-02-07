import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.kotlin.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.benmanes.dependencyversions)
    alias(libs.plugins.kotlin.jvm) apply false
}

allprojects {

    repositories {
        mavenCentral()
        google()
    }

    tasks.withType(Test::class).configureEach {
        testLogging {
            if (System.getenv("CI") == "true") {
                events("PASSED", "FAILED", "SKIPPED")
            }
            setExceptionFormat("full")
        }
    }

    tasks.withType(JavaCompile::class).configureEach {
        options.encoding = "UTF-8"
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()
    }
    tasks.withType(KotlinCompile::class).configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}
