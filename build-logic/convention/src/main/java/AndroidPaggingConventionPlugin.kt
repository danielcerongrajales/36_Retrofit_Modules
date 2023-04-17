import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType


class
AndroidPaggingConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                "implementation"(libs.findLibrary("pagingCommon").get())
                "implementation"(libs.findLibrary("pagingRuntime").get())

            }

        }
    }

}