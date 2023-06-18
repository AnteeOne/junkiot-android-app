package tech.antee.junkiot.simulator.claps_detector.impl.ui.items

sealed interface Action {
    object OnDetectBtnClick : Action
    object OnBackBtnClick : Action
}
