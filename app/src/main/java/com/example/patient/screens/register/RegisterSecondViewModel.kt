package com.example.patient.screens.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.patient.utils.base.BaseViewModel
import com.example.patient.utils.enums.InputErrorType
import com.example.patient.utils.ui.Validator.validateDigitField
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
class RegisterSecondViewModel @Inject constructor(): BaseViewModel() {
    val fio=MutableLiveData("")
    val phone=MutableLiveData("")
    val extraPhone=MutableLiveData("")
    val mainAddress=MutableLiveData("")
    val dateOfBirth=MutableLiveData("")
    val number=MutableLiveData("")
    val fieldError = MutableSharedFlow<Pair<String, InputErrorType>>()
    val numberOfFieldsRequired=MutableLiveData(6)
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
        buttonEnabled.postValue(validFields.size == numberOfFieldsRequired.value)
    }
    fun validateFio(){
        viewModelScope.launch {
            fio.asFlow().debounce(300).distinctUntilChanged().collect {
                val valid=validateTextFields("fio",it)
                addField(valid)
                fieldError.emit(valid)
            }
        }

    }

    fun validatePhone(){
        viewModelScope.launch {
            phone.asFlow().debounce(300).distinctUntilChanged().collect {
                val valid=validateTextFields("phone",it)
                addField(valid)
                fieldError.emit(valid)
            }
        }
    }
    fun validateExtraPhone(){
        viewModelScope.launch {
            extraPhone.asFlow().debounce(300).distinctUntilChanged().collect {
                val valid=validateTextFields("extraPhone",it)
                addField(valid)
                fieldError.emit(valid)
            }
        }
    }
    fun validateNumber(){
        viewModelScope.launch {
            number.asFlow().debounce(300).distinctUntilChanged().collect {
                val valid= validateDigitField("number",it)
                addField(valid)
                fieldError.emit(valid)
            }
        }
    }
    fun validateAddress(){
        viewModelScope.launch {
            mainAddress.asFlow().debounce(300).distinctUntilChanged().collect {
                val valid=validateTextFields("address",it)
                addField(valid)
                fieldError.emit(valid)
            }
        }
    }
    fun validateBirthDay(){
        viewModelScope.launch {
            dateOfBirth.asFlow().debounce(300).distinctUntilChanged().collect {
                val valid=validateTextFields("dateOfBirth",it)
                addField(valid)
                fieldError.emit(valid)
            }
        }
    }

}