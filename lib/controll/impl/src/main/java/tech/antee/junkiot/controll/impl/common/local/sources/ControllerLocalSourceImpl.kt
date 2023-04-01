package tech.antee.junkiot.controll.impl.common.local.sources

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import tech.antee.junkiot.controll.impl.common.local.entities.ControllerEntity
import javax.inject.Inject

class ControllerLocalSourceImpl @Inject constructor() : ControllerLocalSource {

    private val _controllers: MutableStateFlow<List<ControllerEntity>> = MutableStateFlow(listOf())
    override val controllers: Flow<List<ControllerEntity>> = _controllers

    override fun update(list: List<ControllerEntity>) {
        _controllers.value = list
    }

    override fun delete(id: Int) {
        _controllers.value = _controllers.value.filter { controllerEntity -> controllerEntity.id != id }
    }

    override fun deleteAll() {
        _controllers.value = listOf()
    }
}
