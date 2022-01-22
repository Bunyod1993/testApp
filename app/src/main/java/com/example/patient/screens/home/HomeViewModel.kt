package com.example.patient.screens.home

import androidx.lifecycle.MutableLiveData
import com.example.patient.repositories.auth.AuthRepository
import com.example.patient.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val authRepo: AuthRepository): BaseViewModel() {
    val phone=MutableLiveData("")
    val otp=MutableLiveData("")
    fun getApi(){
//        viewModelScope.launch {
//            authRepo.login(this@HomeViewModel).collect{
//                Log.v("tag","hello $it")
//            }
//        }
    }

}