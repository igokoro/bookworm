plugins {
    id("igokoro.android.library")
//    id("igokoro.android.hilt")
    kotlin("kapt")
    alias(libs.plugins.ksp)
    alias(libs.plugins.anvil)
    kotlin("plugin.parcelize")
    id("igokoro.compose")
}

android {
    namespace = "dev.igokoro.bookworm.features.library.assets"
}

anvil {
    generateDaggerFactories.set(true)
}

dependencies {
    implementation(projects.dataLayer)
    implementation(projects.tools.anvil)

//    kapt(libs.dagger.compiler)
    ksp(libs.circuit.codegen)

    implementation(libs.circuit.foundation)
    implementation(libs.circuit.retained)
    implementation(libs.circuit.overlay)
    implementation(libs.circuit.codegen.annotations)

    implementation(libs.kotlin.collections.immutable)
    implementation(libs.bundles.compose)
    implementation(libs.accompanist.pager)
    implementation(libs.bundles.androidx)
}
