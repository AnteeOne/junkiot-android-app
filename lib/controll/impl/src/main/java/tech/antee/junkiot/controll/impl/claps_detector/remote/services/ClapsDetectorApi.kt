package tech.antee.junkiot.controll.impl.claps_detector.remote.services

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import tech.antee.junkiot.controll.impl.claps_detector.remote.dto.AddClapDetectionValueDto

interface ClapsDetectorApi {

    @GET("controllers/claps-detector/values")
    suspend fun getClapsDetectionsValues(@Path("controllerId") controllerId: Int? = null)

    @POST("controllers/claps-detector/values")
    suspend fun addClapDetectionValue(@Body dto: AddClapDetectionValueDto)
}
