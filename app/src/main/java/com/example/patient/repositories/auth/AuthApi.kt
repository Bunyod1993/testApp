package com.example.patient.repositories.auth


import retrofit2.http.*

interface AuthApi {
    @POST("/api/login")
    suspend fun login(@Query("login") login:String,@Query("password") password:String): AuthModel
}