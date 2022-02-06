package com.example.patient.screens.home


import android.os.CountDownTimer
import androidx.navigation.Navigation
import com.example.patient.R
import com.example.patient.databinding.HomeFragmentBinding
import com.example.patient.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    override fun getViewBinding() = HomeFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = HomeViewModel::class.java
    private lateinit var timer: CountDownTimer

    override fun observeData() {
        super.observeData()

    }

    override fun setUpViews() {
        super.setUpViews()
        binding.first.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeToRegister)
        }
        binding.third.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeToLocalPatientsFragment)
        }
        binding.fouth.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeToLocalInfoFragment)
        }
        binding.second.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeToSearchFragment)

        }
    }

}