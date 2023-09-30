package com.socketfit.androidapp.database

import android.database.sqlite.SQLiteDatabase
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class DatabasePrepopulation : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        // Insert the initial data for all users
        val listOfPreferences = listOf<String>("Sensor 1", "Sensor 2", "Sensor 3", "Sensor 4", "Sensor 5", "Sensor 6")

        for (sensorName in listOfPreferences) {
            val sensorEntity = SensorEntity(name = sensorName, isSelected = false, userEmail = "")
         //   db.insert("sensors", SQLiteDatabase.CONFLICT_REPLACE, sensorEntity.toContentValues())
        }
    }
}
