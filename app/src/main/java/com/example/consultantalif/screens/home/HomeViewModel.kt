package com.example.consultantalif.screens.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.consultantalif.repositories.auth.AuthRepository
import com.example.consultantalif.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val authRepo: AuthRepository): BaseViewModel() {
    fun getApi(){
//        viewModelScope.launch {
//            authRepo.login(this@HomeViewModel).collect{
//                Log.v("tag","hello $it")
//            }
//        }
    }

}