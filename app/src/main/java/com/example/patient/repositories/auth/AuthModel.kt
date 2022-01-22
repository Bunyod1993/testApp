package com.example.patient.repositories.auth

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class AuthModel(
    @SerializedName("code")
    val code:Int,
    @SerializedName("message")
    val message:String,
    @SerializedName("data")
    val data:AuthModelData?
) {

}


@Keep
data class AuthModelData(
    @SerializedName("token")
    val token:String?,
    @SerializedName("promoter")
    val promoter: Promoter
)
@Keep
data class Promoter(
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("enabled")
    val enabled:Boolean,
    @SerializedName("address")
    val address:String,
    @SerializedName("country")
    val country: Country
)
@Keep
data class Country(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("code")
    val code:String
)