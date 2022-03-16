package com.example

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.example.patient.utils.LocaleManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PatientApp: Application(){
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base?.let { LocaleManager.setLocale(it) })
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)
    }
}
