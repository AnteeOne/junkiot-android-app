package tech.antee.junkiot.simulator.list.impl.ui.items

sealed interface Action {

    data class OnItemClick(val id: String) : Action
}
