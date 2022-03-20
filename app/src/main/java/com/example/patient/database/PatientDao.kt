package com.example.patient.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.patient.repositories.auth.User
import com.example.patient.repositories.register.Register
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {
    @Query("SELECT * FROM patient")
    fun getProfile(): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatient(vararg entity: Register)

}