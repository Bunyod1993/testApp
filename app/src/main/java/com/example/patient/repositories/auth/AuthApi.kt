package com.example.patient.repositories.auth

import com.example.patient.utils.Constants
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {
    @POST("/api/login")
    suspend fun login(@Query("login") login:String,@Query("password") password:String): AuthModel

    @GET("/questions/{questionId}?key=" + Constants.STACKOVERFLOW_API_KEY + "&site=stackoverflow&filter=withbody")
    suspend fun questionDetails(@Path("questionId") questionId: String?): Response<Any>
}