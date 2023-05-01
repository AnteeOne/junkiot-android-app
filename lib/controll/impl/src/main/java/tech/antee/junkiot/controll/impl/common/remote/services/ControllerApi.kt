package tech.antee.junkiot.controll.impl.common.remote.services

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import tech.antee.junkiot.controll.impl.common.remote.dto.AddControllerDto
import tech.antee.junkiot.controll.impl.common.remote.dto.ControllerDto

interface ControllerApi {

    @GET("controllers")
    suspend fun getControllers(): List<ControllerDto>

    @POST("controllers")
    suspend fun addController(@Body addController: AddControllerDto): ControllerDto

    @DELETE("controllers/{id}")
    suspend fun deleteController(@Path("id") id: Int)
}
