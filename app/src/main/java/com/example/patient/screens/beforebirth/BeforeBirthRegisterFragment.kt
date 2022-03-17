package com.example.patient.screens.beforebirth


import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.patient.R
import com.example.patient.databinding.BeforeBirthRegisterFragmentBinding
import com.example.patient.screens.MainActivity
import com.example.patient.utils.base.BaseFragment
import com.example.patient.utils.ui.*
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@AndroidEntryPoint
class BeforeBirthRegisterFragment :
    BaseFragment<BeforeBirthRegisterFragmentBinding, BeforeBirthRegisterViewModel>() {
    override fun getViewModelClass() = BeforeBirthRegisterViewModel::class.java
    override fun getViewBinding() = BeforeBirthRegisterFragmentBinding.inflate(layoutInflater)
    override fun setUpViews() {
        super.setUpViews()
        (activity as MainActivity).setSupportActionBar(binding.toolbar)

        binding.viewModel = viewModel

        lifecycleScope.launch {
            viewModel.fieldError.collect {
                binding.date.validate(requireContext(), it.second)
            }
        }

        binding.next.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_toEmergencyFragment)
        }

        binding.date.setOnFocusChangeListener { _, b ->
            if (b) {
                viewModel.validateDate()
            }
        }

        binding.secondDateField.setOnFocusChangeListener { _, b ->
            if (b) {
                viewModel.validateSecondDate()
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
        binding.dateSecond.setEndIconOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder
                    .datePicker()
                    .setTitleText(getString(R.string.date_of_pastonavka))
                    .build()
            datePicker.show(parentFragmentManager, "2date")
            datePicker.addOnPositiveButtonClickListener {
                binding.secondDateField.setText(it.toDate())
            }
        }

        viewModel.firstAnalysis.postValue(binding.checkbox.isChecked)
        binding.checkbox.setOnCheckedChangeListener { _, b ->
            viewModel.firstAnalysis.postValue(b)
        }

        binding.checkbox1.setOnCheckedChangeListener { _, b ->
            viewModel.secondAnalysis.postValue(b)
            if (b) {
                viewModel.addField()
                binding.dateSecond.visible()
            } else {
                viewModel.removeField()
                binding.dateSecond.invisible()
            }
        }

    }

    override fun observeData() {
        super.observeData()
        viewModel.date.observe(viewLifecycleOwner) {
            binding.date.reset()
        }
        viewModel.secondDate.observe(viewLifecycleOwner) {
            binding.dateSecond.reset()
        }
    }
}