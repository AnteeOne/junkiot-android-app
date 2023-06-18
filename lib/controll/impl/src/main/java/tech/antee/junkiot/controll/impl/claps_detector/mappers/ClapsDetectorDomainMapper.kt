package tech.antee.junkiot.controll.impl.claps_detector.mappers

import tech.antee.junkiot.controll.claps_detector.models.ClapDetection
import tech.antee.junkiot.controll.impl.claps_detector.local.entities.ClapDetectionValueEntity
import tech.antee.junkiot.controll.impl.claps_detector.remote.dto.AddClapDetectionValueDto
import tech.antee.junkiot.controll.impl.claps_detector.remote.dto.ClapDetectionValueDto
import javax.inject.Inject

class ClapsDetectorDomainMapper @Inject constructor() {

    fun map(entity: ClapDetectionValueEntity): ClapDetection = with(entity) {
        ClapDetection(id, controllerId, timeStamp)
    }

    fun map(dto: ClapDetectionValueDto): ClapDetection = with(dto) {
        ClapDetection(id, controllerId, timeStamp)
    }

    fun mapBack(model: ClapDetection): ClapDetectionValueEntity = with(model) {
        ClapDetectionValueEntity(id, controllerId, timeStamp)
    }

    fun mapBack(controllerId: Int): AddClapDetectionValueDto = AddClapDetectionValueDto(controllerId)
}
