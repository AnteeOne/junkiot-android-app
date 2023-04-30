package tech.antee.junkiot.controll.impl.light_sensor.remote.services

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import tech.antee.junkiot.controll.impl.light_sensor.remote.dto.AddLightSensorValueDto

interface LightSensorApi {

    @GET("controllers/light-sensor/values")
    suspend fun getLightSensorValues(@Path("controllerId") controllerId: Int? = null)

    @POST("controllers/light-sensor/values")
    suspend fun addLightSensorValue(@Body dto: AddLightSensorValueDto)
}
