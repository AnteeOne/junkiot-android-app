package tech.antee.junkiot.controll.impl.common.mappers

import tech.antee.junkiot.controll.common.models.parseControllerType
import tech.antee.junkiot.controll.impl.common.local.entities.ControllerEntity
import tech.antee.junkiot.controll.impl.common.remote.dto.ControllerDto

class ControllerSourceMapper {

    fun mapToEntity(dto: ControllerDto): ControllerEntity = with(dto) {
        ControllerEntity(controllerTypeId.parseControllerType(), id, name, isOnline)
    }

    fun mapToEntity(dtos: List<ControllerDto>): List<ControllerEntity> = dtos.map(::mapToEntity)
}
