package com.socketfit.androidapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_info")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    var heightCm: String? = null,  // Nullable and default to null
    var weightKg: String? = null,  // Nullable and default to null
    var amputationType: String? = null,  // Nullable and default to null
    var prostheticType: String? = null  // Nullable and default to null
):Serializable
