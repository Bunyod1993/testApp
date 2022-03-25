package com.example.patient.repositories.helper

import com.example.patient.utils.base.RemoteErrorEmitter
import kotlinx.coroutines.flow.Flow

interface HelperRepository {
    suspend fun getHospitals(emitter: RemoteErrorEmitter):Flow<List<Hospital>>
    suspend fun getHospitalTypes(emitter: RemoteErrorEmitter):Flow<List<HospitalType>>
}