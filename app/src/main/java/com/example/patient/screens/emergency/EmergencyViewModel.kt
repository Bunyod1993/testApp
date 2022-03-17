package com.example.patient.screens.emergency

import androidx.lifecycle.MutableLiveData
import com.example.patient.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmergencyViewModel @Inject constructor() : BaseViewModel() {
    val type = MutableLiveData("")
    val date = MutableLiveData("")
    val diagnose = MutableLiveData("")
    val process = MutableLiveData("")
    val nurse = MutableLiveData("")
    val beenInformed = MutableLiveData(false)
    val hasBeenDirected = MutableLiveData(false)
    val hasHelperNurse = MutableLiveData(false)
    val transportSupported = MutableLiveData(false)
}