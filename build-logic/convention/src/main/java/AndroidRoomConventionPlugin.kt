import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin-kapt")
            }


            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                //ROOM
                "implementation"(libs.findLibrary("roomRuntime").get())
                "implementation"(libs.findLibrary("roomCompile").get())
                "implementation"(libs.findLibrary("roomKtx").get())
                "kapt"(libs.findLibrary("roomCompile").get())
            }



        }
    }

}