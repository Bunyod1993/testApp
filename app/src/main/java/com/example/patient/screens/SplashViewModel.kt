package com.example.patient.screens

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.patient.utils.Constants.AUTH_TOKEN
import com.example.patient.utils.Constants.FIRST_ACCESS
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(prefs: SharedPreferences):ViewModel() {
    val firstAccess=MutableLiveData<Boolean?>(null)
    init {
        firstAccess.postValue(prefs.getBoolean(FIRST_ACCESS,true))
    }
}