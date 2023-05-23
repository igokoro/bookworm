enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
//    resolutionStrategy { // THIS IS IMPORTANT
//        // For some reason, Gradle does a lookup on the wrong coordinates:
//        // 'com.squareup.wire:com.squareup.wire.gradle.plugin'
//        // instead of the one below...
//        eachPlugin {
//            if (requested.id.id == "com.squareup.wire") {
//                useModule("com.squareup.wire:wire-gradle-plugin:${requested.version}")
//            }
//        }
//    }

    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }


}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "bookworm"
include(":app")
include(":data-layer")
include(":network")
include(":cards-storage")
include(":features:landing")
include(":tools:anvil")
include(":features:library-assets")
include(":features:add-library-card")
