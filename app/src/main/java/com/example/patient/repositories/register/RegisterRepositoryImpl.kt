package com.example.patient.repositories.register

import android.util.Log
import com.example.patient.utils.base.BaseRemoteRepository
import com.example.patient.utils.base.RemoteErrorEmitter
import com.example.patient.utils.ui.normalize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RegisterRepositoryImpl @Inject constructor(private val api: RegisterApi) :
    RegisterRepository, BaseRemoteRepository() {
    override suspend fun registerPregnant(
        emitter: RemoteErrorEmitter,
        register: Register
    ): Flow<RegisterResp> {
        return flow {
            safeApiCallNoContext(emitter) {
                val resp = api.register(
                    register.fio,
                    register.publishDate.normalize(),
                    register.type,
                    register.birthday.normalize(),
                    register.passport,
                    register.address,
                    register.phone,
                    register.phoneEx,
                    register.infoMenstruation.normalize(),
                    register.infoEstimatedDate.normalize(),
                    register.infoParity,
                    register.infoBirthPermit
                )
                emit(resp)
            }
        }
    }

    override suspend fun updateFormSecond(
        emitter: RemoteErrorEmitter,
        form2: Form2,
        code: String
    ): Flow<RegisterResp> {
        return flow {
            safeApiCallNoContext(emitter) {
                Log.v("tag", "retrofit start $form2 $code")
                form2.visit_date_1=form2.visit_date_1.normalize()
                form2.visit_date_2=form2.visit_date_2.normalize()
                val resp = api.updateFormSecond(
                    code,
                    form2.ch_visit_date_1,
                    form2.visit_date_1,
                    form2.ch_visit_date_2,
                    form2.visit_date_2
                )
                Log.v("tag", "$resp")
                emit(resp)
            }
        }
    }

    override suspend fun updateFormThird(
        emitter: RemoteErrorEmitter,
        form2: Form3,
        code: String
    ): Flow<RegisterResp> {
        return flow {
            safeApiCallNoContext(emitter) {
                val resp = api.updateFormThird(
                    code = code,
                    egcy_init_hospital_type = form2.egcy_init_hospital_type,
                    egcy_init_date = form2.egcy_init_date.normalize(),
                    egcy_init_diagnosis = form2.egcy_init_diagnosis,
                    egcy_init_treatment = form2.egcy_init_treatment,
                    egcy_init_phonogram = form2.egcy_init_phonogram,
                    egcy_init_relatives = form2.egcy_init_relatives,
                    egcy_init_projects = form2.egcy_init_projects,
                    egcy_init_accompanying = form2.egcy_init_accompanying,
                    egcy_init_transport_provided = form2.egcy_init_transport_provided,
                    egcy_init_accompanying_gender = form2.egcy_init_accompanying_gender
                )
                Log.v("tag", "$resp")
                emit(resp)
            }
        }
    }

    override suspend fun updateFormFourth(
        emitter: RemoteErrorEmitter,
        form2: Form4,
        code: String
    ): Flow<RegisterResp> {
        return flow {
            safeApiCallNoContext(emitter) {
                val resp = api.updateFormFourth(
                    code = code,
                    rtn_accept_referral = form2.rtn_accept_referral,
                    ch_rtn_accept_newborn_1 = form2.ch_rtn_accept_newborn_1,
                    rtn_accept_newborn_1 = form2.rtn_accept_newborn_1.normalize()
                )
                Log.v("tag", "$resp")
                emit(resp)
            }
        }
    }

    override suspend fun updateFormFifth(
        emitter: RemoteErrorEmitter,
        form2: Form5,
        code: String
    ): Flow<RegisterResp> {
        return flow {
            safeApiCallNoContext(emitter) {
                val resp = api.updateFormFifth(
                    code = code,
                    mlty_maternal = form2.mlty_maternal,
                    mlty_maternal_hospital_id = form2.mlty_maternal_hospital_id,
                    mlty_child_cause = form2.mlty_child_cause,
                    mlty_child = form2.mlty_child,
                    mlty_maternal_cause = form2.mlty_maternal_cause,
                    mlty_child_recorded_days = form2.mlty_child_recorded_days,
                    mlty_child_hospital_id = form2.mlty_child_hospital_id
                )
                Log.v("tag", "$resp")
                emit(resp)
            }
        }
    }

    override suspend fun updateFormFirst(
        emitter: RemoteErrorEmitter,
        register: Register, code: String
    ): Flow<RegisterResp> {
        return flow {
            safeApiCallNoContext(emitter) {
                val resp = api.updateFormFirst(
                    code = code,
                    fio = register.fio,
                    publishDate = register.publishDate,
                    type = register.type,
                    passport = register.passport,
                    birthday = register.birthday.normalize(),
                    address = register.address,
                    phone = register.phone,
                    phoneEx = register.phoneEx,
                    infoMenstruation = register.infoMenstruation,
                    infoEstimatedDate = register.infoEstimatedDate.normalize(),
                    infoBirthPermit = register.infoBirthPermit,
                    infoParity = register.infoParity
                )
                Log.v("tag", "$resp")
                emit(resp)
            }
        }
    }
}