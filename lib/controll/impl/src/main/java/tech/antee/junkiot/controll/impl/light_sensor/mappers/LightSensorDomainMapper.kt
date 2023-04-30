package tech.antee.junkiot.controll.impl.light_sensor.mappers

import tech.antee.junkiot.controll.impl.light_sensor.local.entities.LightPredictionEntity
import tech.antee.junkiot.controll.impl.light_sensor.local.entities.LuxEntity
import tech.antee.junkiot.controll.impl.light_sensor.remote.dto.AddLightSensorValueDto
import tech.antee.junkiot.controll.impl.light_sensor.remote.dto.LightPredictionDto
import tech.antee.junkiot.controll.light_sensor.models.LightSensorPredictionValue
import tech.antee.junkiot.controll.light_sensor.models.Lux
import javax.inject.Inject

class LightSensorDomainMapper @Inject constructor() {

    fun map(entity: LuxEntity): Lux = with(entity) {
        Lux(value)
    }

    fun mapBack(model: Lux): LuxEntity = with(model) {
        LuxEntity(value)
    }

    fun mapBack(model: Lux, controllerId: Int): AddLightSensorValueDto = with(model) {
        AddLightSensorValueDto(controllerId, value)
    }

    fun map(dto: LightPredictionDto): LightSensorPredictionValue = with(dto) {
        LightSensorPredictionValue.valueOf(lightSensorPredictionValue)
    }

    fun map(entity: LightPredictionEntity): LightSensorPredictionValue = with(entity) {
        LightSensorPredictionValue.valueOf(value)
    }

    fun mapBack(model: LightSensorPredictionValue): LightPredictionEntity = with(model) {
        LightPredictionEntity(name)
    }
}
