package com.example.patient.repositories.search

import com.example.patient.utils.base.RemoteErrorEmitter
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun getPatientsByIDPhone(emitter: RemoteErrorEmitter,value:String): Flow<SearchResp>

    suspend fun getPatients(
        emitter: RemoteErrorEmitter,
        offset: Int,
        limit: Int,
        jsonBody: String
    ): Flow<SearchResp>

}