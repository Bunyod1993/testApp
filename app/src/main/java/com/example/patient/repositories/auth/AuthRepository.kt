package com.example.patient.repositories.auth

import com.example.patient.repositories.Resource
import com.example.patient.utils.base.RemoteErrorEmitter
import com.example.patient.utils.enums.InputErrorType
import com.example.patient.utils.enums.InputType
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(emitter: RemoteErrorEmitter, login :String,password:String): Flow<Resource<String>>
    suspend fun forgot(emitter: RemoteErrorEmitter): Flow<SupportModel>
    fun getFields(): MutableList<Pair<InputType, InputErrorType>>
    fun getProfile():Flow<User?>
    fun logout()
}