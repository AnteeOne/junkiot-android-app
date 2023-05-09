plugins {
    id(Plugins.androidLibrary)
    id(Plugins.androidBase)
    id(Plugins.kotlinKapt)
}

dependencies {
    implementation(Deps.appCompat)
    implementation(Deps.coreKtx)
    implementation(Deps.Coroutines.kotlinCore)
    implementation(Deps.inject)
    implementation(Deps.ML.tensorFlowAudio)

    projectApi(Modules.libTensorFlowBridgeApi)
}
