plugins {
    id("kotlin")
    alias(libs.plugins.mavenPublish)
}

dependencies {
    api(libs.michaelbull.result)
    implementation(libs.jsoup)

    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.junit.jupiter)
}

tasks.withType(Test::class).configureEach {
    useJUnitPlatform()
}
