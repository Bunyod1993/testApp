package com.example.patient.screens.localPatients

import com.example.patient.databinding.LocalPatientsFragmentBinding
import com.example.patient.screens.MainActivity
import com.example.patient.utils.base.BaseFragment

class LocalPatientsFragment : BaseFragment<LocalPatientsFragmentBinding, LocalPatientsViewModel>() {

    override fun getViewBinding() = LocalPatientsFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = LocalPatientsViewModel::class.java
    override fun setUpViews() {
        super.setUpViews()
        (activity as MainActivity).setSupportActionBar(binding.toolbar)


    }

}