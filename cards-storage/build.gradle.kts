plugins {
    id("igokoro.android.library")
//    id("igokoro.android.hilt")
    alias(libs.plugins.anvil)
    id("com.squareup.wire")
}

android {
    namespace = "dev.igokoro.bookworm.cards.storage"
}

anvil {
    generateDaggerFactories.set(true)
}

wire {
    kotlin {}
}

//protobuf {
//    // Configures the Protobuf compilation and the protoc executable
//    protoc {
//        // Downloads from the repositories
////        artifact = "com.google.protobuf:protoc:3.14.0"
//        // this will only work on Apple silicone
//        artifact = "com.google.protobuf:protoc:3.14.0:osx-x86_64"
//    }
//
//    // Generates the java Protobuf-lite code for the Protobufs in this project
//    generateProtoTasks {
//        all().configureEach {
//            builtins {
//                // Configures the task output type
//                create("java") {
//                    option("lite")
//                }
//            }
//        }
//    }
//}

dependencies {
    implementation(libs.androidx.datastore)
    implementation(libs.protobuf)

    implementation(libs.dagger)
    implementation(libs.anvil.annotations)
    implementation(projects.tools.anvil)

}
