package tech.antee.junkiot.controll.impl.noise_detector.remote.services

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import tech.antee.junkiot.controll.impl.noise_detector.remote.dto.AddNoiseDetectionValueDto

interface NoiseDetectorApi {

    @GET("controllers/noise-detector/values")
    suspend fun getNoiseDetectionsValues(@Path("controllerId") controllerId: Int? = null)

    @POST("controllers/noise-detector/values")
    suspend fun addNoiseDetectionValue(@Body dto: AddNoiseDetectionValueDto)
}
