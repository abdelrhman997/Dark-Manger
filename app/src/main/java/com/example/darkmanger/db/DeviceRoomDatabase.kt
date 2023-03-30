package com.example.darkmanger.db

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.darkmanger.model.Device
import com.example.darkmanger.utils.Converters

@RequiresApi(Build.VERSION_CODES.O)
@Database(entities = [Device::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)

abstract class DeviceRoomDatabase:RoomDatabase() {
    abstract fun deviceDao(): DeviceDao
    companion object {
        @Volatile
        private var INSTANCE: DeviceRoomDatabase? = null
        fun getDatabase(context: Context): DeviceRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DeviceRoomDatabase::class.java,
                    "device_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}