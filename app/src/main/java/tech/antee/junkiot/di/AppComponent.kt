package tech.antee.junkiot.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import tech.antee.junkiot.App
import tech.antee.junkiot.di.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, FeaturesModule::class])
interface AppComponent : AppProvider {

    fun inject(app: App)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            @ApplicationContext
            context: Context
        ): AppComponent
    }
}
