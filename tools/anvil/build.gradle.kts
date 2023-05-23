plugins {
    id("igokoro.android.library")
}

android {
    namespace = "dev.igokoro.bookworm.anvil"
}

dependencies {

    api(libs.anvil.annotations)
    api(libs.javax.inject)
    api(libs.dagger)
}
