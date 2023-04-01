package tech.antee.junkiot.data.remote.di

import com.tinder.scarlet.Scarlet
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import tech.antee.junkiot.controll.impl.common.network.service.ControllerApi
import tech.antee.junkiot.controll.impl.common.network.service.ControllerReactiveApi
import tech.antee.junkiot.data.remote.di.qualifiers.ScarletControllersQualifier
import javax.inject.Singleton

@Module(
    includes = [RetrofitModule::class, ScarletModule::class]
)
class NetworkApiModule {

    @Provides
    @Singleton
    fun controllerApi(retrofit: Retrofit): ControllerApi = retrofit.create(ControllerApi::class.java)

    @Provides
    @Singleton
    fun controllersReactiveApi(@ScarletControllersQualifier scarlet: Scarlet): ControllerReactiveApi =
        scarlet.create(ControllerReactiveApi::class.java)
}
