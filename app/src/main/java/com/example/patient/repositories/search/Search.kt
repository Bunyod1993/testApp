package com.example.patient.repositories.search

import com.example.patient.repositories.register.RegisterModel
import com.google.gson.annotations.SerializedName

data class SearchResp(
    @SerializedName("code")
    val code:Int,

    @SerializedName("message")
    val message:String,

    @SerializedName("payload")
    val payload:List<RegisterModel>
)