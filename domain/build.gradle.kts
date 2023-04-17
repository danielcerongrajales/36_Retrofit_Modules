plugins {
    id("convention.library.convention")
    id("kotlin-parcelize")
    id("convention.hilt.convention")
    id("convention.pagging.convention")
}

android {

    buildTypes {
        getByName("release") {
            isMinifyEnabled =false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

}

