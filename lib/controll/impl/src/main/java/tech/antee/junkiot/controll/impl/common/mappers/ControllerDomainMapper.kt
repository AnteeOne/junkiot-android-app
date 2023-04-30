package tech.antee.junkiot.controll.impl.common.mappers

import tech.antee.junkiot.controll.common.models.Controller
import tech.antee.junkiot.controll.common.models.parseControllerType
import tech.antee.junkiot.controll.impl.common.local.entities.ControllerEntity
import tech.antee.junkiot.controll.impl.common.remote.dto.ControllerDto

class ControllerDomainMapper {

    fun map(entity: ControllerEntity): Controller = with(entity) {
        Controller(controllerType, id, name, isOnline)
    }

    fun map(entities: List<ControllerEntity>): List<Controller> = entities.map(::map)

    fun map(dto: ControllerDto): Controller = with(dto) {
        Controller(controllerTypeId.parseControllerType(), id, name, isOnline)
    }
}
