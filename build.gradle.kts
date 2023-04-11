// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
//    ext.hilt_version = '2.42'
    dependencies {
        classpath(Plugins.CLASSPATH_DAGGER_HILT)
//        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.42")

    }
}
plugins {
    id ("com.android.application") version "7.2.1" apply false
    id ("com.android.library") version "7.2.1" apply false
    id ("org.jetbrains.kotlin.android") version "1.7.10" apply false
    id("androidx.benchmark") version "1.1.0-beta04" apply false
    id("com.android.test") version "7.2.1" apply false
}
task<Delete>("clean") {
    delete=setOf( rootProject.buildDir)
}