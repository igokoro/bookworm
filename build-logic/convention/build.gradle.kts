plugins {
    `kotlin-dsl`
}

group = "com.igokoro.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "igokoro.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "igokoro.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "igokoro.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("umbrellaCompose") {
            id = "igokoro.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("umbrellaPaparazzi") {
            id = "igokoro.paparazzi"
            implementationClass = "PaparazziPlugin"
        }
    }
}
