package com.example.patient.repositories.auth

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class AuthModel(
    @SerializedName("access_token")
    val token:String,
    @SerializedName("code")
    val code:Int,
    @SerializedName("message")
    val message:String,
    @SerializedName("payload")
    val user:User?
) {

}


@Keep
@Entity(tableName = "profile")
data class User(
    @SerializedName("id")
    @PrimaryKey
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
