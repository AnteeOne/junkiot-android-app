package tech.antee.junkiot.simulator.claps_detector.impl.ui.items

import tech.antee.junkiot.controll.claps_detector.models.ClapDetection

data class UiState(
    val simulator: SimulatorItem?,
    val detectorState: DetectorUiState,
    val clapped: Boolean,
    val clapDetections: List<ClapDetection>?,
    val isLoading: Boolean,
    val isError: Boolean
) {

    fun withLoading(isLoading: Boolean) = copy(isLoading = isLoading)

    fun withSimulatorItem(simulatorItem: SimulatorItem) = copy(
        simulator = simulatorItem,
        isLoading = false,
        isError = false
    )

    fun withDetectorState(detectorState: DetectorUiState) = copy(
        detectorState = detectorState
    )

    fun withClapState(clapped: Boolean) = copy(
        clapped = clapped
    )

    fun withClapDetections(clapDetections: List<ClapDetection>) = copy(
        clapDetections = clapDetections
    )

    fun withError() = copy(isError = true)

    companion object {

        fun empty(): UiState = UiState(
            simulator = null,
            detectorState = DetectorUiState.Empty,
            clapped = false,
            clapDetections = null,
            isLoading = false,
            isError = false
        )
    }
}
