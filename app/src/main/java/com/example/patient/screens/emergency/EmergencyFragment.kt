package com.example.patient.screens.emergency

import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.patient.R
import com.example.patient.databinding.EmergencyFragmentBinding
import com.example.patient.screens.MainActivity
import com.example.patient.utils.base.BaseFragment
import com.example.patient.utils.ui.reset
import com.example.patient.utils.ui.validate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@AndroidEntryPoint
class EmergencyFragment : BaseFragment<EmergencyFragmentBinding, EmergencyViewModel>() {
    override fun getViewModelClass() = EmergencyViewModel::class.java
    override fun getViewBinding() = EmergencyFragmentBinding.inflate(layoutInflater)
    override fun setUpViews() {
        super.setUpViews()
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.title = ""
        binding.viewModel = viewModel
        val code = arguments?.getString("code", "") ?: ""

        binding.next.setOnClickListener {
            if (viewModel.buttonEnabled.value!!) {
                viewModel.updateRequest(code)
                Navigation.findNavController(it).navigateUp()
            } else viewModel.validateAll()
        }

        viewModel.getHospitals().observe(viewLifecycleOwner) {
            binding.typeField.setOnFocusChangeListener { _, b ->
                if (b) {
                    val adapter = ArrayAdapter(requireContext(), R.layout.list_item,
                        it.map { pair -> pair.title })
                    binding.typeField.setAdapter(adapter)
                    viewModel.validateType()
                }
            }
            binding.typeField.setOnItemClickListener { _, _, i, _ ->
                val itemId = it[i]
                viewModel.type.postValue(itemId.id.toString())
            }
        }


        binding.typeField.setBackgroundResource(R.drawable.input)
        binding.nurseField.setOnFocusChangeListener { _, b ->
            if (b) viewModel.validateNurse()
        }
        binding.processField.setOnFocusChangeListener { _, b ->
            if (b) viewModel.validateProcess()
        }
        binding.reasonField.setOnFocusChangeListener { _, b ->
            if (b) viewModel.validateDiagnose()
        }
        binding.dateField.setOnFocusChangeListener { _, b ->
            if (b) viewModel.validateDate()
        }
        binding.checkbox1.setOnCheckedChangeListener { _, b ->
            viewModel.beenInformed.postValue(b)
        }
        binding.checkbox2.setOnCheckedChangeListener { _, b ->
            viewModel.hasBeenDirected.postValue(b)
        }
        binding.checkbox3.setOnCheckedChangeListener { _, b ->
            viewModel.hasHelperNurse.postValue(b)
        }
        binding.checkbox4.setOnCheckedChangeListener { _, b ->
            viewModel.transportSupported.postValue(b)
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.type.observe(viewLifecycleOwner) {
            binding.typeLayout.reset()
        }
        viewModel.date.observe(viewLifecycleOwner) {
            binding.dateField.reset()
        }
        viewModel.diagnose.observe(viewLifecycleOwner) {
            binding.reasonField.reset()
        }
        viewModel.process.observe(viewLifecycleOwner) {
            binding.processField.reset()
        }
        viewModel.nurse.observe(viewLifecycleOwner) {
            binding.nurseField.reset()
        }
        lifecycleScope.launch {
            viewModel.fieldError.collect {
                when (it.first) {
                    "date" -> binding.dateField.validate(requireContext(), it.second)
                    "nurse" -> binding.nurseField.validate(requireContext(), it.second)
                    "type" -> binding.typeField.validate(requireContext(), it.second)
                    "diagnose" -> binding.reasonField.validate(requireContext(), it.second)
                    "process" -> binding.processField.validate(requireContext(), it.second)
                    else -> {}
                }
            }
        }
    }

}