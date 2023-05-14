package tech.antee.junkiot.simulator.claps_detector.impl.ui.items

data class UiState(
    val simulator: SimulatorItem?,
    val isLoading: Boolean,
    val isError: Boolean
) {

    fun withLoading(isLoading: Boolean) = copy(isLoading = isLoading)

    fun withSimulatorItem(simulatorItem: SimulatorItem) = copy(
        simulator = simulatorItem,
        isLoading = false,
        isError = false
    )

    fun withError() = copy(isError = true)

    companion object {

        fun empty(): UiState = UiState(
            simulator = null,
            isLoading = false,
            isError = false
        )
    }
}
