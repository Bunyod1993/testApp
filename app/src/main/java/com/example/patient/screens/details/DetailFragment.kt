package com.example.patient.screens.details


import com.example.patient.databinding.DetailFragmentBinding
import com.example.patient.utils.base.BaseFragment

class DetailFragment : BaseFragment<DetailFragmentBinding, DetailViewModel>() {
    override fun getViewModelClass() = DetailViewModel::class.java

    override fun getViewBinding() = DetailFragmentBinding.inflate(layoutInflater)

}