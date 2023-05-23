import org.gradle.api.Plugin
import org.gradle.api.Project

class PaparazziPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("app.cash.paparazzi")
            }

            tasks.configureEach {
                if(this.name == "check") {
                    this.dependsOn("verifyPaparazzi")
                }
            }
        }
    }
}
