package com.example.patient.screens.reverse

import androidx.lifecycle.MutableLiveData
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
class ReverseRegisterViewModel @Inject constructor() : BaseViewModel() {
    val date = MutableLiveData("")

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
        buttonEnabled.postValue(validFields.size == 1)
    }

    fun validateDate() {
        viewModelScope.launch {
            date.asFlow().debounce(400).distinctUntilChanged().collect {
                val err = if (it.matches(Constants.dateRegex.toRegex())) Pair("date", InputErrorType.VALID)
                else Pair("date", InputErrorType.INVALID)
                addField(err)
                fieldError.emit(err)

            }
        }
    }
}