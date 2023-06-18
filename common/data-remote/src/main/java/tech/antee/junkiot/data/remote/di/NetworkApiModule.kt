package tech.antee.junkiot.data.remote.di

import com.tinder.scarlet.Scarlet
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import tech.antee.junkiot.controll.impl.claps_detector.remote.services.ClapsDetectorApi
import tech.antee.junkiot.controll.impl.claps_detector.remote.services.ClapsDetectorReactiveApi
import tech.antee.junkiot.controll.impl.common.remote.services.ControllerApi
import tech.antee.junkiot.controll.impl.common.remote.services.ControllerReactiveApi
import tech.antee.junkiot.controll.impl.light_sensor.remote.services.LightSensorApi
import tech.antee.junkiot.controll.impl.light_sensor.remote.services.LightSensorReactiveApi
import tech.antee.junkiot.controll.impl.noise_detector.remote.services.NoiseDetectorApi
import tech.antee.junkiot.controll.impl.noise_detector.remote.services.NoiseDetectorReactiveApi
import tech.antee.junkiot.data.remote.di.qualifiers.ScarletClapsDetectorPredictionsQualifier
import tech.antee.junkiot.data.remote.di.qualifiers.ScarletControllersQualifier
import tech.antee.junkiot.data.remote.di.qualifiers.ScarletLightSensorPredictionsQualifier
import tech.antee.junkiot.data.remote.di.qualifiers.ScarletNoiseDetectorPredictionsQualifier
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

    @Provides
    @Singleton
    fun lightSensorApi(retrofit: Retrofit): LightSensorApi = retrofit.create(LightSensorApi::class.java)

    @Provides
    @Singleton
    fun lightSensorReactiveApi(@ScarletLightSensorPredictionsQualifier scarlet: Scarlet): LightSensorReactiveApi =
        scarlet.create(LightSensorReactiveApi::class.java)

    @Provides
    @Singleton
    fun clapsDetectorApi(retrofit: Retrofit): ClapsDetectorApi = retrofit.create(ClapsDetectorApi::class.java)

    @Provides
    @Singleton
    fun clapsDetectorReactiveApi(@ScarletClapsDetectorPredictionsQualifier scarlet: Scarlet): ClapsDetectorReactiveApi =
        scarlet.create(ClapsDetectorReactiveApi::class.java)

    @Provides
    @Singleton
    fun noiseDetectorApi(retrofit: Retrofit): NoiseDetectorApi = retrofit.create(NoiseDetectorApi::class.java)

    @Provides
    @Singleton
    fun noiseDetectorReactiveApi(@ScarletNoiseDetectorPredictionsQualifier scarlet: Scarlet): NoiseDetectorReactiveApi =
        scarlet.create(NoiseDetectorReactiveApi::class.java)
}
