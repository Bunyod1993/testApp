package com.example.consultantalif.repositories.auth

import com.example.consultantalif.utils.base.BaseRemoteRepository
import com.example.consultantalif.utils.base.RemoteErrorEmitter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: AuthApi):AuthRepository,
    BaseRemoteRepository() {
    override suspend fun login(emitter: RemoteErrorEmitter): Flow<Any?> {
        return flow {
            safeApiCallNoContext(emitter){
                api.lastActiveQuestions(3).body()
            }
        }
    }
}