package com.socketfit.androidapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SensorDao {
    @Query("SELECT * FROM sensors")
    fun getAllSensors(): LiveData<List<SensorEntity>>

    @Update
    suspend fun updateSensor(sensor: SensorEntity)

    @Delete
    suspend fun deleteSensor(sensor: SensorEntity)

    @Insert
    suspend fun insertSensor(sensor: SensorEntity)

}
