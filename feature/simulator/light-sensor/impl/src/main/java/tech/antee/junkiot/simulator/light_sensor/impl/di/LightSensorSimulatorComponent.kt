package tech.antee.junkiot.simulator.light_sensor.impl.di

import dagger.BindsInstance
import dagger.Component
import tech.antee.junkiot.di.qualifiers.ControllerId
import tech.antee.junkiot.di.scopes.FeatureScope
import tech.antee.junkiot.simulator.light_sensor.impl.ui.LightSensorSimulatorViewModel

@FeatureScope
@Component(
    modules = [LightSensorSimulatorModule::class],
    dependencies = [LightSensorSimulatorDependencies::class]
)
interface LightSensorSimulatorComponent {

    val viewModel: LightSensorSimulatorViewModel

    @Component.Factory
    interface Factory {
        fun create(
            deps: LightSensorSimulatorDependencies,
            @ControllerId
            @BindsInstance
            controllerId: Int
        ): LightSensorSimulatorComponent
    }
}
