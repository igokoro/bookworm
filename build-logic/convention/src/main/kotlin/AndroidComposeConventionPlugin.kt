import com.igokoro.forAndroidModule
import com.igokoro.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            forAndroidModule {
                configureAndroidCompose(it)
            }
        }
    }
}
