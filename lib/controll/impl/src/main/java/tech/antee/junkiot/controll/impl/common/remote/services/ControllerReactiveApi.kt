package tech.antee.junkiot.controll.impl.common.remote.services

import com.tinder.scarlet.ws.Receive
import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.impl.common.remote.dto.ControllerDto

// TODO: https://github.com/Tinder/Scarlet/issues/114
interface ControllerReactiveApi {

    @Receive
    fun observeControllers(): Flow<List<ControllerDto>>
}
