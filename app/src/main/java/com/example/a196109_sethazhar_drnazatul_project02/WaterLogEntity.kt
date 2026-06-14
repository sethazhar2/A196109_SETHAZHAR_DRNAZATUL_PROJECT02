package com.example.a196109_sethazhar_drnazatul_project02

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_logs")
data class WaterLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Int,
    val time: String,
    val note: String
)