package com.example.consultantalif.screens

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.consultantalif.utils.Constants.AUTH_TOKEN
import javax.inject.Inject

class SplashViewModel @Inject constructor(prefs: SharedPreferences):ViewModel() {
    val authToken=MutableLiveData("")
    init {
        authToken.postValue(prefs.getString(AUTH_TOKEN,""))
    }
}