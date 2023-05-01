package tech.antee.junkiot.controll.impl.light_sensor.remote.services

import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.impl.light_sensor.remote.dto.LightPredictionDto

interface LightSensorReactiveApi {

    @Receive
    fun observeLightSensorPredictions(): Flow<List<LightPredictionDto>>

    @Send
    fun sendControllerId(controllerId: Int): Boolean
}
