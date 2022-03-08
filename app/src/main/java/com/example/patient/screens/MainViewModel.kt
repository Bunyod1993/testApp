package com.example.patient.screens

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.patient.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(prefs: SharedPreferences): ViewModel() {
    val authToken= MutableLiveData("default")
    init {
        authToken.postValue(prefs.getString(Constants.AUTH_TOKEN,""))
    }
}