import extension.*
import java.util.*
import java.io.FileInputStream
plugins {

    id("com.android.library")
    id("com.google.devtools.ksp")
//    id(Plugins.PARCELIZE)
    id("dagger.hilt.android.plugin")



}

android {
    compileSdk =34

    defaultConfig {
        minSdk =21
        targetSdk= 34

        testInstrumentationRunner =("androidx.test.runner.AndroidJUnitRunner")
        consumerProguardFiles ("consumer-rules.pro")
        buildConfigField ("String", "MOVIE_API_KEY", "\"" + getApiKey() + "\"")

    }
    buildFeatures {
        buildConfig = true
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
        sourceCompatibility= JavaVersion.VERSION_17
        targetCompatibility =JavaVersion.VERSION_17
    }
//    kotlinOptions {
//        jvmTarget = "17"
//    }
//    kotlin {
//        jvmToolchain(17)
//    }
    namespace = "com.example.data"

}

dependencies {
    implementation (project(":domain"))
    //paging 3
   implementation("androidx.paging:paging-common-ktx:3.2.1")
   implementation("androidx.paging:paging-runtime:3.2.1")
    //ROOM
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.room:room-paging:2.5.2")
    // Retrofit
   implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //dagger hilt
    implementation("com.google.dagger:hilt-android:2.48")
    ksp ("com.google.dagger:hilt-android-compiler:2.48")

    //common
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("junit:junit:4.13.2")
    implementation("androidx.test.ext:junit:1.1.5")
    implementation("androidx.test.espresso:espresso-core:3.5.1")

}

fun getApiKey():Any? {
    val apikeyPropertiesFile = File("keystore.properties")
    val apikeyProperties =Properties()
    apikeyProperties.load(FileInputStream(apikeyPropertiesFile))
    return apikeyProperties.getProperty("MOVIE_API_KEY")
}