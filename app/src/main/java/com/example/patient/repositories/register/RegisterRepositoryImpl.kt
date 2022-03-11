package com.example.patient.repositories.register

import com.example.patient.utils.base.BaseRemoteRepository
import com.example.patient.utils.base.RemoteErrorEmitter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val api:RegisterApi) : BaseRemoteRepository(),RegisterRepository {
    override suspend fun registerPregnant(
        emitter: RemoteErrorEmitter,
        register: Register
    ): Flow<RegisterResp> {
        return flow {
            safeApiCallNoContext(emitter){
                val resp=api.register(
                    register.fio,register.publishDate,register.type,register.birthday,
                    register.passport,register.address,register.phone,register.phoneEx,
                    register.infoMenstruation,register.infoEstimatedDate,register.infoParity,
                    register.infoBirthPermit
                )
                emit(resp)
            }
        }
    }
}