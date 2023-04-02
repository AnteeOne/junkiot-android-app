plugins {
    id(Plugins.androidApplication)
    id(Plugins.androidBase)
    id(Plugins.compose)
    id(Plugins.kotlinKapt)
}

android {
    defaultConfig {
        applicationId = AppConfig.applicationId
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
    }
}

dependencies {
    projectImplementation(Modules.coreStyles)
    projectImplementation(Modules.coreStrings)
    projectImplementation(Modules.coreUtils)

    projectImplementation(Modules.commonDomain)
    projectImplementation(Modules.commonData)
    projectImplementation(Modules.commonDataLocal)
    projectImplementation(Modules.commonDataRemote)
    projectImplementation(Modules.commonDi)
    projectImplementation(Modules.commonUi)
    projectImplementation(Modules.commonMultiCompose)

    projectImplementation(Modules.libControllImpl)

    projectImplementation(Modules.featureMainImpl)
    projectImplementation(Modules.featureControllerListImpl)
    projectImplementation(Modules.featureSimulatorListImpl)

    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.material3)
    implementation(Deps.Compose.activity)
    implementation(Deps.Compose.toolsPreview)
    debugImplementation(Deps.Compose.tools)

    implementation(Deps.appCompat)
    implementation(Deps.coreKtx)
    implementation(Deps.material)
    implementation(Deps.constraintLayout)
    implementation(Deps.Compose.navigation)
    implementation(Deps.Accompanist.systemUiController)

    implementation(Deps.Dagger.core)
    kapt(Deps.Dagger.compiler)

    implementation(Deps.Network.retrofit)
    implementation(Deps.Network.okHttpLogging)
    implementation(Deps.Network.scarlet)
    implementation(Deps.Network.scarletOkHttp)
    implementation(Deps.Network.scarletGson)

    testImplementation(Deps.Test.androidJUnit)
    androidTestImplementation(Deps.Test.espresso)
    androidTestImplementation(Deps.Test.jUnit)
}
