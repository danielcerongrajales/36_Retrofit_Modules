package extension

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Adds required dependencies to app module
 */
fun DependencyHandler.common() {
    implementation(Deps.coreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    testImplementation(Deps.junit)
    androidTestImplementation (Deps.extJunit)
    androidTestImplementation (Deps.espresso)

}
fun DependencyHandler.shimmer() {
    implementation(Deps.shimmer)
}
fun DependencyHandler.lottie() {
    implementation(Deps.lottie)
}
fun DependencyHandler.glide() {
    implementation(Deps.glide)
//    kapt(Deps.glideCompile)
}
fun DependencyHandler.paging() {
    implementation(Deps.pagingCommon)
    implementation(Deps.pagingRuntime)

}
fun DependencyHandler.hilt() {
    implementation(Deps.hilt)
//    kapt(Deps.hiltCompile)


}
fun DependencyHandler.retrofit() {
    implementation(Deps.retrofit)
    implementation(Deps.retrofitGson)


}
fun DependencyHandler.room() {
    implementation(Deps.roomRuntime)
//    kapt(Deps.roomCompile)
    implementation(Deps.roomKtx)


}
fun DependencyHandler.roomPaging() {
    implementation(Deps.roomPaging)
}

fun DependencyHandler.coil() {
    implementation(Deps.coil)
}

fun DependencyHandler.coroutines() {
    implementation(Deps.coroutines)
}
fun DependencyHandler.viewmodel() {
    implementation(Deps.viewmodel)
}
fun DependencyHandler.livedata() {
    implementation(Deps.livedata)
}
fun DependencyHandler.activity() {
    implementation(Deps.activity)
}
fun DependencyHandler.navigationFragment() {
    implementation(Deps.navigationFragment)
}
fun DependencyHandler.navigationUi() {
    implementation(Deps.navigationUi)
}
fun DependencyHandler.constraintlayout() {
    implementation(Deps.constraintlayout)
}





/*
 * These extensions mimic the extensions that are generated on the fly by Gradle.
 * They are used here to provide above dependency syntax that mimics Gradle Kotlin DSL
 * syntax in module\build.gradle.kts files.
 */
private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

private fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)



private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

private fun DependencyHandler.testRuntimeOnly(dependencyNotation: Any): Dependency? =
    add("testRuntimeOnly", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

private fun DependencyHandler.project(
    path: String,
    configuration: String? = null
): ProjectDependency {
    val notation = if (configuration != null) {
        mapOf("path" to path, "configuration" to configuration)
    } else {
        mapOf("path" to path)
    }

    return uncheckedCast(project(notation))
}

@Suppress("unchecked_cast", "nothing_to_inline", "detekt.UnsafeCast")
private inline fun <T> uncheckedCast(obj: Any?): T = obj as T