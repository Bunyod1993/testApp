package com.example.patient.screens.death

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.patient.repositories.register.Form5
import com.example.patient.repositories.register.RegisterRepository
import com.example.patient.utils.base.BaseViewModel
import com.example.patient.utils.base.ScreenState
import com.example.patient.utils.enums.InputErrorType
import com.example.patient.utils.ui.Validator.validateTextFields
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
class RegisterDeathViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
) : BaseViewModel() {

    val deathRegionOne = MutableLiveData("")
    val deathReasonOne = MutableLiveData("")
    val deathHours = MutableLiveData("")
    val deathRegionTwo = MutableLiveData("")
    val deathReasonTwo = MutableLiveData("")

    val buttonEnabled = MutableLiveData(false)
    private val numberOfValidFields = MutableLiveData(2)
    val fieldError = MutableSharedFlow<Pair<String, InputErrorType>>()
    private val listOfFields = mutableListOf<Pair<String, InputErrorType>>()

    private fun addField(field: Pair<String, InputErrorType>) {
        exists(field)
        listOfFields.add(field)
        enableButton()
    }

    private fun exists(field: Pair<String, InputErrorType>) {
        listOfFields.remove(field)
    }

    fun setNumberOfFields(number: Int) {
        numberOfValidFields.postValue(number)
    }

    private fun enableButton() {
        val validFields = listOfFields.filter { pair -> pair.second == InputErrorType.VALID }
        buttonEnabled.postValue(validFields.size == numberOfValidFields.value)
    }

    fun validateDeathReasonOne() {
        viewModelScope.launch {
            deathReasonOne.asFlow().debounce(300).distinctUntilChanged().collect {
                val err = validateTextFields("reason1", it)
                addField(err)
                fieldError.emit(err)
            }
        }
    }

    fun validateDeathRegionOne() {
        viewModelScope.launch {
            deathReasonOne.asFlow().debounce(300).distinctUntilChanged().collect {
                val err = validateTextFields("region1", it)
                addField(err)
                fieldError.emit(err)
            }
        }
    }

    fun validateDeathHours() {
        viewModelScope.launch {
            deathReasonOne.asFlow().debounce(300).distinctUntilChanged().collect {
                val err = validateTextFields("deathHours", it)
                addField(err)
                fieldError.emit(err)
            }
        }
    }

    fun validateDeathReasonTwo() {
        viewModelScope.launch {
            deathReasonOne.asFlow().debounce(300).distinctUntilChanged().collect {
                val err = validateTextFields("reason2", it)
                addField(err)
                fieldError.emit(err)
            }
        }
    }

    fun validateDeathRegionTwo() {
        viewModelScope.launch {
            deathReasonOne.asFlow().debounce(300).distinctUntilChanged().collect {
                val err = validateTextFields("region2", it)
                addField(err)
                fieldError.emit(err)
            }
        }
    }

    fun updateRequest(code: String) {
        viewModelScope.launch {
            val form = Form5()
//            form.ch_visit_date_1 = if (firstAnalysis.value!!) 1 else 0
//            form.visit_date_1 = date.value!!
//            form.ch_visit_date_2 = if (secondAnalysis.value!!) 1 else 0
//            form.visit_date_2 = secondDate.value!!
            mutableScreenState.postValue(ScreenState.LOADING)
            registerRepository.updateForm5(this@RegisterDeathViewModel, form, code)
                .collect {
                    mutableScreenState.postValue(ScreenState.RENDER)
                    Log.v("tag","$it")
                }
        }
    }
}