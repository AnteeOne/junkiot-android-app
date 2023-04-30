package tech.antee.junkiot.simulator.light_sensor.impl.ui.mappers

import tech.antee.junkiot.controll.common.models.Controller
import tech.antee.junkiot.controll.light_sensor.models.LightSensorPredictionValue
import tech.antee.junkiot.simulator.light_sensor.impl.ui.items.LightPredictionUiState
import tech.antee.junkiot.simulator.light_sensor.impl.ui.items.LightSensorUiState
import tech.antee.junkiot.simulator.light_sensor.impl.ui.items.SimulatorItem
import tech.antee.junkiot.simulator.light_sensor.models.LightSensorState
import javax.inject.Inject

class SimulatorUiMapper @Inject constructor() {

    fun map(controller: Controller): SimulatorItem = with(controller) {
        SimulatorItem(
            controllerType = controllerType,
            id = id,
            name = name,
            isOnline = isOnline
        )
    }

    fun map(model: LightSensorState): LightSensorUiState = when (model) {
        is LightSensorState.Empty -> LightSensorUiState.Empty
        is LightSensorState.Value -> LightSensorUiState.Value(model.lux)
    }

    fun map(model: LightSensorPredictionValue?): LightPredictionUiState = when (model) {
        null -> LightPredictionUiState.Empty
        else -> LightPredictionUiState.Value(model)
    }
}
