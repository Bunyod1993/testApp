package com.example.patient.screens.emergency

import androidx.navigation.Navigation
import com.example.patient.R
import com.example.patient.databinding.EmergencyFragmentBinding
import com.example.patient.screens.MainActivity
import com.example.patient.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmergencyFragment : BaseFragment<EmergencyFragmentBinding,EmergencyViewModel>() {
    override fun getViewModelClass()=EmergencyViewModel::class.java
    override fun getViewBinding()= EmergencyFragmentBinding.inflate(layoutInflater)
    override fun setUpViews() {
        super.setUpViews()
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        binding.viewModel = viewModel
        binding.next.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_toReverseRegisterFragment)
        }
    }


}