package com.example.darkmanger

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.darkmanger.db.DeviceRoomDatabase

@RequiresApi(Build.VERSION_CODES.O)
class UsageApplication : Application(){
    val database: DeviceRoomDatabase by lazy { DeviceRoomDatabase.getDatabase(this) }
}