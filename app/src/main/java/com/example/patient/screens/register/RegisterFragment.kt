package com.example.patient.screens.register


import androidx.navigation.Navigation
import com.example.patient.R
import com.example.patient.databinding.RegisterFragmentBinding
import com.example.patient.utils.base.BaseFragment

class RegisterFragment :  BaseFragment<RegisterFragmentBinding, RegisterViewModel>() {

    override fun getViewBinding() = RegisterFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = RegisterViewModel::class.java

    override fun setUpViews() {
        super.setUpViews()
        binding.next.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_toRegisterSecondFragment)
        }
    }
}