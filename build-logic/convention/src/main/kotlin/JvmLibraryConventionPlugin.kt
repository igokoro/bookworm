import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.getByType

private fun Project.configureJvm() {
    project.extensions.getByType<SourceSetContainer>()
        .run {
            getByName("main").java.srcDir("src/main/kotlin")
            getByName("test").java.srcDir("src/test/kotlin")
        }

    project.extensions.getByType<JavaPluginExtension>()
        .run {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
}
