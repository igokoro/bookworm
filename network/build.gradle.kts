plugins {
    id("igokoro.android.library")
//    id("igokoro.android.hilt")
    alias(libs.plugins.anvil)
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.igokoro.bookworm.network"
}

anvil {
    generateDaggerFactories.set(true)
}

dependencies {

    api(libs.retrofit)
    implementation(libs.retrofit.moshi)
    ksp(libs.moshi.ksp)
    api(libs.moshi)
    implementation(libs.moshi.adapters)
    api(libs.okhttp)
    implementation(libs.okhttp.logging)

    implementation(libs.dagger)
    implementation(libs.anvil.annotations)
    implementation(projects.tools.anvil)
}
