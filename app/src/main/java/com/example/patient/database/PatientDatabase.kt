package com.example.patient.database

import androidx.room.*

import com.example.patient.utils.Constants.DB_VERSION
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Database(
    entities = [Test::class],
    version = DB_VERSION
)
@Singleton
abstract class PatientDatabase : RoomDatabase() {
    abstract fun testDao(): TestDao
}

@Dao
interface TestDao{
    @Query("SELECT * FROM test")
    suspend fun getTest(): Flow<Test>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTest(vararg test: Test)
}
@Entity(tableName = "test")
data class Test(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val test:String)