package tech.antee.junkiot.controll.impl.common.mappers

import tech.antee.junkiot.controll.common.models.parseControllerType
import tech.antee.junkiot.controll.impl.common.local.entities.ControllerEntity
import tech.antee.junkiot.controll.impl.common.network.dto.ControllerDto

class ControllerSourceMapper {

    fun mapToEntity(dto: ControllerDto): ControllerEntity = with(dto) {
        ControllerEntity(controllerTypeId.parseControllerType(), id, name)
    }

    fun mapToEntity(dtos: List<ControllerDto>): List<ControllerEntity> = dtos.map(::mapToEntity)
}
