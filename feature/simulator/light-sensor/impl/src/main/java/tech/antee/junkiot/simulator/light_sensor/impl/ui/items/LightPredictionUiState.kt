package tech.antee.junkiot.simulator.light_sensor.impl.ui.items

import tech.antee.junkiot.controll.light_sensor.models.LightSensorPredictionValue

sealed interface LightPredictionUiState {
    object Empty : LightPredictionUiState
    data class Value(val lightPrediction: LightSensorPredictionValue) : LightPredictionUiState
}
