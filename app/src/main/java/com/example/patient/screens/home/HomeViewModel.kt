package com.example.patient.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.patient.repositories.auth.AuthRepository
import com.example.patient.repositories.auth.User
import com.example.patient.repositories.helper.HelperRepository
import com.example.patient.repositories.helper.Hospital
import com.example.patient.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepo: AuthRepository
) : BaseViewModel() {
    val phone = MutableLiveData("")
    val user = MutableLiveData<User?>(null)
    fun getProfile() {
        viewModelScope.launch {
            authRepo.getProfile().collect {
                user.postValue(it)
            }
        }
    }
}