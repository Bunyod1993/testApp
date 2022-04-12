package com.example.patient.database

import androidx.room.*
import com.example.patient.repositories.auth.User
import com.example.patient.repositories.register.Register
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {

    @Query("SELECT * FROM patient")
    fun getPatients(): Flow<List<Register>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatient(vararg entity: Register):Long

    @Delete
    suspend fun deletePatient(register: Register)

}