import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("igokoro.android.application")
    id("igokoro.compose")
    kotlin("kapt")
    alias(libs.plugins.ksp)
//    id("igokoro.android.hilt")
    alias(libs.plugins.anvil)
}

android {
    namespace = "dev.igokoro.bookworm"
    compileSdk = 33
}

//anvil {
//    generateDaggerFactories.set(true)
//}

dependencies {
    kapt(libs.dagger.compiler)
    ksp(libs.circuit.codegen)

//    implementation(projects.features.landing)
    implementation(projects.features.libraryAssets)
    implementation(projects.features.addLibraryCard)
//    implementation(projects.screens.challengeDetails)

    implementation(projects.dataLayer)
    implementation(projects.network)
    implementation(projects.tools.anvil)

    implementation(libs.circuit.foundation)
    implementation(libs.circuit.overlay)

//    implementation(libs.bundles.androidx.navigation)
    implementation(libs.kotlin.collections.immutable)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.androidx)
    implementation(libs.androidx.compose.ui.viewbinding)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}
