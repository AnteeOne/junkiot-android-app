package tech.antee.junkiot.simulator.noise_sensor.impl.ui.items

sealed interface Event {
    object ShowErrorSnackBar : Event
    object NavBack : Event
}
