package tech.antee.junkiot.simulator.noise_sensor.impl.ui.items

import tech.antee.junkiot.controll.noise_detector.models.NoiseDetection

data class UiState(
    val simulator: SimulatorItem?,
    val detectorState: DetectorUiState,
    val noiseDetectionUiState: NoiseDetectionUiState,
    val noiseDetections: List<NoiseDetection>?,
    val isLoading: Boolean,
    val isError: Boolean
) {

    fun withLoading(isLoading: Boolean) = copy(isLoading = isLoading)

    fun withSimulatorItem(simulatorItem: SimulatorItem) = copy(
        simulator = simulatorItem,
        isLoading = false,
        isError = false
    )

    fun withNoiseDetectionState(noiseDetectionUiState: NoiseDetectionUiState) = copy(
        noiseDetectionUiState = noiseDetectionUiState
    )

    fun withDetectorState(detectorState: DetectorUiState) = copy(
        detectorState = detectorState
    )

    fun withNoiseDetections(noiseDetections: List<NoiseDetection>) = copy(
        noiseDetections = noiseDetections
    )

    fun withError() = copy(isError = true)

    companion object {

        fun empty(): UiState = UiState(
            simulator = null,
            detectorState = DetectorUiState.Empty,
            noiseDetectionUiState = NoiseDetectionUiState.Empty,
            noiseDetections = null,
            isLoading = false,
            isError = false
        )
    }
}
