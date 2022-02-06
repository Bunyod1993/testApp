package com.example.patient.screens.register

import androidx.navigation.Navigation
import com.example.patient.R
import com.example.patient.databinding.RegisterSecondFragmentBinding
import com.example.patient.utils.base.BaseFragment

class RegisterSecondFragment :  BaseFragment<RegisterSecondFragmentBinding, RegisterSecondViewModel>() {

    override fun getViewBinding() = RegisterSecondFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = RegisterSecondViewModel::class.java

    override fun setUpViews() {
        super.setUpViews()
        binding.next.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_toRegisterThirdFragment)
        }
    }
}