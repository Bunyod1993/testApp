package com.example.patient.screens.beforebirth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.patient.repositories.register.Form2
import com.example.patient.repositories.register.RegisterRepository
import com.example.patient.utils.Constants
import com.example.patient.utils.base.BaseViewModel
import com.example.patient.utils.base.ScreenState
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
class BeforeBirthRegisterViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
) : BaseViewModel() {
    val date = MutableLiveData("")
    val secondDate = MutableLiveData("")
    val firstAnalysis = MutableLiveData(false)
    val secondAnalysis = MutableLiveData(false)
    val buttonEnabled = MutableLiveData(false)
    private val numberOfValidFields = MutableLiveData(0)
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


    fun addField() {
        val current = numberOfValidFields.value!! + 1
        numberOfValidFields.postValue(current)
    }

    fun removeField() {
        val current = numberOfValidFields.value!! - 1
        numberOfValidFields.postValue(current)
    }

    fun validateDate() {
        viewModelScope.launch {
            date.asFlow().debounce(400).distinctUntilChanged().collect {
                val err = if (it.matches(Constants.dateRegex.toRegex())) Pair(
                    "date",
                    InputErrorType.VALID
                )
                else Pair("date", InputErrorType.INVALID)
                addField(err)
                fieldError.emit(err)

            }
        }
    }

    fun validateSecondDate() {
        viewModelScope.launch {
            date.asFlow().debounce(400).distinctUntilChanged().collect {
                val err = if (it.matches(Constants.dateRegex.toRegex())) Pair(
                    "2date",
                    InputErrorType.VALID
                )
                else Pair("2date", InputErrorType.INVALID)
                addField(err)
                fieldError.emit(err)
            }
        }
    }

    fun updateRequest(code: String) {
        Log.v("tag","$code")
        viewModelScope.launch {
            val form = Form2()
            form.ch_visit_date_1 = firstAnalysis.value!!
            form.visit_date_1 = date.value!!
            form.ch_visit_date_2 = secondAnalysis.value!!
            form.visit_date_2 = secondDate.value!!
            mutableScreenState.postValue(ScreenState.LOADING)
            registerRepository.updateForm2(this@BeforeBirthRegisterViewModel, form, code)
                .collect {
                    mutableScreenState.postValue(ScreenState.RENDER)
                    Log.v("tag","$it")
                }
        }
    }
}