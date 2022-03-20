package com.example.patient.dependencyInjection

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.patient.database.PatientDatabase
import com.example.patient.utils.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, PatientDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries().build()


    @Singleton
    @Provides
    fun provideProfileDao(dataBase: PatientDatabase) = dataBase.profileDao()

    @Singleton
    @Provides
    fun providePatientDao(dataBase: PatientDatabase) = dataBase.patientDao()


}