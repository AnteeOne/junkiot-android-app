package tech.antee.junkiot.controller.list.impl.ui.items

sealed interface Event {
    data class OnNavToDetails(val id: String) : Event
}
