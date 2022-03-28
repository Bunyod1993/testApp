package com.example.patient.screens.death

import androidx.lifecycle.lifecycleScope
import com.example.patient.databinding.RegisterDeathFragmentBinding
import com.example.patient.screens.MainActivity
import com.example.patient.utils.base.BaseFragment
import com.example.patient.utils.ui.invisible
import com.example.patient.utils.ui.reset
import com.example.patient.utils.ui.validate
import com.example.patient.utils.ui.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@AndroidEntryPoint
class RegisterDeathFragment : BaseFragment<RegisterDeathFragmentBinding, RegisterDeathViewModel>() {

    override fun getViewModelClass() = RegisterDeathViewModel::class.java

    override fun getViewBinding() = RegisterDeathFragmentBinding.inflate(layoutInflater)
    override fun setUpViews() {
        super.setUpViews()
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.title=""
        binding.viewModel = viewModel
        binding.checkbox1.setOnCheckedChangeListener { _, b ->
            if (b) {
                binding.firstDeathReasonWrapper.visible()
                viewModel.setNumberOfFields(2)
                binding.checkbox2.isChecked = false
            } else binding.firstDeathReasonWrapper.invisible()
        }
        binding.checkbox2.setOnCheckedChangeListener { _, b ->
            if (b) {
                viewModel.setNumberOfFields(3)
                binding.secondDeathReasonWrapper.visible()
                binding.checkbox1.isChecked = false
            } else binding.secondDeathReasonWrapper.invisible()
        }
        binding.deathRegionField.setOnFocusChangeListener { _, b ->
            if (b) {
                viewModel.validateDeathRegionOne()
            }
        }
        binding.deathRegion2.setOnFocusChangeListener { _, b ->
            if (b) {
                viewModel.validateDeathRegionTwo()
            }
        }
        binding.deathReason.setOnFocusChangeListener { _, b ->
            if (b) {
                viewModel.validateDeathReasonOne()
            }
        }
        binding.deathReason2.setOnFocusChangeListener { _, b ->
            if (b) {
                viewModel.validateDeathReasonTwo()
            }
        }
        binding.deathHours.setOnFocusChangeListener { _, b ->
            if (b) {
                viewModel.validateDeathHours()
            }
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.deathHours.observe(viewLifecycleOwner){
            binding.deathHours.reset()
        }
        viewModel.deathReasonOne.observe(viewLifecycleOwner){
            binding.deathReason.reset()
        }
        viewModel.deathReasonTwo.observe(viewLifecycleOwner){
            binding.deathReason2.reset()
        }
        viewModel.deathRegionOne.observe(viewLifecycleOwner){
            binding.deathRegionField.reset()
        }
        viewModel.deathRegionTwo.observe(viewLifecycleOwner){
            binding.deathRegion2.reset()
        }
        lifecycleScope.launch {
            viewModel.fieldError.collect {
                when(it.first){
                    "deathHours" -> {
                        binding.deathHours.validate(requireContext(),it.second)
                    }
                    "region1" -> {
                        binding.deathRegionField.validate(requireContext(),it.second)
                    }
                    "reason1" -> {
                        binding.deathReason.validate(requireContext(),it.second)
                    }
                    "region2" -> {
                        binding.deathRegion2.validate(requireContext(),it.second)
                    }
                    "reason2" -> {
                        binding.deathReason2.validate(requireContext(),it.second)
                    }
                }
            }
        }

    }

}