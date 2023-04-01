package tech.antee.junkiot.controller.list.impl.ui.items

sealed interface Action {

    data class OnItemClick(val id: String) : Action
}
