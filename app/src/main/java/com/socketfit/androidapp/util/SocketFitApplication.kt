package com.socketfit.androidapp.util

import android.app.Application
import com.socketfit.androidapp.moduleOne.dashboard.DashboardFragment

class SocketFitApplication : Application() {

    companion object {
        private lateinit var instance: SocketFitApplication

        fun getInstance(): SocketFitApplication {
            return instance
        }
        var pref: SavePreferences? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        pref = SavePreferences(this)
    }

    var currentSelectedActivity  :String = ""
    lateinit var dashboardFragment : DashboardFragment
}