package tech.antee.junkiot.controll.impl.common.network.service

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import tech.antee.junkiot.controll.impl.common.network.dto.AddControllerDto
import tech.antee.junkiot.controll.impl.common.network.dto.ControllerDto

interface ControllerApi {

    @GET("controllers")
    suspend fun getControllers(): List<ControllerDto>

    @POST("controllers")
    suspend fun addController(addController: AddControllerDto): ControllerDto

    @DELETE("controllers/{id}")
    suspend fun deleteController(@Path("id") id: Int)
}
