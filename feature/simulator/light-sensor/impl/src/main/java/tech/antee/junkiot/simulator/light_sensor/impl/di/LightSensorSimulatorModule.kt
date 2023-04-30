package tech.antee.junkiot.simulator.light_sensor.impl.di

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.SensorManager
import dagger.Module
import dagger.Provides
import tech.antee.junkiot.di.qualifiers.ApplicationContext
import tech.antee.junkiot.di.scopes.FeatureScope
import tech.antee.junkiot.simulator.light_sensor.impl.ui.LightSensorManagerImpl
import tech.antee.junkiot.simulator.light_sensor.managers.LightSensorManager

@Module(
    includes = [MappersModule::class]
)
class LightSensorSimulatorModule {

    @Provides
    @FeatureScope
    fun sensorManager(@ApplicationContext context: Context): SensorManager =
        context.getSystemService(SENSOR_SERVICE) as SensorManager

    @Provides
    @FeatureScope
    fun lightSensorManager(sensorManager: SensorManager): LightSensorManager = LightSensorManagerImpl(sensorManager)
}
