package com.example.consultantalif.repositories.auth

import com.example.consultantalif.repositories.Resource
import com.example.consultantalif.utils.Constants
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {
    @POST("/auth/login")
    suspend fun login(@Body body: RequestBody): Response<AuthModel>

    @GET("/questions/{questionId}?key=" + Constants.STACKOVERFLOW_API_KEY + "&site=stackoverflow&filter=withbody")
    suspend fun questionDetails(@Path("questionId") questionId: String?): Response<Any>
}