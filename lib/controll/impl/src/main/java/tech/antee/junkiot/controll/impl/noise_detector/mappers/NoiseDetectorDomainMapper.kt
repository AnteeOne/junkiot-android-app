package tech.antee.junkiot.controll.impl.noise_detector.mappers

import tech.antee.junkiot.controll.impl.noise_detector.local.entities.NoiseDetectionValueEntity
import tech.antee.junkiot.controll.impl.noise_detector.remote.dto.AddNoiseDetectionValueDto
import tech.antee.junkiot.controll.impl.noise_detector.remote.dto.NoiseDetectionValueDto
import tech.antee.junkiot.controll.noise_detector.models.NoiseDetection
import javax.inject.Inject

class NoiseDetectorDomainMapper @Inject constructor() {

    fun map(entity: NoiseDetectionValueEntity): NoiseDetection = with(entity) {
        NoiseDetection(id, controllerId, label, timeStamp)
    }

    fun map(dto: NoiseDetectionValueDto): NoiseDetection = with(dto) {
        NoiseDetection(id, controllerId, label, timeStamp)
    }

    fun mapBack(model: NoiseDetection): NoiseDetectionValueEntity = with(model) {
        NoiseDetectionValueEntity(id, controllerId, audioLabel, timeStamp)
    }

    fun mapBack(controllerId: Int, label: String): AddNoiseDetectionValueDto =
        AddNoiseDetectionValueDto(controllerId, label)
}
