package com.example.patient.screens.info


import com.example.patient.databinding.InfoFragmentBinding
import com.example.patient.screens.MainActivity
import com.example.patient.utils.base.BaseFragment

class InfoFragment :  BaseFragment<InfoFragmentBinding, InfoViewModel>() {

    override fun getViewBinding() = InfoFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = InfoViewModel::class.java
    override fun setUpViews() {
        super.setUpViews()
        (activity as MainActivity).setSupportActionBar(binding.toolbar)

    }

}