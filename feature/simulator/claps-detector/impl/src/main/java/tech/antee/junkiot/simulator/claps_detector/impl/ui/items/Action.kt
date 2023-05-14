package tech.antee.junkiot.simulator.claps_detector.impl.ui.items

sealed interface Action {
    object OnStartBtnClick : Action
    object OnBackBtnClick : Action
}
