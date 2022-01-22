package com.example.patient.repositories.auth

import com.example.patient.repositories.Resource
import com.example.patient.utils.base.RemoteErrorEmitter
import com.example.patient.utils.enums.InputErrorType
import com.example.patient.utils.enums.InputType
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(emitter: RemoteErrorEmitter, jsonString :String): Flow<Resource<String>>
    fun getFields(): MutableList<Pair<InputType, InputErrorType>>
}