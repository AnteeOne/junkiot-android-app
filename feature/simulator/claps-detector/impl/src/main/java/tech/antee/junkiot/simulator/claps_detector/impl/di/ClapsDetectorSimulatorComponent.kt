package tech.antee.junkiot.simulator.claps_detector.impl.di

import dagger.BindsInstance
import dagger.Component
import tech.antee.junkiot.di.qualifiers.ControllerId
import tech.antee.junkiot.di.scopes.FeatureScope
import tech.antee.junkiot.simulator.claps_detector.impl.ui.ClapsDetectorSimulatorViewModel

@FeatureScope
@Component(
    modules = [ClapsDetectorSimulatorModule::class],
    dependencies = [ClapsDetectorSimulatorDependencies::class]
)
interface ClapsDetectorSimulatorComponent {

    val viewModel: ClapsDetectorSimulatorViewModel

    @Component.Factory
    interface Factory {
        fun create(
            deps: ClapsDetectorSimulatorDependencies,
            @ControllerId
            @BindsInstance
            controllerId: Int
        ): ClapsDetectorSimulatorComponent
    }
}
