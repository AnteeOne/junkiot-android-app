plugins {
    id(Plugins.androidLibrary)
    id(Plugins.androidBase)
    id(Plugins.compose)
    id(Plugins.kotlinKapt)
}

dependencies {
    projectImplementation(Modules.coreStrings)
    projectImplementation(Modules.coreStyles)
    projectImplementation(Modules.commonDomain)
    projectImplementation(Modules.commonDi)
    projectImplementation(Modules.commonUi)
    projectImplementation(Modules.commonUiComponents)
    projectImplementation(Modules.commonMultiCompose)

    projectApi(Modules.featureSimulatorListApi)
    projectImplementation(Modules.libControllApi)
    projectImplementation(Modules.featureSimulatorLightSensorApi)
    projectImplementation(Modules.featureSimulatorClapsDetectorApi)

    implementation(Deps.appCompat)
    implementation(Deps.coreKtx)
    implementation(Deps.material)
    implementation(Deps.Coroutines.viewModel)
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.material3)
    implementation(Deps.Compose.navigation)
    implementation(Deps.Compose.toolsPreview)
    implementation(Deps.Compose.tools)

    implementation(Deps.Dagger.core)
    kapt(Deps.Dagger.compiler)
}
