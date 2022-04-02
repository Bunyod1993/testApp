package com.example.patient.screens.details


import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.patient.R
import com.example.patient.databinding.DetailFragmentBinding
import com.example.patient.repositories.register.Form2
import com.example.patient.repositories.register.Register
import com.example.patient.screens.MainActivity
import com.example.patient.utils.base.BaseFragment
import com.example.patient.utils.ui.deNormalize
import com.example.patient.utils.ui.invisible

class DetailFragment : BaseFragment<DetailFragmentBinding, DetailViewModel>() {
    override fun getViewModelClass() = DetailViewModel::class.java

    override fun getViewBinding() = DetailFragmentBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.title = ""
        val arg = arguments?.get("reg")
        val code = arguments?.getString("code","")?:""
        arg?.let {
            val register = arg as Register
            binding.addressText.text = register.address
            binding.passportText.text = register.passport
            binding.fioText.text = register.fio
            binding.phoneText.text = register.phone
            binding.dateText.text = register.birthday.deNormalize()
            if (code.isEmpty()){
                binding.idText.invisible()
                binding.idLabel.invisible()
            }
            binding.idText.text = code
            val bundle = bundleOf()
            bundle.putString("code", code)
            bundle.putParcelable("reg",register)
            binding.registeredPLace.setOnClickListener {
                navigate(R.id.action_toRegisterFragment, bundle)
            }
            binding.emergencyPLace.setOnClickListener {
                navigate(R.id.action_toEmergencyFragment, bundle)
            }

            binding.reversePLace.setOnClickListener {
                navigate(R.id.action_toReverseRegisterFragment, bundle)
            }

            binding.registeredBeforeBirthPLace.setOnClickListener {
                val form = Form2()
                bundle.putParcelable("form", form)
                navigate(R.id.action_toBeforeBirthRegisterFragment, bundle)
            }

            binding.deathPLace.setOnClickListener {
                navigate(R.id.action_toRegisterDeathFragment, bundle)
            }

        }


    }

    private fun navigate(id: Int, bundle: Bundle = bundleOf()) {
        Navigation.findNavController(requireView()).navigate(id, bundle)
    }

}