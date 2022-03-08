package com.example.patient.screens.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.patient.utils.Constants.dateRegex
import com.example.patient.utils.base.BaseViewModel
import com.example.patient.utils.enums.InputErrorType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class RegisterViewModel @Inject constructor() : BaseViewModel() {
    val date = MutableLiveData("")
    val type = MutableLiveData(-1)
    var requiredFiledNumber = 2

    val fieldError = MutableSharedFlow<Pair<String, InputErrorType>>()

    private val listOfFields = mutableListOf<Pair<String, InputErrorType>>()

    val buttonEnabled = MutableLiveData(false)

    private fun addField(field: Pair<String, InputErrorType>) {
        exists(field)
        listOfFields.add(field)
        enableButton()
    }

    private fun exists(field: Pair<String, InputErrorType>) {
        listOfFields.remove(field)
    }

    private fun enableButton() {
        val validFields = listOfFields.filter { pair -> pair.second == InputErrorType.VALID }
        buttonEnabled.postValue(validFields.size == requiredFiledNumber)
    }

    fun validateDate() {
        viewModelScope.launch {
            date.asFlow().debounce(400).distinctUntilChanged().collect {
                val err = if (it.matches(dateRegex.toRegex())) Pair("date", InputErrorType.VALID)
                else Pair("date", InputErrorType.INVALID)
                addField(err)
                fieldError.emit(err)

            }
        }
    }

    fun validateType() {
        viewModelScope.launch {
            type.asFlow().debounce(400).distinctUntilChanged().collect {
                val validator = if (it >= 0) Pair("type", InputErrorType.VALID)
                else Pair("type", InputErrorType.INVALID)
                addField(validator)
                fieldError.emit(validator)
            }
        }
    }
}