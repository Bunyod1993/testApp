package com.example.patient.repositories.search

import androidx.paging.PagingData
import com.example.patient.repositories.register.RegisterModel
import com.example.patient.utils.base.RemoteErrorEmitter
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun getPatientsByIDPhone(emitter: RemoteErrorEmitter, value: String): Flow<SearchResp>

    suspend fun getPatientsByIDPhonePager(
        emitter: RemoteErrorEmitter,
        value: String
    ): Flow<PagingData<RegisterModel>>

    suspend fun getPatientsPager(
        emitter: RemoteErrorEmitter,
        offset: Int,
        limit: Int,
        jsonBody: String
    ): Flow<PagingData<RegisterModel>>

    suspend fun getPatients(
        emitter: RemoteErrorEmitter,
        offset: Int,
        limit: Int,
        jsonBody: String
    ): Flow<SearchResp>

}