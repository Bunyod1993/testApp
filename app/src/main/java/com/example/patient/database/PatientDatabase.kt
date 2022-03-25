package com.example.patient.database

import androidx.room.*
import com.example.patient.repositories.auth.User
import com.example.patient.repositories.helper.Hospital
import com.example.patient.repositories.helper.HospitalType
import com.example.patient.repositories.register.Register

import com.example.patient.utils.Constants.DB_VERSION
import com.example.patient.utils.Constants.DB_VERSION_NEXT
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Database(
    entities = [User::class, Register::class,HospitalType::class,Hospital::class],
    version = DB_VERSION,
)
@Singleton
abstract class PatientDatabase : RoomDatabase() {
    abstract fun profileDao():ProfileDao
    abstract fun patientDao():PatientDao
    abstract fun helperDao():HelperDao
}
