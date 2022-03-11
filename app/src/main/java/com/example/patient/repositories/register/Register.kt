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

@Keep
data class RegisterResp(
    @SerializedName("code")
    val code:Int,
    @SerializedName("message")
    val message:String,
    @SerializedName("payload")
    val payload:RegisterModel
)

@Keep
data class RegisterModel(
    val accept_date: Any,
    val accept_diagnosis: Any,
    val accept_hospital_id: Any,
    val accept_hospital_type: Any,
    val accept_project: Int,
    val address: String,
    val birthdate: String,
    val childbirth_apgar_scale: Any,
    val childbirth_apgar_scale_total: Any,
    val childbirth_complications_child: Any,
    val childbirth_complications_mother: Any,
    val childbirth_date: Any,
    val childbirth_discharged_home: Int,
    val childbirth_emergency: Int,
    val childbirth_emergency_care: Int,
    val childbirth_gender: Any,
    val childbirth_height: Any,
    val childbirth_hospital_id: Any,
    val childbirth_hospital_type: Any,
    val childbirth_operation_ks: Int,
    val childbirth_referral: Int,
    val childbirth_viviparity: Int,
    val childbirth_weight: Any,
    val code: String,
    val created_at: String,
    val created_by: Int,
    val egcy2_acpt_date: Any,
    val egcy2_acpt_diagnosis: Any,
    val egcy2_acpt_hospital_id: Any,
    val egcy2_acpt_hospital_type: Any,
    val egcy2_acpt_projects: Int,
    val egcy2_init_accompanying: Int,
    val egcy2_init_accompanying_gender: Any,
    val egcy2_init_date: Any,
    val egcy2_init_diagnosis: Any,
    val egcy2_init_hospital_id: Any,
    val egcy2_init_hospital_type: Any,
    val egcy2_init_phonogram: Int,
    val egcy2_init_projects: Int,
    val egcy2_init_relatives: Int,
    val egcy2_init_transport_provided: Any,
    val egcy2_init_treatment: Any,
    val egcy_acpt_date: Any,
    val egcy_acpt_diagnosis: Any,
    val egcy_acpt_hospital_id: Any,
    val egcy_acpt_hospital_type: Any,
    val egcy_acpt_projects: Int,
    val egcy_init_accompanying: Int,
    val egcy_init_accompanying_gender: Any,
    val egcy_init_date: Any,
    val egcy_init_diagnosis: Any,
    val egcy_init_hospital_id: Any,
    val egcy_init_hospital_type: Any,
    val egcy_init_phonogram: Int,
    val egcy_init_projects: Int,
    val egcy_init_relatives: Int,
    val egcy_init_transport_provided: Any,
    val egcy_init_treatment: Any,
    val fio: String,
    val hospital_id: Int,
    val hospital_type: Int,
    val id: Int,
    val info_birthpermit: Int,
    val info_estimated_date: String,
    val info_menstruation: String,
    val info_parity: Int,
    val is_mobile: Int,
    val mlty_child: Int,
    val mlty_child_cause: Any,
    val mlty_child_hospital_id: Any,
    val mlty_child_recorded_days: Any,
    val mlty_maternal: Int,
    val mlty_maternal_cause: Any,
    val mlty_maternal_hospital_id: Any,
    val passport: String,
    val phone: String,
    val phone_ex: String,
    val publish_date: String,
    val region_id: Int,
    val route_agree: Int,
    val route_blank: Int,
    val route_blank_date: Any,
    val route_given_card: Int,
    val route_given_place: Int,
    val route_given_test: Int,
    val route_hospital_id: Any,
    val rtn_accept_hospital_id: Any,
    val rtn_accept_hospital_type: Any,
    val rtn_accept_newborn_1: Any,
    val rtn_accept_newborn_2: Any,
    val rtn_accept_newborn_3: Any,
    val rtn_accept_newborn_4: Any,
    val rtn_accept_postpartum: Int,
    val rtn_accept_qnt: Any,
    val rtn_accept_referral: Int,
    val rtn_ini_card: Int,
    val rtn_ini_given: Int,
    val rtn_ini_hospital_id: Any,
    val rtn_ini_postpartum: Int,
    val rtn_ini_treatment: Int,
    val rtn_qst_leader: Any,
    val rtn_vac_bcg: Any,
    val rtn_vac_hepatitb: Any,
    val rtn_vac_polio: Any,
    val sub_region_id: Int,
    val updated_at: String,
    val updated_by: Int,
    val visit_date_1: String,
    val visit_date_2: Any,
    val visit_date_3: Any,
    val visit_date_4: Any,
    val visit_date_5: Any,
    val visit_date_6: Any,
    val visit_date_7: Any,
    val visit_date_8: Any,
    val visit_gestational: Any,
    val visit_preparations: Int,
    val visit_qnt: Int
)


