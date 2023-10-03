

plugins {
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 34


    defaultConfig {
        applicationId ="com.example.a36_retrofit_modules"
        minSdk =21
        targetSdk= 34
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
    }
    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_17
        targetCompatibility =JavaVersion.VERSION_17
    }
//    kotlinOptions {
//        jvmTarget = "17"
//    }
//    kotlin {
//        jvmToolchain(17)
//    }
    buildFeatures{
        viewBinding= true
        buildConfig = true
    }
    namespace = "com.example.a36_retrofit_modules"
    //|eliminar esto
//    configurations.all {
//        resolutionStrategy {
//            eachDependency {
//                if ((requested.group == "org.jetbrains.kotlin") && (requested.name.startsWith("kotlin-stdlib"))) {
//                    useVersion("1.8.0")
//                }
//            }
//        }
//    }
}

dependencies {
    implementation( project(":domain"))
    implementation (project(":data"))

    //splashscreen
    implementation("androidx.core:core-splashscreen:1.1.0-alpha02")
    //shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")
    //lottie
    implementation("com.airbnb.android:lottie:6.0.0")
    //Glide
//    glide()
    //coil
    implementation("io.coil-kt:coil:2.2.2")
//    implementation 'com.github.jakob-grabner:Circle-Progress-View:1.4'



    //paging 3
   implementation("androidx.paging:paging-common-ktx:3.2.1")
    implementation("androidx.paging:paging-runtime:3.2.1")
    //dagger hilt
    implementation("com.google.dagger:hilt-android:2.48")
    ksp ("com.google.dagger:hilt-android-compiler:2.48")

    //Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    // ViewModelScope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    // Activity
    implementation("androidx.activity:activity-ktx:1.7.2")
    // navigationFragment
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
    //navigationUi
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3")
    //constraintlayout
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")



    //common
   implementation("androidx.core:core-ktx:1.12.0")
   implementation("androidx.appcompat:appcompat:1.6.1")
   implementation("com.google.android.material:material:1.9.0")
    implementation("junit:junit:4.13.2")
   implementation("androidx.test.ext:junit:1.1.5")
   implementation("androidx.test.espresso:espresso-core:3.5.1")

}
