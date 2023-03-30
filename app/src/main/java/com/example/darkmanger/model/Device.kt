package com.example.darkmanger.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.Instant
@Entity(tableName = "device")
data class Device(@ColumnInfo(name = "name")var name:String, @ColumnInfo(name = "id")var id:Int,
                  @ColumnInfo(name = "startTime")var startTime:Instant, @ColumnInfo(name = "endTime") var endTime:Instant,
                  @ColumnInfo(name = "GameType") var type:GameType
                  ,@ColumnInfo(name = "price") var price:Int,@ColumnInfo(name = "isPlaying") var isPlaying:Boolean,
                  @PrimaryKey(autoGenerate = true)
                  val dId: Int = 0)
enum class GameType {
    SINGLE,MULTY
}