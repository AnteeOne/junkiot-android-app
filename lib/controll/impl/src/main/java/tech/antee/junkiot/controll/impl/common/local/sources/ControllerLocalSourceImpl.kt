package tech.antee.junkiot.controll.impl.common.local.sources

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import tech.antee.junkiot.controll.impl.common.local.entities.ControllerEntity

class ControllerLocalSourceImpl : ControllerLocalSource {

    private val _controllers: MutableStateFlow<List<ControllerEntity>> = MutableStateFlow(listOf())
    override val controllers: StateFlow<List<ControllerEntity>> = _controllers

    override fun update(list: List<ControllerEntity>) {
        _controllers.value = list
    }

    override fun delete(id: Int) {
        _controllers.value = controllers.value.filter { controllerEntity -> controllerEntity.id != id }
    }

    override fun deleteAll() {
        _controllers.value = listOf()
    }
}
