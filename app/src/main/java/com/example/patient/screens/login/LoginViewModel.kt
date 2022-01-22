package com.example.patient.screens.login

import android.content.SharedPreferences
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.patient.repositories.auth.AuthRepository
import com.example.patient.utils.Constants.AUTH_TOKEN
import com.example.patient.utils.base.BaseViewModel
import com.example.patient.utils.base.ScreenState
import com.example.patient.utils.enums.InputErrorType
import com.example.patient.utils.enums.InputType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(prefs:SharedPreferences,private val authRepository: AuthRepository) :
    BaseViewModel() {
    val token = MutableLiveData<String>()
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val isLogin = MutableLiveData(false)
    val fieldError = MutableLiveData(Pair(InputType.NONE, InputErrorType.EMPTY))
    private val setOfFields by lazy { authRepository.getFields() }
    init {
        token.postValue(prefs.getString(AUTH_TOKEN,""))
    }
    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            val paramObject = JSONObject()
            paramObject.put(InputType.EMAIL.fieldType, email.value)
            paramObject.put(InputType.PASSWORD.fieldType, password.value)
            mutableScreenState.postValue(ScreenState.LOADING)
            authRepository.login(this@LoginViewModel, paramObject.toString())
                .collect {
                    mutableScreenState.postValue(ScreenState.RENDER)
                    token.postValue(it.data?:"")
                }
        }
    }

    fun validateLoginFields(inputType: InputType) {
        val str: String
        val pair: Pair<InputType, InputErrorType>
        if (inputType == InputType.EMAIL) {
            str = email.value ?: ""
            pair =
                when {
                    str.isEmpty() -> {
                        Pair(inputType, InputErrorType.EMPTY)
                    }
                    str.matches(Patterns.EMAIL_ADDRESS.toRegex()) -> {
                        Pair(inputType, InputErrorType.VALID)
                    }
                    else -> {
                        Pair(inputType, InputErrorType.MISMATCH)
                    }
                }

        } else {
            str = password.value ?: ""
            pair = when {
                str.isEmpty() -> {
                    Pair(inputType, InputErrorType.EMPTY)
                }
                str.length > 4 -> {
                    Pair(inputType, InputErrorType.VALID)
                }
                else -> {
                    Pair(inputType, InputErrorType.INVALID)
                }
            }
        }
        alterField(pair)
        fieldError.postValue(pair)
        enableButton()
    }

    //    to be continued
    private fun enableButton() {
        val validFields = setOfFields.filter { it.second == InputErrorType.VALID }
        isLogin.postValue(validFields.size == setOfFields.size)
    }

    private fun alterField(pair: Pair<InputType, InputErrorType>) {
        val field = setOfFields.find { pair.first == it.first }
        val index = setOfFields.indexOf(field)
        if (index > -1) setOfFields.removeAt(index)
        setOfFields.add(pair)
    }
}