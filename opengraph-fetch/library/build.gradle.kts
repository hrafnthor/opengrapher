plugins {
    id("kotlin")
    alias(libs.plugins.mavenPublish)
}

dependencies {
    implementation(libs.skrapeit)
    implementation(libs.michaelbull.result)

    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.junit.jupiter)
}

tasks.withType(Test::class).configureEach {
    useJUnitPlatform()
}
