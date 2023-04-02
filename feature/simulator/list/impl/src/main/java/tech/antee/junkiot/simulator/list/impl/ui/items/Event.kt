package tech.antee.junkiot.simulator.list.impl.ui.items

sealed interface Event {
    data class OnNavToDetails(val id: String) : Event
}
