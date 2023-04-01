plugins {
    id(Plugins.androidLibrary)
    id(Plugins.androidBase)
    id(Plugins.kotlinKapt)
}

dependencies {
    projectImplementation(Modules.coreUtils)
    projectApi(Modules.commonDomain)
    projectImplementation(Modules.commonDi)

    projectImplementation(Modules.libControllImpl)

    implementation(Deps.appCompat)
    implementation(Deps.coreKtx)
    implementation(Deps.Coroutines.kotlinCore)

    implementation(Deps.Network.retrofit)
    implementation(Deps.Network.gsonConverter)
    implementation(Deps.Network.okHttpLogging)
    implementation(Deps.Network.scarlet)
    implementation(Deps.Network.scarletOkHttp)
    implementation(Deps.Network.scarletGson)

    implementation(Deps.Dagger.core)
    kapt(Deps.Dagger.compiler)
}
