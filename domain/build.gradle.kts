import extension.common
import extension.hilt
import extension.paging

plugins {
    id (Plugins.KOTLIN_KAPT_PLUGIN)
    id (Plugins.JETBRAINS_KOTLIN)
    id (Plugins.ANDROID_LIBRARY_PLUGIN)
    id (Plugins.PARCELIZE)
    id (Plugins.DAGGER_HILT_PLUGIN)


}

android {
    compileSdk = 32

    defaultConfig {
        minSdk =21
        targetSdk =32

        testInstrumentationRunner= "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles  ("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled =false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //paging 3
    paging()
    //dagger hilt
    hilt()

    //common
    common()
}