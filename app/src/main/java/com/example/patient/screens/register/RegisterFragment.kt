package com.example.patient.screens.register


import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.patient.R
import com.example.patient.databinding.RegisterFragmentBinding
import com.example.patient.screens.MainActivity
import com.example.patient.utils.base.BaseFragment
import com.example.patient.utils.ui.Utils
import com.example.patient.utils.ui.Utils.getTypes
import com.example.patient.utils.ui.toDate
import com.example.patient.utils.ui.validate
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterFragmentBinding, RegisterViewModel>() {

    override fun getViewBinding() = RegisterFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = RegisterViewModel::class.java

    override fun setUpViews() {
        super.setUpViews()
        binding.registerViewModel = viewModel
        (activity as MainActivity).setSupportActionBar(binding.toolbar)

        binding.next.setOnClickListener {
            if (viewModel.buttonEnabled.value!!) {
                mainViewModel.register.type = viewModel.type.value ?: -1
                mainViewModel.register.publishDate = viewModel.date.value ?: ""
                Navigation.findNavController(it).navigate(R.id.action_toRegisterSecondFragment)
            } else {
                viewModel.validateFields()
            }

        }
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



        binding.typeField.setOnItemClickListener { _, _, i, _ ->
            val itemId = getTypes()[i].first
            viewModel.type.postValue(itemId)
        }

        binding.typeField.setOnFocusChangeListener { _, b ->
            if (b) {
                val adapter = ArrayAdapter(requireContext(), R.layout.list_item,
                    getTypes().map { pair -> pair.second })
                binding.typeField.setAdapter(adapter)
                viewModel.validateType()
            }
        }

        binding.typeField.setBackgroundResource(R.drawable.input)

    }

    override fun observeData() {
        super.observeData()
        lifecycleScope.launch {
            viewModel.fieldError.collect {
                when (it.first) {
                    "date" -> {
                        binding.dateField.validate(requireContext(), it.second)
                    }
                    else -> {
                        binding.typeField.validate(requireContext(), it.second)
                    }
                }
            }
        }
        viewModel.date.observe(viewLifecycleOwner) {
            binding.dateField.setBackgroundResource(R.drawable.input)
        }

        viewModel.user.observe(viewLifecycleOwner) {
            it?.let { user ->
                binding.region.text = user.region
                binding.street.text = user.subregion
                binding.filial.text = user.hospital
            }
        }


    }

}