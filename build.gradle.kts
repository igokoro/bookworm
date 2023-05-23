//buildscript {
//    dependencies {
//        classpath("com.squareup.wire:wire-gradle-plugin:4.6.2")
//    }
//}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.hilt) apply false
    id("com.squareup.wire") version "4.6.2" apply false
//    alias(libs.plugins.detekt) apply false
//    alias(libs.plugins.spotless) apply false
//    alias(libs.plugins.paparazzi.plugin) apply false
//    alias(libs.plugins.google.services) apply false
}
