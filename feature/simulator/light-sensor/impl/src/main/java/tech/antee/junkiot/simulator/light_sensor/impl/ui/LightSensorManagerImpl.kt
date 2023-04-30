package tech.antee.junkiot.simulator.light_sensor.impl.ui

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import tech.antee.junkiot.simulator.light_sensor.managers.LightSensorManager
import tech.antee.junkiot.simulator.light_sensor.models.LightSensorManagerState
import tech.antee.junkiot.simulator.light_sensor.models.LightSensorState
import javax.inject.Inject

@FlowPreview
class LightSensorManagerImpl @Inject constructor(
    private val sensorManager: SensorManager
) : LightSensorManager {

    private val lightSensor: Sensor by lazy {
        sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    private val _lightSensorManagerState: MutableStateFlow<LightSensorManagerState> =
        MutableStateFlow(LightSensorManagerState.Disabled)
    override val lightSensorManagerState: Flow<LightSensorManagerState> = _lightSensorManagerState

    private val _lightSensorValues = callbackFlow {
        val listener = object : SensorEventListener {

            override fun onSensorChanged(event: SensorEvent?) {
                if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
                    trySendBlocking(LightSensorState.Value(event.values[0]))
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, value: Int) {}
        }

        trySendBlocking(LightSensorState.Empty)
        sensorManager.registerListener(listener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
        _lightSensorManagerState.value = LightSensorManagerState.Enabled

        awaitClose {
            sensorManager.unregisterListener(listener)
            _lightSensorManagerState.value = LightSensorManagerState.Disabled
            trySendBlocking(LightSensorState.Empty)
        }
    }
        .buffer(capacity = Channel.UNLIMITED)
        .debounce(LightSensorManagerSettings.SENSOR_VALUES_DELAY)
    override val lightSensorValues: Flow<LightSensorState> = _lightSensorValues

    private object LightSensorManagerSettings {
        const val SENSOR_VALUES_DELAY = 300L
    }
}
