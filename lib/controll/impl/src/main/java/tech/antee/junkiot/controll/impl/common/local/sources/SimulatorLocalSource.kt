package tech.antee.junkiot.controll.impl.common.local.sources

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.impl.common.local.entities.ControllerEntity

interface SimulatorLocalSource {

    val simulators: Flow<List<ControllerEntity>>

    fun update(list: List<ControllerEntity>)

    fun add(entity: ControllerEntity)

    fun delete(id: Int)

    fun deleteAll()
}
