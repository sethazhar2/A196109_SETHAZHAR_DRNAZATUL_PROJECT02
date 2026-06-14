package com.example.a196109_sethazhar_drnazatul_project02

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterLogDao {

    @Insert
    suspend fun insert(log: WaterLogEntity)
    @Delete
    suspend fun deleteLog(log: WaterLogEntity)
    @Update
    suspend fun update(log: WaterLogEntity)

    @Query("SELECT * FROM water_logs")
    fun getAllLogs(): Flow<List<WaterLogEntity>>
}