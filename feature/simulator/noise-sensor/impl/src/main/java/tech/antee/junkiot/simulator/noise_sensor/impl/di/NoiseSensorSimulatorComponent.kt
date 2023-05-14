package tech.antee.junkiot.simulator.noise_sensor.impl.di

import dagger.BindsInstance
import dagger.Component
import tech.antee.junkiot.di.qualifiers.ControllerId
import tech.antee.junkiot.di.scopes.FeatureScope
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.NoiseSensorSimulatorViewModel

@FeatureScope
@Component(
    modules = [NoiseSensorSimulatorModule::class],
    dependencies = [NoiseSensorSimulatorDependencies::class]
)
interface NoiseSensorSimulatorComponent {

    val viewModel: NoiseSensorSimulatorViewModel

    @Component.Factory
    interface Factory {
        fun create(
            deps: NoiseSensorSimulatorDependencies,
            @ControllerId
            @BindsInstance
            controllerId: Int
        ): NoiseSensorSimulatorComponent
    }
}
