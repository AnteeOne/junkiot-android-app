package tech.antee.junkiot.simulator.list.impl.ui.items

data class UiState(
    val simulators: List<SimulatorItem>,
    val isLoading: Boolean,
    val isError: Boolean
) {

    fun withLoading(isLoading: Boolean) = copy(isLoading = isLoading)

    fun withSuccess(simulators: List<SimulatorItem>) = copy(
        simulators = simulators,
        isLoading = false,
        isError = false
    )

    fun withError() = copy(isError = true)

    companion object {

        fun empty(): UiState = UiState(
            simulators = listOf(),
            isLoading = false,
            isError = false
        )
    }
}
