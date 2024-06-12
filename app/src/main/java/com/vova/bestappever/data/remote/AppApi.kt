package com.vova.bestappever.data.remote

import com.vova.bestappever.common.ApplicationUpdateRequest
import com.vova.bestappever.common.UserAuthRequest
import com.vova.bestappever.common.UserAuthResponse
import com.vova.bestappever.data.models.Application
import com.vova.bestappever.data.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AppApi{

    @GET("allapplicationsmasters")
    suspend fun getNormalApplications() : List<Application>

    @GET("allapplicationsmaster")
    suspend fun getEmergencyApplications() : List<Application>

    @POST("login")
    suspend fun login(@Body userAuth: UserAuthRequest) : UserAuthResponse

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int) : List<User>

    @POST("applications/master/{id}")
    suspend fun update(@Path("id") id: Int, @Body data: ApplicationUpdateRequest)
}