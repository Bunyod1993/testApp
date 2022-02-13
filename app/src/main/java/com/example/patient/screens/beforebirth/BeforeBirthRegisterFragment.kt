package com.example.patient.screens.beforebirth


import com.example.patient.databinding.BeforeBirthRegisterFragmentBinding
import com.example.patient.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeforeBirthRegisterFragment : BaseFragment<BeforeBirthRegisterFragmentBinding,BeforeBirthRegisterViewModel>() {
    override fun getViewModelClass()=BeforeBirthRegisterViewModel::class.java
    override fun getViewBinding()= BeforeBirthRegisterFragmentBinding.inflate(layoutInflater)
}