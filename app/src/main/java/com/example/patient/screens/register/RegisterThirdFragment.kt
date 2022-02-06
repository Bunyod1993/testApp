package com.example.patient.screens.register


import com.example.patient.databinding.RegisterThirdFragmentBinding
import com.example.patient.utils.base.BaseFragment

class RegisterThirdFragment  :  BaseFragment<RegisterThirdFragmentBinding, RegisterThirdViewModel>() {

    override fun getViewBinding() = RegisterThirdFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = RegisterThirdViewModel::class.java


}