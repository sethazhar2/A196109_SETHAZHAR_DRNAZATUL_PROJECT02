package com.example.a196109_sethazhar_drnazatul_project02

class HydroRepository(private val dao: WaterLogDao) {

    val allLogs = dao.getAllLogs()

    suspend fun insert(log: WaterLogEntity) {
        dao.insert(log)
    }
    suspend fun delete(log: WaterLogEntity) {
        dao.deleteLog(log)
    }
    suspend fun update(log: WaterLogEntity) {
        dao.update(log)
    }
}