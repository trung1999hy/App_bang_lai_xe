package com.example.drivingtest.ui

import android.app.Application
import com.example.drivingtest.local.Preference

class MainApp : Application() {

    var preference: Preference? = null
    override fun onCreate() {
        super.onCreate()
        instant = this
        preference = Preference.buildInstance(this)
        if (preference?.firstInstall == false) {
            preference?.firstInstall = true
            preference?.setValueCoin(30)
        }
    }

    companion object {
        private var  instant: MainApp? = null
        fun getInstant(): MainApp? {
            if (instant == null) {
                instant = MainApp()
            }
            return instant
        }
    }


}