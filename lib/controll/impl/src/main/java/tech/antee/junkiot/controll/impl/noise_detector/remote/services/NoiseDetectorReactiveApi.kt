package tech.antee.junkiot.controll.impl.noise_detector.remote.services

import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.impl.noise_detector.remote.dto.NoiseDetectionValueDto

interface NoiseDetectorReactiveApi {

    @Receive
    fun observeNoiseDetections(): Flow<List<NoiseDetectionValueDto>>

    @Send
    fun sendControllerId(controllerId: Int): Boolean
}
