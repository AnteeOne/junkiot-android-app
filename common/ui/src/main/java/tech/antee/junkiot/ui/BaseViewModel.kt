package tech.antee.junkiot.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import tech.antee.junkiot.utils.coroutines.ktx.launchSecurely
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<State, Event, Action> : ViewModel() {

    protected abstract val _uiState: MutableStateFlow<State>
    val uiState: StateFlow<State> get() = _uiState

    private val _uiEvents = Channel<Event>(capacity = Channel.UNLIMITED)
    val uiEvents: Flow<Event> = _uiEvents.receiveAsFlow()

    abstract fun onAction(action: Action)

    protected open fun onLoading(inProgress: Boolean) {}

    protected open fun onError(t: Throwable) {
        Log.e(javaClass.simpleName, "onError: ${t.message}", t)
    }

    protected fun launchSafely(
        scope: CoroutineScope = viewModelScope,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        onLoading: (Boolean) -> Unit = ::onLoading,
        onError: (Throwable) -> Unit = ::onError,
        onFinally: () -> Unit = {},
        block: suspend CoroutineScope.() -> Unit
    ): Job = launchSecurely(
        scope = scope,
        context = context,
        start = start,
        onLoading = onLoading,
        onError = onError,
        onFinally = onFinally,
        block = block
    )

    protected inline fun <reified T : State> updateIfStateIs(
        afterUpdate: (currentState: T) -> Unit = {},
        newState: (currentState: T) -> T
    ) {
        updateState(
            afterUpdate = { state ->
                if (state is T) afterUpdate(state)
            }
        ) { currentState ->
            if (currentState is T) {
                newState(currentState)
            } else {
                Log.w(
                    javaClass.simpleName,
                    "updateState: current state = $currentState, expected = ${T::class.qualifiedName}"
                )
                currentState
            }
        }
    }

    protected inline fun <reified Old : State, reified New : State> replaceIfStateIs(
        crossinline afterUpdate: (currentState: New) -> Unit = {},
        newState: (currentState: Old) -> New
    ) {
        updateState(
            afterUpdate = { state ->
                if (state is New) afterUpdate(state)
            }
        ) { currentState ->
            if (currentState is Old) {
                newState(currentState)
            } else {
                Log.w(
                    javaClass.simpleName,
                    "replaceState: current state = $currentState, expected = ${Old::class.qualifiedName}"
                )
                currentState
            }
        }
    }

    protected inline fun updateState(afterUpdate: (State) -> Unit = {}, function: (State) -> State) {
        _uiState.update(function)
        afterUpdate(_uiState.value)
    }

    protected fun emitEvent(event: Event): ChannelResult<Unit> = _uiEvents.trySend(event)
}
