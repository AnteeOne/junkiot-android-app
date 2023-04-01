package tech.antee.junkiot.controller.list.impl.ui.items

data class UiState(
    val controllers: List<ControllerItem>,
    val isLoading: Boolean,
    val isError: Boolean
) {

    fun withLoading(isLoading: Boolean) = copy(isLoading = isLoading)

    fun withSuccess(controllers: List<ControllerItem>) = copy(
        controllers = controllers,
        isLoading = false,
        isError = false
    )

    fun withError() = copy(isError = true)

    companion object {

        fun empty(): UiState = UiState(
            controllers = listOf(),
            isLoading = false,
            isError = false
        )
    }
}
