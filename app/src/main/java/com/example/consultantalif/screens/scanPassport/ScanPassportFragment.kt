package com.example.consultantalif.screens.scanPassport

import com.example.consultantalif.databinding.ScanPassportFragmentBinding
import com.example.consultantalif.utils.base.BaseFragment

class ScanPassportFragment : BaseFragment<ScanPassportFragmentBinding, ScanPassportViewModel>() {

    override fun getViewModelClass() = ScanPassportViewModel::class.java

    override fun getViewBinding(): ScanPassportFragmentBinding = ScanPassportFragmentBinding
        .inflate(layoutInflater)

    override fun observeData() {
        super.observeData()
    }

    override fun setUpViews() {
        super.setUpViews()
    }
}