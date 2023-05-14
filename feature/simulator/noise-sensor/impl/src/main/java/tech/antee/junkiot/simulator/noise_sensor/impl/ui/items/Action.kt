package tech.antee.junkiot.simulator.noise_sensor.impl.ui.items

sealed interface Action {
    object OnStartBtnClick : Action
    object OnBackBtnClick : Action
}
