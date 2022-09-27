import extension.*
import java.util.*
import java.io.FileInputStream
plugins {

    id(Plugins.ANDROID_LIBRARY_PLUGIN)
    id(Plugins.JETBRAINS_KOTLIN)
    id(Plugins.KOTLIN_KAPT_PLUGIN)
    id(Plugins.PARCELIZE)
    id(Plugins.DAGGER_HILT_PLUGIN)


}

android {
    compileSdk =32

    defaultConfig {
        minSdk =21
        targetSdk= 32

        testInstrumentationRunner =("androidx.test.runner.AndroidJUnitRunner")
        consumerProguardFiles ("consumer-rules.pro")
        buildConfigField ("String", "MOVIE_API_KEY", "\"" + getApiKey() + "\"")

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled =false
            proguardFiles (
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility= JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation (project(":domain"))
    //paging 3
    paging()
    //ROOM
    room()
    roomPaging()
    // Retrofit
    retrofit()
    //dagger hilt
    hilt()

    //common
    common()

}

fun getApiKey():Any? {
    val apikeyPropertiesFile = File("keystore.properties")
    val apikeyProperties =Properties()
    apikeyProperties.load(FileInputStream(apikeyPropertiesFile))
    return apikeyProperties.getProperty("MOVIE_API_KEY")
}