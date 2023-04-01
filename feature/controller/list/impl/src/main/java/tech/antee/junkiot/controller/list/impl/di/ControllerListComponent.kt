package tech.antee.junkiot.controller.list.impl.di

import dagger.Component
import tech.antee.junkiot.controller.list.impl.ui.ControllerListViewModel
import tech.antee.junkiot.di.scopes.FeatureScope

@FeatureScope
@Component(
    modules = [ControllerListModule::class],
    dependencies = [ControllerListDependencies::class]
)
interface ControllerListComponent {

    val viewModel: ControllerListViewModel

    @Component.Factory
    interface Factory {
        fun create(deps: ControllerListDependencies): ControllerListComponent
    }
}
