package com.example.consultantalif.screens.login

import androidx.lifecycle.viewModelScope
import com.example.consultantalif.repositories.auth.AuthRepository
import com.example.consultantalif.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository): BaseViewModel() {
    fun login(){
        viewModelScope.launch {
            authRepository.login(this@LoginViewModel).collect {

            }
        }

    }
}