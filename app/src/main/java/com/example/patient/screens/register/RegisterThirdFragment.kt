package com.example.patient.screens.register


import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.patient.R
import com.example.patient.databinding.RegisterThirdFragmentBinding
import com.example.patient.repositories.register.*
import com.example.patient.screens.MainActivity
import com.example.patient.utils.base.BaseFragment
import com.example.patient.utils.ui.invisible
import com.example.patient.utils.ui.toDate
import com.example.patient.utils.ui.validate
import com.example.patient.utils.ui.visible
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@FlowPreview
@AndroidEntryPoint
class RegisterThirdFragment : BaseFragment<RegisterThirdFragmentBinding, RegisterThirdViewModel>() {

    override fun getViewBinding() = RegisterThirdFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = RegisterThirdViewModel::class.java

    override fun setUpViews() {
        super.setUpViews()
        binding.registerViewModel = viewModel
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        val code = arguments?.getString("code", "") ?: ""
        val bundle = bundleOf()

        if (code.isEmpty()) {
            binding.next.visible()
            binding.save.invisible()

        } else {
            binding.save.visible()
            binding.next.invisible()

            val arg = arguments?.get("reg")
            arg?.let {
                val register = it as Register
                viewModel.initValues(register)
                viewModel.validateFields()
                bundle.putParcelable("reg", register)
                binding.checkbox.isChecked = register.infoBirthPermit == 1
            }

        }
        binding.next.setOnClickListener {
            if (viewModel.buttonEnabled.value!!) {
                mainViewModel.register.infoMenstruation = viewModel.lastMenstruationDate.value ?: ""
                mainViewModel.register.infoEstimatedDate = viewModel.estimatedBirthDate.value ?: ""
                val parity = viewModel.parity.value ?: "-1"
                mainViewModel.register.infoParity = if (parity.isEmpty()) -1 else parity.toInt()
                viewModel.register(mainViewModel.register).observe(viewLifecycleOwner) { model ->
                        val details = mainViewModel.register
                        bundle.putParcelable("reg", details)
                        bundle.putString("code", model?.code)
                        Navigation.findNavController(requireView())
                            .navigate(R.id.action_toDetailsFragment, bundle)

                }
            } else {
                viewModel.validateFields()
            }
        }

        binding.save.setOnClickListener {
            if (viewModel.buttonEnabled.value!!) {
                mainViewModel.register.infoMenstruation = viewModel.lastMenstruationDate.value ?: ""
                mainViewModel.register.infoEstimatedDate = viewModel.estimatedBirthDate.value ?: ""
                val parity = viewModel.parity.value ?: "-1"
                mainViewModel.register.infoParity = if (parity.isEmpty()) -1 else parity.toInt()
                viewModel.update(mainViewModel.register, code)
                    .observe(viewLifecycleOwner) { model ->
                        model?.let {
                            val details = mainViewModel.register
                            bundle.putParcelable("reg", details)
                            bundle.putString("code", it.code)
                            Navigation.findNavController(requireView())
                                .navigate(R.id.action_toDetailsFragment, bundle)
                        }
                    }
            } else {
                viewModel.validateFields()
            }
        }
        lifecycleScope.launch {
            viewModel.fieldError.collect {
                when (it.first) {
                    "dateOfMenstruation" -> {
                        binding.firstDateField.validate(requireContext(), it.second, null)
                    }
                    "parity" -> {
                        binding.parityField.validate(requireContext(), it.second, null)

                    }
                    else -> {

                    }
                }
            }
        }

        binding.firstDate.setEndIconOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder
                    .datePicker()
                    .setTitleText(getString(R.string.date_of_pastonavka))
                    .build()
            datePicker.show(parentFragmentManager, "dateOfMenstruation")
            datePicker.addOnPositiveButtonClickListener {
                binding.firstDateField.setText(it.toDate())
                val estimatedTime = it + TimeUnit.DAYS.toMillis(280)
                binding.secondDateField.setText(estimatedTime.toDate())
                viewModel.validateMenstruation()
            }
        }

        binding.checkbox.setOnCheckedChangeListener { _, b ->
            if (b) mainViewModel.register.infoBirthPermit = 1
            else mainViewModel.register.infoBirthPermit = 0
        }

    }

    override fun observeData() {
        super.observeData()
        viewModel.lastMenstruationDate.observe(viewLifecycleOwner) {
            binding.firstDateField.setBackgroundResource(R.drawable.input)
        }
        viewModel.parity.observe(viewLifecycleOwner) {
            binding.parityField.setBackgroundResource(R.drawable.input)
        }

    }

    override fun observeView() {
        super.observeView()
        binding.firstDateField.setOnFocusChangeListener { _, b ->
            if (b) viewModel.validateMenstruation()
        }
        binding.parityField.setOnFocusChangeListener { _, b ->
            if (b) viewModel.validateParity()
        }
    }
}