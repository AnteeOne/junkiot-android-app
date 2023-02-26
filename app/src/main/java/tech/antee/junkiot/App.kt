package tech.antee.junkiot

import android.app.Application
import tech.antee.junkiot.di.AppProvider
import tech.antee.junkiot.di.DaggerAppComponent

class App : Application() {

    lateinit var appProvider: AppProvider

    override fun onCreate() {
        super.onCreate()
        appProvider = DaggerAppComponent.factory().create(this).apply {
            inject(this@App)
        }
    }
}

val Application.appProvider: AppProvider
    get() = (this as App).appProvider
