plugins {
    id("convention.application.convention")
    id("convention.hilt.convention")
    id("convention.pagging.convention")

}

android {


    defaultConfig {
        applicationId ="com.example.a36_retrofit_modules"
        targetSdk= 32
        versionCode= 1
        versionName ="1.0"


    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
  
    buildFeatures{
        viewBinding= true
    }
}

dependencies {
    implementation( project(":domain"))
    implementation (project(":data"))

    //splashscreen
    implementation(libs.splashscreen)
    //shimmer
    implementation(libs.shimmer)
    //lottie
    implementation(libs.lottie)
    //Glide
    implementation(libs.glide)
    kapt(libs.glideCompile)
    //coil
    implementation(libs.coil)

//    implementation 'com.github.jakob-grabner:Circle-Progress-View:1.4'


    //Corrutinas
    implementation(libs.coroutines)
    // ViewModelScope
    implementation(libs.viewmodel)
    // LiveData
    implementation(libs.livedata)
    // Activity
    implementation(libs.activity)
    // navigationFragment
    implementation(libs.navigationFragment)
    //navigationUi
    implementation(libs.navigationUi)
    //constraintlayout
    implementation(libs.constraintlayout)
   




}
