package com.example.consultantalif.repositories.auth

import com.google.gson.annotations.SerializedName

data class AuthModel(
    @SerializedName("code")
    val code:Int,
    @SerializedName("message")
    val message:String,
    @SerializedName("data")
    val data:String?
) {

}