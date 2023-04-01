plugins {
    id(Plugins.androidLibrary)
    id(Plugins.androidBase)
}

dependencies {
    implementation(Deps.Coroutines.kotlinCore)
    implementation(Deps.inject)
}
