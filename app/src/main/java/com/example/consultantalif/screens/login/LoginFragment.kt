package com.example.consultantalif.screens.login


import androidx.navigation.Navigation
import com.example.consultantalif.R
import com.example.consultantalif.databinding.LoginFragmentBinding
import com.example.consultantalif.repositories.Resource
import com.example.consultantalif.utils.base.BaseFragment
import com.example.consultantalif.utils.enums.InputErrorType
import com.example.consultantalif.utils.enums.InputType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>() {

    override fun getViewBinding() = LoginFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = LoginViewModel::class.java

    override fun observeData() {
        super.observeData()

        viewModel.token.observe(viewLifecycleOwner) {
            if (it is Resource.Success)
                Navigation.findNavController(requireView()).navigate(R.id.action_loginToHome)
        }

        viewModel.fieldError.observe(viewLifecycleOwner) {
            if (it.first == InputType.EMAIL) {
                binding.emailAddress.isErrorEnabled = false
                binding.emailAddress.error = ""
                when (it.second) {
                    InputErrorType.EMPTY -> {}
                    InputErrorType.MISMATCH -> {
                        binding.emailAddress.isErrorEnabled = true
                        binding.emailAddress.error = getString(R.string.error_fill_correctly)
                    }
                    InputErrorType.VALID -> {}
                    else -> {}
                }
            } else {
                binding.password.isErrorEnabled = false
                binding.password.error = ""
                when (it.second) {
                    InputErrorType.EMPTY -> {}
                    InputErrorType.MISMATCH -> {}
                    InputErrorType.INVALID -> {
                        binding.password.isErrorEnabled = true
                        binding.password.error = getString(R.string.error_fill_correctly)
                    }
                    InputErrorType.VALID -> {}
                }
            }
        }

        viewModel.email.observe(viewLifecycleOwner) {
          viewModel.validateLoginFields(InputType.EMAIL)
        }

        viewModel.password.observe(viewLifecycleOwner) {
            viewModel.validateLoginFields(InputType.PASSWORD)
        }
        viewModel.isLogin.observe(viewLifecycleOwner) {
            binding.login.isEnabled = it
        }

    }

    override fun setUpViews() {
        super.setUpViews()
        binding.loginModel = viewModel
        binding.login.setOnClickListener {
            viewModel.login()
        }
//        binding.emailField.setOnFocusChangeListener { _, hasFocus ->
//            if (!hasFocus)
//                viewModel.validateLoginFields(InputType.EMAIL)
//        }
//        binding.passwordField.setOnFocusChangeListener { _, hasFocus ->
//            if (!hasFocus)
//                viewModel.validateLoginFields(InputType.PASSWORD)
//        }
    }

}