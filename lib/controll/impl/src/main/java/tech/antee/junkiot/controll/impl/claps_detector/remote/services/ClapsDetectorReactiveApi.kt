package tech.antee.junkiot.controll.impl.claps_detector.remote.services

import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.impl.claps_detector.remote.dto.ClapDetectionValueDto

interface ClapsDetectorReactiveApi {

    @Receive
    fun observeClapsDetections(): Flow<List<ClapDetectionValueDto>>

    @Send
    fun sendControllerId(controllerId: Int): Boolean
}
