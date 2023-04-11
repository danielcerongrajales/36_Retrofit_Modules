import org.gradle.api.artifacts.dsl.DependencyHandler

///**
// * To define plugins
// */
//object BuildPlugins {
//    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
//    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
//}

/**
 * To define dependencies
 */
object Deps {
    const val coreKtx="androidx.core:core-ktx:${Versions.coreKtx}"
    const val appCompat="androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material="com.google.android.material:material:${Versions.material}"
    const val junit="junit:junit:${Versions.junit}"
    const val extJunit="androidx.test.ext:junit:${Versions.extJunit}"
    const val espresso="androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val splashscreen = "androidx.core:core-splashscreen:${Versions.splashscreen}"
    const val shimmer="com.facebook.shimmer:shimmer:${Versions.shimmer}"
    const val lottie="com.airbnb.android:lottie:${Versions.lottie}"
    const val glide= "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompile="com.github.bumptech.glide:compiler:${Versions.glide}"
    const val pagingCommon="androidx.paging:paging-common-ktx:${Versions.paging}"
    const val pagingRuntime="androidx.paging:paging-runtime:${Versions.paging}"
    const val hilt= "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompile= "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val retrofit="com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson="com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val roomRuntime="androidx.room:room-runtime:${Versions.room}"
    const val roomCompile="androidx.room:room-compiler:${Versions.room}"
    const val roomKtx="androidx.room:room-ktx:${Versions.room}"
    const val roomPaging="androidx.room:room-paging:${Versions.room}"
    const val coil="io.coil-kt:coil:${Versions.coil}"
    const val coroutines="org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val viewmodel="androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel}"
    const val livedata="androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata}"
    const val activity="androidx.activity:activity-ktx:${Versions.activity}"
    const val navigationFragment="androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragment}"
    const val navigationUi="androidx.navigation:navigation-ui-ktx:${Versions.navigationUi}"
    const val constraintlayout="androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"


//

}

