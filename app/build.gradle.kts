import extension.*

plugins {
    id (Plugins.ANDROID_APPLICATION_PLUGIN)
    id (Plugins.JETBRAINS_KOTLIN)
    id (Plugins.KOTLIN_KAPT_PLUGIN)
    id(Plugins.DAGGER_HILT_PLUGIN)
}

android {
    compileSdk = 32


    defaultConfig {
        applicationId ="com.example.a36_retrofit_modules"
        minSdk =21
        targetSdk= 32
        versionCode= 1
        versionName ="1.0"

        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        create("benchmark") {
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }
    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding= true
    }
}

dependencies {
    implementation( project(":domain"))
    implementation (project(":data"))

    //splashscreen
    implementation(Deps.splashscreen)
    //shimmer
    shimmer()
    //lottie
    lottie()
    //Glide
    glide()
    //coil
   coil()
//    implementation 'com.github.jakob-grabner:Circle-Progress-View:1.4'



    //paging 3
    paging()
    //dagger hilt
    hilt()

    //Corrutinas
    coroutines()
    // ViewModelScope
    viewmodel()
    // LiveData
    livedata()
    // Activity
    activity()
    // navigationFragment
    navigationFragment()
    //navigationUi
    navigationUi()
    //constraintlayout
    constraintlayout()



    //common
    common()

}
