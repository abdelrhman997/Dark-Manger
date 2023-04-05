package com.example.darkmanger.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.darkmanger.db.DeviceDao
import com.example.darkmanger.model.Device
import com.example.darkmanger.model.GameType
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class UsageViewModel (private val deviceDao: DeviceDao) : ViewModel() {
    val start1 = MutableLiveData<Boolean>()
    val start2 = MutableLiveData<Boolean>()
    val start3 = MutableLiveData<Boolean>()
    val start4 = MutableLiveData<Boolean>()
    val start5 = MutableLiveData<Boolean>()
    val instant = MutableLiveData<Instant>()
    val instant2 = MutableLiveData<Instant>()
    val instant3 = MutableLiveData<Instant>()
    val instant4 = MutableLiveData<Instant>()
    val instant5 = MutableLiveData<Instant>()
    val time1=MutableLiveData<String>()
    val time2=MutableLiveData<String>()
    val time3=MutableLiveData<String>()
    val time4=MutableLiveData<String>()
    val time5=MutableLiveData<String>()
    private val TAG = "UsageViewModel"
    init {
        start1.value=false
        start2.value=false
        start3.value=false
        start4.value=false
        start5.value=false
        instant.value= Instant.now()
        instant2.value= Instant.now()
        instant3.value= Instant.now()
        instant4.value= Instant.now()
        instant5.value= Instant.now()
        time1.value= DateTimeFormatter.ofPattern(" HH:mm:ss a").withZone(ZoneId.of("UTC+2")).format(Instant.now())
        time2.value = DateTimeFormatter.ofPattern(" HH:mm:ss a").withZone(ZoneId.of("UTC+2")).format(Instant.now())
        time3.value = DateTimeFormatter.ofPattern(" HH:mm:ss a").withZone(ZoneId.of("UTC+2")).format(Instant.now())
        time4.value = DateTimeFormatter.ofPattern(" HH:mm:ss a").withZone(ZoneId.of("UTC+2")).format(Instant.now())
        time5.value = DateTimeFormatter.ofPattern(" HH:mm:ss a").withZone(ZoneId.of("UTC+2")).format(Instant.now())
        Log.i(TAG, ":viewmodel created ")
    }


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
//        val newDevice = updateDeviceStatus(deviceName, id, startTime, endTime, type, price, isPlaying,did)
        updateDevice(newDevice)
    }

}
@RequiresApi(Build.VERSION_CODES.O)
class UsageViewModelFactory(private val deviceDao: DeviceDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsageViewModel(deviceDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")    }

}