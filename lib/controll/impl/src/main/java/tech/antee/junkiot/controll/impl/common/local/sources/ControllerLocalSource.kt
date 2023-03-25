package tech.antee.junkiot.controll.impl.common.local.sources

import kotlinx.coroutines.flow.StateFlow
import tech.antee.junkiot.controll.impl.common.local.entities.ControllerEntity

interface ControllerLocalSource {

    val controllers: StateFlow<List<ControllerEntity>>

    fun update(list: List<ControllerEntity>)

    fun delete(id: Int)

    fun deleteAll()
}
