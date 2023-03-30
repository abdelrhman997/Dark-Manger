package com.example.darkmanger.db

import androidx.room.*
import com.example.darkmanger.model.Device
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(device: Device)

    @Update
    suspend fun update(device: Device)

    @Delete
    suspend fun delete(device: Device)

    @Query("SELECT * from device WHERE id = :id")
    fun getDevice(id: Int): Flow<Device>

    @Query("SELECT * from device ORDER BY name ASC")
    fun getDevices(): Flow<List<Device>>
}