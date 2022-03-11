package com.example.patient.repositories.register

import com.example.patient.repositories.auth.AuthModel
import retrofit2.http.POST
import retrofit2.http.Query

interface RegisterApi {
    @POST("/api/createPregnant")
    suspend fun register(@Query("fio") fio:String,
                      @Query("publish_date") publishDate:String,
                      @Query("hospital_type") type:Int,
                      @Query("birthdate") birthday:String,
                      @Query("passport") passport:String,
                      @Query("address") address:String,
                      @Query("phone") phone:String,
                      @Query("phone_ex") phoneEx:String,
                      @Query("info_menstruation") infoMenstruation:String,
                      @Query("info_estimated_date") infoEstimatedDate:String,
                      @Query("info_parity") infoParity:Int,
                      @Query("info_birthpermit") infoBirthPermit:Int,
      ): RegisterResp
}