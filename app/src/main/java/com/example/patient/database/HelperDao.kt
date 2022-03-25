package com.example.patient.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.patient.repositories.helper.Hospital
import com.example.patient.repositories.helper.HospitalType
import com.example.patient.repositories.register.Register
import kotlinx.coroutines.flow.Flow

@Dao
interface HelperDao {
    @Query("SELECT * FROM hospitals")
    fun getHospitals(): Flow<List<Hospital>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHospitals(hospitals:List<Hospital>)

    @Query("SELECT * FROM hospital_type")
    fun getHospitalTypes(): Flow<List<HospitalType>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHospitalTypes(hospitalTypes:List<HospitalType>)
}