package com.example.consultantalif.repositories.auth

import com.example.consultantalif.repositories.Resource
import com.example.consultantalif.utils.base.RemoteErrorEmitter
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(emitter: RemoteErrorEmitter): Flow<Resource<String>>
}