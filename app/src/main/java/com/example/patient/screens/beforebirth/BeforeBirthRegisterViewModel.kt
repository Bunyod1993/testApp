package com.example.patient.screens.beforebirth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.patient.utils.Constants
import com.example.patient.utils.base.BaseViewModel
import com.example.patient.utils.enums.InputErrorType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeforeBirthRegisterViewModel @Inject constructor() : BaseViewModel() {
    val date = MutableLiveData("")
    val firstAnalysis = MutableLiveData(false)
    val secondAnalysis = MutableLiveData(false)
    val buttonEnabled = MutableLiveData(false)
    val fieldError = MutableLiveData<Pair<String, InputErrorType>>()

    fun validateDate() {
        viewModelScope.launch {
            date.asFlow().debounce(400).distinctUntilChanged().collect {
                val err = if (it.matches(Constants.dateRegex.toRegex())) Pair(
                    "date",
                    InputErrorType.VALID
                )
                else Pair("date", InputErrorType.INVALID)
                buttonEnabled.postValue(err.second == InputErrorType.VALID)
                fieldError.postValue(err)

            }
        }
    }
}