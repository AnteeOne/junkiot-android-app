package tech.antee.junkiot.controll.impl.common.mappers

import tech.antee.junkiot.controll.common.models.AddController
import tech.antee.junkiot.controll.impl.common.remote.dto.AddControllerDto

class AddControllerDomainMapper {

    fun mapBack(model: AddController): AddControllerDto = with(model) {
        AddControllerDto(type.id, name)
    }
}
