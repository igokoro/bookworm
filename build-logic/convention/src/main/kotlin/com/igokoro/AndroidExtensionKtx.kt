package com.igokoro

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.kotlin
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal fun Project.forAndroidComponent(block: (AndroidComponentsExtension<*, *, *>) -> Unit) {
    val libraryExtension = extensions.findByType<LibraryAndroidComponentsExtension>()
    val applicationExtension = extensions.findByType<ApplicationAndroidComponentsExtension>()

    when {
        libraryExtension != null -> block(libraryExtension)
        applicationExtension != null -> block(applicationExtension)
        else -> error("QualityConventionPlugin does not support this Android module type")
    }
}

internal fun Project.forAndroidModule(block: (CommonExtension<*, *, *, *>) -> Unit) {
    val libraryExtension = extensions.findByType<ApplicationExtension>()
    val applicationExtension = extensions.findByType<LibraryExtension>()

    when {
        libraryExtension != null -> block(libraryExtension)
        applicationExtension != null -> block(applicationExtension)
        else -> error("QualityConventionPlugin does not support this Android module type")
    }
}

fun org.gradle.api.Project.`kotlin`(configure: Action<KotlinAndroidProjectExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("kotlin", configure)
