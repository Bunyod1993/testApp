package com.example.patient.screens.death

import com.example.patient.databinding.RegisterDeathFragmentBinding
import com.example.patient.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterDeathFragment : BaseFragment<RegisterDeathFragmentBinding, RegisterDeathViewModel>() {

    override fun getViewModelClass() = RegisterDeathViewModel::class.java

    override fun getViewBinding() = RegisterDeathFragmentBinding.inflate(layoutInflater)

}