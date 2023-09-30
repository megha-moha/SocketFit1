package com.socketfit.androidapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sensors")
data class SensorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "is_selected")
    val isSelected: Boolean,

    @ColumnInfo(name = "user_email")
    val userEmail: String // Associate with user's email ID
)
