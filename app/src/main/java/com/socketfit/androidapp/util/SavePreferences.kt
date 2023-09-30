package com.socketfit.androidapp.util

import android.content.Context
import android.content.SharedPreferences

class SavePreferences(var context: Context) {
    private var email = "email"
    private var password = "password"
    private var sharedPreferences: SharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun setEmail(s: String?) {
        editor.putString(email, s)
        editor.commit()
    }

    fun getEmail(): String? {
        return sharedPreferences.getString(email, "")
    }


    fun setPassword(s: String?) {
        editor.putString(password, s)
        editor.commit()
    }

    fun getPassword(): String? {
        return sharedPreferences.getString(password, "")
    }
}