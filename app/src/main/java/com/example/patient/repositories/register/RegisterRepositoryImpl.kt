package com.example.patient.repositories.register

import com.example.patient.utils.base.BaseRemoteRepository
import com.example.patient.utils.base.RemoteErrorEmitter
import com.example.patient.utils.ui.normalize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val api: RegisterApi) :
    BaseRemoteRepository(), RegisterRepository {
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

    override suspend fun updateForm2(emitter: RemoteErrorEmitter, form2: Form2): Flow<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun updateForm3(emitter: RemoteErrorEmitter, form2: Form2): Flow<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun updateForm4(emitter: RemoteErrorEmitter, form2: Form2): Flow<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun updateForm5(emitter: RemoteErrorEmitter, form2: Form2): Flow<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun updateForm1(
        emitter: RemoteErrorEmitter,
        register: Register
    ): Flow<RegisterResp> {
        TODO("Not yet implemented")
    }
}