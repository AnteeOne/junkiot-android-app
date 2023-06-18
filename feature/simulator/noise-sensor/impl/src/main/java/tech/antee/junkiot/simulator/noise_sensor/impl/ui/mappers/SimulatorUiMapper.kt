package tech.antee.junkiot.simulator.noise_sensor.impl.ui.mappers

import tech.antee.junkiot.controll.common.models.Controller
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.items.DetectorUiState
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.items.NoiseDetectionUiState
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.items.SimulatorItem
import tech.antee.junkiot.tensorflow_bridge.audio.services.noise.NoiseDetectionState
import tech.antee.junkiot.tensorflow_bridge.audio.services.sound_detection.DetectorState
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

    fun map(detectorState: DetectorState): DetectorUiState = when (detectorState) {
        DetectorState.Empty -> DetectorUiState.Empty
        DetectorState.Disabled -> DetectorUiState.Disabled
        DetectorState.Enabled -> DetectorUiState.Enabled
    }

    fun map(noiseDetectionState: NoiseDetectionState): NoiseDetectionUiState = when (noiseDetectionState) {
        is NoiseDetectionState.Empty -> NoiseDetectionUiState.Empty
        is NoiseDetectionState.Noise -> NoiseDetectionUiState.Noise(noiseDetectionState.label)
    }
}
