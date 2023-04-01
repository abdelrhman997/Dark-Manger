package com.example.darkmanger.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.darkmanger.db.DeviceDao
import com.example.darkmanger.model.Device
import com.example.darkmanger.model.GameType
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.Instant

class UsageViewModel (private val deviceDao: DeviceDao) : ViewModel() {
    private fun insertDevice(device: Device) {
        viewModelScope.launch {
            deviceDao.insert(device)
        }
    }
    private fun updateDevice(device: Device) {
        viewModelScope.launch {
            deviceDao.update(device)
        }
    }

    private fun getNewDeviceEntry(deviceName: String,id:Int, startTime:Instant, endTime: Instant,type:GameType,price:Int,isPlaying:Boolean): Device {
        return Device(
            name = deviceName,id=id, startTime = startTime
        , endTime = endTime, type = type, price = price, isPlaying = isPlaying

        )
    }
    private fun updateDeviceStatus(deviceName: String,id:Int, startTime:Instant, endTime: Instant,type:GameType,price:Int,isPlaying:Boolean,did: Int): Device {
        return Device(
            name = deviceName,id=id, startTime = startTime
        , endTime = endTime, type = type, price = price, isPlaying = isPlaying, dId = did

        )
    }
    fun addNewDevice(deviceName: String, id:Int, startTime:Instant, endTime: Instant, type: GameType, price:Int, isPlaying:Boolean) {
        val newDevice = getNewDeviceEntry(deviceName, id, startTime, endTime, type, price, isPlaying)
        insertDevice(newDevice)
    }
    fun updateDevice(deviceName: String, id:Int, startTime:Instant, endTime: Instant, type: GameType, price:Int, isPlaying:Boolean,did:Int) {
        val newDevice = updateDeviceStatus(deviceName, id, startTime, endTime, type, price, isPlaying,did)
        updateDevice(newDevice)
    }

}

class UsageViewModelFactory(private val deviceDao: DeviceDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsageViewModel(deviceDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")    }

}