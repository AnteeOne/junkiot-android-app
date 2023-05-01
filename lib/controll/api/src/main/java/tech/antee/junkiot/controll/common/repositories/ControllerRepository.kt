package tech.antee.junkiot.controll.common.repositories

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.common.models.AddController
import tech.antee.junkiot.controll.common.models.Controller

interface ControllerRepository {

    val controllers: Flow<List<Controller>>

    val simulators: Flow<List<Controller>>

    suspend fun addController(add: AddController): Controller

    suspend fun deleteController(id: Int)
}
