package com.example.patient.screens.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.patient.repositories.register.RegisterModel
import com.example.patient.repositories.register.RegisterResp
import com.example.patient.repositories.search.SearchRepository
import com.example.patient.repositories.search.SearchResp
import com.example.patient.utils.base.BaseViewModel
import com.example.patient.utils.base.ErrorType
import com.example.patient.utils.base.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : BaseViewModel() {

    val searchInput = MutableLiveData("")
    val fio = MutableLiveData("")
    val identNumber = MutableLiveData("")
    val phone = MutableLiveData("")
    val passport = MutableLiveData("")
    val birthday = MutableLiveData("")

    fun getByIdPhone() {
        viewModelScope.launch {
            mutableScreenState.postValue(ScreenState.LOADING)
            val value = searchInput.value ?: ""
            searchRepository.getPatientsByIDPhone(this@SearchViewModel, value).collect {
                mutableScreenState.postValue(ScreenState.RENDER)
                Log.v("tag", "by id  ${it.payload}")
            }
        }
    }

    fun getPatients(offset: Int, limit: Int):LiveData<SearchResp> {
        val resp=MutableLiveData<SearchResp>()
        viewModelScope.launch {
            val json = JSONObject()
            json.put("fio", fio.value ?: "")
            json.put("passport", passport.value ?: "")
            json.put("code", identNumber.value ?: "")
            json.put("birthdate", birthday.value ?: "")
            json.put("phone", phone.value ?: "")
            searchRepository.getPatients(this@SearchViewModel, offset, limit, json.toString())
                .collect {
                    resp.postValue(it)
                }
        }
        return  resp
    }
    fun getPatientsInit(offset: Int, limit: Int):LiveData<SearchResp> {
        val resp=MutableLiveData<SearchResp>()
        viewModelScope.launch {
            val json = JSONObject()
            json.put("fio", fio.value ?: "")
            json.put("passport", passport.value ?: "")
            json.put("code", identNumber.value ?: "")
            json.put("birthdate", birthday.value ?: "")
            json.put("phone", phone.value ?: "")
            mutableScreenState.postValue(ScreenState.LOADING)
            searchRepository.getPatients(this@SearchViewModel, offset, limit, json.toString())
                .collect {
                    mutableScreenState.postValue(ScreenState.RENDER)
                    resp.postValue(it)
                }
        }
        return  resp
    }
}