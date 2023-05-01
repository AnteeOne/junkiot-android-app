package tech.antee.junkiot.simulator.light_sensor.impl.ui.items

sealed interface Event {
    object ShowErrorSnackBar : Event
    object NavBack : Event
}
