package com.example.patient.screens.beforebirth


import androidx.navigation.Navigation
import com.example.patient.R
import com.example.patient.databinding.BeforeBirthRegisterFragmentBinding
import com.example.patient.screens.MainActivity
import com.example.patient.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeforeBirthRegisterFragment : BaseFragment<BeforeBirthRegisterFragmentBinding,BeforeBirthRegisterViewModel>() {
    override fun getViewModelClass()=BeforeBirthRegisterViewModel::class.java
    override fun getViewBinding()= BeforeBirthRegisterFragmentBinding.inflate(layoutInflater)
    override fun setUpViews() {
        super.setUpViews()
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        binding.next.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_toEmergencyFragment)
        }
    }
}