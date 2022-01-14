package com.example.consultantalif.repositories.auth

import com.example.consultantalif.repositories.Resource
import com.example.consultantalif.utils.base.RemoteErrorEmitter
import com.example.consultantalif.utils.enums.InputErrorType
import com.example.consultantalif.utils.enums.InputType
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(emitter: RemoteErrorEmitter, jsonString :String): Flow<Resource<String>>
    fun getFields(): MutableList<Pair<InputType, InputErrorType>>
}