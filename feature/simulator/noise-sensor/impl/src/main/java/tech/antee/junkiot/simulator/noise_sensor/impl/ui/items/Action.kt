package tech.antee.junkiot.simulator.noise_sensor.impl.ui.items

sealed interface Action {
    object OnDetectBtnClick : Action
    object OnBackBtnClick : Action
}
