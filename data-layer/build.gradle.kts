plugins {
    id("igokoro.android.library")
//    id("igokoro.android.hilt")
    alias(libs.plugins.anvil)
}

android {
    namespace = "dev.igokoro.bookworm.data"
}

anvil {
    generateDaggerFactories.set(true)
}

dependencies {
    api(projects.cardsStorage)
    implementation(projects.network)

    implementation(libs.dagger)
    implementation(libs.anvil.annotations)
    implementation(projects.tools.anvil)

//    implementation("androidx.core:core-ktx:1.8.0")
//    implementation("androidx.appcompat:appcompat:1.4.1")
//    implementation("com.google.android.material:material:1.5.0")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
