package com.example.patient.repositories.register

import androidx.annotation.Keep
import com.example.patient.utils.ui.normalize
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

@Keep
data class Register(
    @SerializedName("fio")
    var fio:String,
    @SerializedName("publish_date")
    var publishDate:String,
    @SerializedName("hospital_type")
    var type:Int,
    @SerializedName("birthdate")
    var birthday:String,
    @SerializedName("phone")
    var phone:String,
    @SerializedName("phone_ex")
    var phoneEx:String,
    @SerializedName("passport")
    var passport:String,
    @SerializedName("address")
    var address:String,
    @SerializedName("info_menstruation")
    var infoMenstruation:String,
    @SerializedName("info_estimated_date")
    var infoEstimatedDate:String,
    @SerializedName("info_parity")
    var infoParity:Int,
    @SerializedName("info_birthpermit")
    var infoBirthPermit:Int
){
    constructor():this("","",-1,"","",
        "","","","","",
        -1,-1)
    fun toJson():String{
        val gson= Gson()
        this.birthday.normalize()
        this.infoEstimatedDate.normalize()
        this.infoMenstruation.normalize()
        return gson.toJson(this)
    }
}
