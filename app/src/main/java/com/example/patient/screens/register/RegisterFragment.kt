package com.example.patient.screens.register


import com.example.patient.databinding.RegisterFragmentBinding
import com.example.patient.utils.base.BaseFragment

class RegisterFragment :  BaseFragment<RegisterFragmentBinding, RegisterViewModel>() {

    override fun getViewBinding() = RegisterFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = RegisterViewModel::class.java


}