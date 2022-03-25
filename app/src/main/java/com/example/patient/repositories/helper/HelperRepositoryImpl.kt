package com.example.patient.repositories.helper

import com.example.patient.database.HelperDao
import com.example.patient.utils.base.BaseRemoteRepository
import com.example.patient.utils.base.RemoteErrorEmitter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HelperRepositoryImpl @Inject constructor(
    private val helperApi: HelperApi,
    private val helperDao: HelperDao
) : HelperRepository, BaseRemoteRepository() {
    override suspend fun getHospitals(emitter: RemoteErrorEmitter): Flow<List<Hospital>> {
        return flow {
            safeApiCallNoContext(emitter) {
                 helperDao.getHospitals().collect {
                     if (it.isNotEmpty()) emit(it)
                     else {
                        val api= helperApi.getHospitals().payload
                         emit(api)
                         helperDao.insertHospitals(api)
                     }
                 }
            }
        }
    }

    override suspend fun getHospitalTypes(emitter: RemoteErrorEmitter): Flow<List<HospitalType>> {
        return flow {
            safeApiCallNoContext(emitter) {
                helperDao.getHospitalTypes().collect {
                    if (it.isNotEmpty()) emit(it)
                    else {
                        val api= helperApi.getHospitalType().payload
                        emit(api)
                        helperDao.insertHospitalTypes(api)
                    }
                }
            }
        }
    }
}