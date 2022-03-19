package com.example.patient.screens.death

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.patient.utils.base.BaseViewModel
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
class RegisterDeathViewModel @Inject constructor() : BaseViewModel() {

    val deathRegionOne=MutableLiveData("")
    val deathReasonOne=MutableLiveData("")
    val deathHours=MutableLiveData("")
    val deathRegionTwo=MutableLiveData("")
    val deathReasonTwo=MutableLiveData("")

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
    fun setNumberOfFields(number:Int){
        numberOfValidFields.postValue(number)
    }

    private fun enableButton() {
        val validFields = listOfFields.filter { pair -> pair.second == InputErrorType.VALID }
        buttonEnabled.postValue(validFields.size == numberOfValidFields.value)
    }
    fun validateDeathReasonOne(){
        viewModelScope.launch {
            deathReasonOne.asFlow().debounce(300).distinctUntilChanged().collect {
                val err=validateTextFields("reason1",it)
                addField(err)
                fieldError.emit(err)
            }
        }
    }
    fun validateDeathRegionOne(){
        viewModelScope.launch {
            deathReasonOne.asFlow().debounce(300).distinctUntilChanged().collect {
                val err=validateTextFields("region1",it)
                addField(err)
                fieldError.emit(err)
            }
        }
    }
    fun validateDeathHours(){
        viewModelScope.launch {
            deathReasonOne.asFlow().debounce(300).distinctUntilChanged().collect {
                val err=validateTextFields("deathHours",it)
                addField(err)
                fieldError.emit(err)
            }
        }
    }
    fun validateDeathReasonTwo(){
        viewModelScope.launch {
            deathReasonOne.asFlow().debounce(300).distinctUntilChanged().collect {
                val err=validateTextFields("reason2",it)
                addField(err)
                fieldError.emit(err)
            }
        }
    }
    fun validateDeathRegionTwo(){
        viewModelScope.launch {
            deathReasonOne.asFlow().debounce(300).distinctUntilChanged().collect {
                val err=validateTextFields("region2",it)
                addField(err)
                fieldError.emit(err)
            }
        }
    }
}