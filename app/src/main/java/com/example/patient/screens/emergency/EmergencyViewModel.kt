package com.example.patient.screens.emergency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.patient.repositories.helper.HelperRepository
import com.example.patient.repositories.helper.Hospital
import com.example.patient.utils.base.BaseViewModel
import com.example.patient.utils.base.ScreenState
import com.example.patient.utils.enums.InputErrorType
import com.example.patient.utils.ui.Validator.validateTextFields
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmergencyViewModel @Inject constructor(
    private val helperRepository: HelperRepository
) : BaseViewModel() {
    val type = MutableLiveData("")
    val date = MutableLiveData("")
    val diagnose = MutableLiveData("")
    val process = MutableLiveData("")
    val nurse = MutableLiveData("")
    val beenInformed = MutableLiveData(false)
    val hasBeenDirected = MutableLiveData(false)
    val hasHelperNurse = MutableLiveData(false)
    val transportSupported = MutableLiveData(false)
    val buttonEnabled = MutableLiveData(false)
    private val numberOfValidFields = MutableLiveData(5)
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

    private fun enableButton() {
        val validFields = listOfFields.filter { pair -> pair.second == InputErrorType.VALID }
        buttonEnabled.postValue(validFields.size == numberOfValidFields.value)
    }

    fun validateType() {
        viewModelScope.launch {
            type.asFlow().debounce(300).distinctUntilChanged().collect {
                val err = if (it == "-1") Pair("type", InputErrorType.INVALID)
                else Pair("type", InputErrorType.VALID)
                addField(err)
                fieldError.emit(err)
            }
        }
    }

    fun validateDate() {
        viewModelScope.launch {
            date.asFlow().debounce(300).distinctUntilChanged().collect {
                val err = validateTextFields("date", it)
                addField(err)
                fieldError.emit(err)
            }
        }
    }

    fun validateDiagnose() {
        viewModelScope.launch {
            diagnose.asFlow().debounce(300).distinctUntilChanged().collect {
                val err = validateTextFields("diagnose", it)
                addField(err)
                fieldError.emit(err)
            }
        }
    }

    fun validateProcess() {
        viewModelScope.launch {
            process.asFlow().debounce(300).distinctUntilChanged().collect {
                val err = validateTextFields("process", it)
                addField(err)
                fieldError.emit(err)
            }
        }
    }

    fun validateNurse() {
        viewModelScope.launch {
            nurse.asFlow().debounce(300).distinctUntilChanged().collect {
                val err = validateTextFields("nurse", it)
                addField(err)
                fieldError.emit(err)
            }
        }
    }

    fun validateAll() {
        validateDate()
        validateType()
        validateDiagnose()
        validateProcess()
        validateNurse()
    }
    fun getHospitals(): LiveData<List<Hospital>> {
        val resp = MutableLiveData<List<Hospital>>()
        viewModelScope.launch {
            mutableScreenState.postValue(ScreenState.LOADING)
            helperRepository.getHospitals(this@EmergencyViewModel).collect {
                mutableScreenState.postValue(ScreenState.RENDER)
                resp.postValue(it)
            }
        }
        return resp
    }
}