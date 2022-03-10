package com.example.patient.screens.register

import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.patient.R
import com.example.patient.databinding.RegisterSecondFragmentBinding
import com.example.patient.screens.MainActivity
import com.example.patient.utils.base.BaseFragment
import com.example.patient.utils.ui.toDate
import com.example.patient.utils.ui.validate
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@AndroidEntryPoint
class RegisterSecondFragment :
    BaseFragment<RegisterSecondFragmentBinding, RegisterSecondViewModel>() {

    override fun getViewBinding() = RegisterSecondFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = RegisterSecondViewModel::class.java

    override fun setUpViews() {
        super.setUpViews()
        binding.registerViewModel = viewModel
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        binding.next.setOnClickListener {
            mainViewModel.register.fio = viewModel.fio.value ?: ""
            mainViewModel.register.passport = viewModel.number.value ?: ""
            mainViewModel.register.address = viewModel.mainAddress.value ?: ""
            mainViewModel.register.phone = viewModel.phone.value ?: ""
            mainViewModel.register.phoneEx = viewModel.extraPhone.value ?: ""
            mainViewModel.register.birthday = viewModel.dateOfBirth.value ?: ""
            Navigation.findNavController(it).navigate(R.id.action_toRegisterThirdFragment)
        }

    }

    override fun observeData() {
        super.observeData()
        lifecycleScope.launch {
            viewModel.fieldError.collect {
                when (it.first) {
                    "fio" -> {
                        binding.fioField.validate(requireContext(), it.second, null)
                    }
                    "address" -> {
                        binding.addressField.validate(requireContext(), it.second, null)

                    }
                    "phone" -> {
                        binding.phoneField.validate(requireContext(), it.second, null)
                    }
                    "extraPhone" -> {
                        binding.phoneRelativeField.validate(requireContext(), it.second, null)
                    }
                    "number" -> {
                        binding.serialField.validate(requireContext(), it.second, null)

                    }
                    "dateOfBirth" -> {
                        binding.dateField.validate(requireContext(), it.second, null)
                    }
                    else -> {

                    }
                }
            }
        }
        viewModel.dateOfBirth.observe(viewLifecycleOwner) {
            binding.dateField.setBackgroundResource(R.drawable.input)
        }
        viewModel.fio.observe(viewLifecycleOwner) {
            binding.fioField.setBackgroundResource(R.drawable.input)
        }
        viewModel.phone.observe(viewLifecycleOwner) {
            binding.phoneField.setBackgroundResource(R.drawable.input)
        }
        viewModel.extraPhone.observe(viewLifecycleOwner) {
            binding.phoneRelativeField.setBackgroundResource(R.drawable.input)
        }
        viewModel.number.observe(viewLifecycleOwner) {
            binding.serialField.setBackgroundResource(R.drawable.input)
        }
        viewModel.mainAddress.observe(viewLifecycleOwner) {
            binding.addressField.setBackgroundResource(R.drawable.input)
        }
    }

    override fun observeView() {
        super.observeView()
        binding.dateField.setOnFocusChangeListener { _, b ->
            if (b) viewModel.validateBirthDay()
        }
        binding.fioField.setOnFocusChangeListener { _, b ->
            if (b) viewModel.validateFio()
        }
        binding.phoneField.setOnFocusChangeListener { _, b ->
            if (b) viewModel.validatePhone()
        }
        binding.phoneRelativeField.setOnFocusChangeListener { _, b ->
            if (b) viewModel.validateExtraPhone()
        }
        binding.serialField.setOnFocusChangeListener { _, b ->
            if (b) viewModel.validateNumber()
        }
        binding.addressField.setOnFocusChangeListener { _, b ->
            if (b) viewModel.validateAddress()
        }

        binding.date.setEndIconOnClickListener {
            viewModel.validateBirthDay()
            val datePicker =
                MaterialDatePicker.Builder
                    .datePicker()
                    .setTitleText(getString(R.string.date_of_pastonavka))
                    .build()
            datePicker.show(parentFragmentManager, "dateOfBirth")
            datePicker.addOnPositiveButtonClickListener {
                binding.dateField.setText(it.toDate())
            }
        }
    }
}