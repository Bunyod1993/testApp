package com.example.patient.screens.register


import androidx.navigation.Navigation
import com.example.patient.R
import com.example.patient.databinding.RegisterThirdFragmentBinding
import com.example.patient.screens.MainActivity
import com.example.patient.utils.base.BaseFragment

class RegisterThirdFragment  :  BaseFragment<RegisterThirdFragmentBinding, RegisterThirdViewModel>() {

    override fun getViewBinding() = RegisterThirdFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = RegisterThirdViewModel::class.java

    override fun setUpViews() {
        super.setUpViews()
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        binding.next.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_toBeforeBirthRegisterFragment)
        }
    }
}