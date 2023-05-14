package tech.antee.junkiot.simulator.claps_detector.impl.ui.items

sealed interface Event {
    object ShowErrorSnackBar : Event
    object NavBack : Event
}
