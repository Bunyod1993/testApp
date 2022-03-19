package com.example.patient.screens.reverse

import androidx.lifecycle.lifecycleScope
import com.example.patient.R
import com.example.patient.databinding.ReverseRegisterFragmentBinding
import com.example.patient.screens.MainActivity
import com.example.patient.utils.base.BaseFragment
import com.example.patient.utils.ui.toDate
import com.example.patient.utils.ui.validate
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReverseRegisterFragment :
    BaseFragment<ReverseRegisterFragmentBinding, ReverseRegisterViewModel>() {
    override fun getViewModelClass() = ReverseRegisterViewModel::class.java

    override fun getViewBinding() = ReverseRegisterFragmentBinding.inflate(layoutInflater)
    override fun setUpViews() {
        super.setUpViews()
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        binding.viewModel = viewModel
        binding.dateField.setOnFocusChangeListener { _, b ->
            if (b) {
                viewModel.validateDate()
            }
        }
        binding.date.setEndIconOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder
                    .datePicker()
                    .setTitleText(getString(R.string.date_of_pastonavka))
                    .build()
            datePicker.show(parentFragmentManager, "date")
            datePicker.addOnPositiveButtonClickListener {
                binding.dateField.setText(it.toDate())
            }
        }
    }

    override fun observeData() {
        super.observeData()
        lifecycleScope.launch {
            viewModel.fieldError.collect {
                when (it.first) {
                    "date" -> {
                        binding.dateField.validate(requireContext(), it.second)
                    }
                    else -> {}
                }
            }
        }
        viewModel.date.observe(viewLifecycleOwner) {
            binding.dateField.setBackgroundResource(R.drawable.input)
        }

    }
}