package com.example.patient.repositories.register

import com.example.patient.utils.base.RemoteErrorEmitter
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun registerPregnant(emitter: RemoteErrorEmitter,register: Register):Flow<RegisterResp>
}