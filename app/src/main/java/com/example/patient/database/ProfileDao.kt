package com.example.patient.database

import androidx.room.*
import com.example.patient.repositories.auth.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile")
    fun getProfile(): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPromoter(vararg entity: User)

}