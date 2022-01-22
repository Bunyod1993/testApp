package com.example.patient.screens

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.patient.utils.Constants.AUTH_TOKEN
import javax.inject.Inject

class SplashViewModel @Inject constructor(prefs: SharedPreferences):ViewModel() {
    val authToken=MutableLiveData("")
    init {
        authToken.postValue(prefs.getString(AUTH_TOKEN,""))
    }
}