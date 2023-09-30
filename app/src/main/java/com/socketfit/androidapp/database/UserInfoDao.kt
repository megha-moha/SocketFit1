package com.socketfit.androidapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserInfoDao {
    @Insert
    suspend fun insertUserInfo(userInfo: UserInfo)

    @Query("SELECT * FROM user_info WHERE email = :email AND password = :password")
    suspend fun getUserByEmailAndPassword(email: String, password: String): UserInfo?

}
