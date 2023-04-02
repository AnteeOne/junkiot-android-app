package tech.antee.junkiot.simulator.list.impl.di

import dagger.Component
import tech.antee.junkiot.di.scopes.FeatureScope
import tech.antee.junkiot.simulator.list.impl.ui.SimulatorListViewModel

@FeatureScope
@Component(
    modules = [SimulatorListModule::class],
    dependencies = [SimulatorListDependencies::class]
)
interface SimulatorListComponent {

    val viewModel: SimulatorListViewModel

    @Component.Factory
    interface Factory {
        fun create(deps: SimulatorListDependencies): SimulatorListComponent
    }
}
