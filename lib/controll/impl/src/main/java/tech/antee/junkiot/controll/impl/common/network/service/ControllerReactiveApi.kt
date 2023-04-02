package tech.antee.junkiot.controll.impl.common.network.service

import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.impl.common.network.dto.ControllerDto

// TODO: https://github.com/Tinder/Scarlet/issues/114
interface ControllerReactiveApi {

    @Receive
    fun observeControllers(): Flow<List<ControllerDto>>

    @Receive
    fun observeEvent(): Flow<WebSocket.Event>

    @Send
    fun sendText(message: String): Boolean
}