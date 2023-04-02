plugins {
    id(Plugins.androidLibrary)
    id(Plugins.androidBase)
    id(Plugins.compose)
    id(Plugins.kotlinKapt)
}

dependencies {
    projectImplementation(Modules.coreStrings)
    projectImplementation(Modules.coreStyles)

    projectImplementation(Modules.commonUiComponents)
    projectImplementation(Modules.commonMultiCompose)
    projectImplementation(Modules.commonDi)

    projectApi(Modules.featureMainApi)

    projectImplementation(Modules.featureControllerListImpl)

    implementation(Deps.appCompat)
    implementation(Deps.coreKtx)
    implementation(Deps.material)
    implementation(Deps.Coroutines.viewModel)
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.material3)
    implementation(Deps.Compose.toolsPreview)
    implementation(Deps.Compose.navigation)
    debugImplementation(Deps.Compose.tools)

    implementation(Deps.Dagger.core)
    kapt(Deps.Dagger.compiler)
}
