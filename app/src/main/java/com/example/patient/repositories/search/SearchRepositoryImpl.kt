package com.example.patient.repositories.search

import com.example.patient.utils.base.BaseRemoteRepository
import com.example.patient.utils.base.ErrorType
import com.example.patient.utils.base.RemoteErrorEmitter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi
) : BaseRemoteRepository(), SearchRepository {

    override suspend fun getPatientsByIDPhone(
        emitter: RemoteErrorEmitter,
        value: String
    ): Flow<SearchResp> {
        return flow {
            safeApiCallNoContext(emitter) {
                val resp=searchApi.getPatientsByFioIDPhone(value)
                if (resp.code==401)
                    emitter.onError(ErrorType.SESSION_EXPIRED)
                emit(resp)
            }
        }
    }

    override suspend fun getPatients(
        emitter: RemoteErrorEmitter,
        offset: Int,
        limit: Int,
        jsonBody: String
    ): Flow<SearchResp> {
        return flow {
            safeApiCallNoContext(emitter) {
                val body = jsonBody.toRequestBody()
                val resp = searchApi.getPatients(offset, limit, body)
                if (resp.code==401)
                    emitter.onError(ErrorType.SESSION_EXPIRED)
                emit(resp)
            }
        }
    }
}