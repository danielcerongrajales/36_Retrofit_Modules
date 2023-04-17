// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
//    ext.hilt_version = '2.42'
    dependencies {
        //classpath(Plugins.CLASSPATH_DAGGER_HILT)
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.44.2")

    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.android)  apply false
}
task<Delete>("clean") {
    delete=setOf( rootProject.buildDir)
}