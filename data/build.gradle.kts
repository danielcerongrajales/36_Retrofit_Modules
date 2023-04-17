import java.util.*
plugins {
    id("convention.library.convention")
    id("kotlin-parcelize")
    id("convention.hilt.convention")
    id("convention.pagging.convention")
    id("convention.room.convention")
}

android {


    buildTypes {
        getByName("release") {
            isMinifyEnabled =false
            proguardFiles (
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

}

dependencies {
    implementation (project(":domain"))

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofitGson)



}
