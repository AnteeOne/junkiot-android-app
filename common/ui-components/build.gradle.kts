plugins {
    id(Plugins.androidLibrary)
    id(Plugins.androidBase)
    id(Plugins.compose)
}

dependencies {
    projectImplementation(Modules.coreStrings)
    projectImplementation(Modules.coreStyles)
    projectImplementation(Modules.libControllApi)

    implementation(Deps.appCompat)
    implementation(Deps.coreKtx)
    implementation(Deps.material)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.material3)
    implementation(Deps.Compose.toolsPreview)
    debugImplementation(Deps.Compose.tools)
}
