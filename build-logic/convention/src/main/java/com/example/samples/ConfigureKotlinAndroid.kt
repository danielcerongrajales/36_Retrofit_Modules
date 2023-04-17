package com.example.samples

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.io.File
import java.io.FileInputStream
import java.util.Properties

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>,
) {

    commonExtension.apply {
        compileSdk = 32

        defaultConfig {
            minSdk = 21
            testInstrumentationRunner =("androidx.test.runner.AndroidJUnitRunner")
            proguardFile("consumer-rules.pro")
            buildConfigField ("String", "MOVIE_API_KEY", "\"" + getApiKey() + "\"")
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
            isCoreLibraryDesugaringEnabled = true
        }
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }


    //version catalog
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        add("implementation", libs.findLibrary("androidx.ktx").get())
        add("implementation", libs.findLibrary("androidx.appcompat").get())
        add("implementation", libs.findLibrary("material").get())
        add("testImplementation", libs.findLibrary("junit").get())
        add("androidTestImplementation", libs.findLibrary("androidx.test.ext").get())
        add("androidTestImplementation", libs.findLibrary("androidx.appcompat").get())


    }

}
fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
fun getApiKey():Any? {
    val apikeyPropertiesFile = File("keystore.properties")
    val apikeyProperties = Properties()
    apikeyProperties.load(FileInputStream(apikeyPropertiesFile))
    return apikeyProperties.getProperty("MOVIE_API_KEY")
}

