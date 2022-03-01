package com.example.patient.repositories.auth

import androidx.annotation.Keep
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class AuthModel(
    @SerializedName("status")
    val status:String,
    @SerializedName("access_token")
    val token:String,
    @SerializedName("user")
    val user:User?
) {

}


@Keep
@Entity(tableName = "profile")
data class User(
    @SerializedName("id")
    val id:String,
    @SerializedName("fullname")
    val fullName:String,
    @SerializedName("phone")
    val phone:String,
    @SerializedName("image")
    val image:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("job")
    val job:String,
    @SerializedName("region")
    val region:String,
    @SerializedName("subregion")
    val subregion:String,
    @SerializedName("hospital")
    val hospital:String
)
