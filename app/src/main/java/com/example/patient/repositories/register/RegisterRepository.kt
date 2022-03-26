package com.example.patient.repositories.register

import com.example.patient.utils.base.RemoteErrorEmitter
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun registerPregnant(emitter: RemoteErrorEmitter,register: Register):Flow<RegisterResp>
    suspend fun updateForm2(emitter: RemoteErrorEmitter,form2: Form2):Flow<Any>
    suspend fun updateForm3(emitter: RemoteErrorEmitter,form2: Form2):Flow<Any>
    suspend fun updateForm4(emitter: RemoteErrorEmitter,form2: Form2):Flow<Any>
    suspend fun updateForm5(emitter: RemoteErrorEmitter,form2: Form2):Flow<Any>
    suspend fun updateForm1(emitter: RemoteErrorEmitter,register: Register):Flow<RegisterResp>

}