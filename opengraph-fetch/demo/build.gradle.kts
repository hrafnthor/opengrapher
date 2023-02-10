plugins {
    id("kotlin")
}

dependencies {
    implementation(project(":library"))
    implementation(libs.hth.lumber)
}
