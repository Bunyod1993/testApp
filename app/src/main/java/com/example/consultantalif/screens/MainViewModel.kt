package com.example.consultantalif.screens

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consultantalif.repositories.auth.AuthRepository
import com.example.consultantalif.utils.Constants
import com.example.consultantalif.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(prefs: SharedPreferences): ViewModel() {
    val authToken= MutableLiveData("")
    init {
        authToken.postValue(prefs.getString(Constants.AUTH_TOKEN,""))
    }
}