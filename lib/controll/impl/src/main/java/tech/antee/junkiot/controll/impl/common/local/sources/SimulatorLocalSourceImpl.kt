package tech.antee.junkiot.controll.impl.common.local.sources

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import tech.antee.junkiot.controll.common.models.ControllerType
import tech.antee.junkiot.controll.impl.common.local.entities.ControllerEntity
import javax.inject.Inject

class SimulatorLocalSourceImpl @Inject constructor() : SimulatorLocalSource {

    private val mockList = listOf(
        ControllerEntity(
            ControllerType.LIGHT_SENSOR,
            1,
            "Bedroom sensor",
            true
        )
    )

    private val _simulators: MutableStateFlow<List<ControllerEntity>> = MutableStateFlow(mockList)
    override val simulators: Flow<List<ControllerEntity>> = _simulators

    override fun update(list: List<ControllerEntity>) {
        _simulators.value = list
    }

    override fun add(entity: ControllerEntity) {
        _simulators.value = buildList {
            addAll(_simulators.value)
            add(entity)
        }
    }

    override fun delete(id: Int) {
        _simulators.value = _simulators.value.filter { simulatorsEntity -> simulatorsEntity.id != id }
    }

    override fun deleteAll() {
        _simulators.value = listOf()
    }
}
