package com.example.patient.screens.reverse

import com.example.patient.databinding.ReverseRegisterFragmentBinding
import com.example.patient.screens.MainActivity
import com.example.patient.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReverseRegisterFragment :
    BaseFragment<ReverseRegisterFragmentBinding, ReverseRegisterViewModel>() {
    override fun getViewModelClass() = ReverseRegisterViewModel::class.java

    override fun getViewBinding() = ReverseRegisterFragmentBinding.inflate(layoutInflater)
    override fun setUpViews() {
        super.setUpViews()
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        binding.viewModel = viewModel
    }
}