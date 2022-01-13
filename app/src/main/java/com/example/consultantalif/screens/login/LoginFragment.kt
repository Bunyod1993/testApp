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
                when (it.second) {
                    InputErrorType.EMPTY -> {
                        binding.emailAddress.isErrorEnabled = false
                    }
                    InputErrorType.MISMATCH -> {
                        binding.emailAddress.isErrorEnabled = true
                        binding.emailAddress.error = getString(R.string.error_fill_correctly)
                    }
                    InputErrorType.VALID -> {
                        binding.emailAddress.isErrorEnabled = false
                        binding.emailAddress.error = ""
                    }
                    else -> {
                        binding.emailAddress.isErrorEnabled = false
                        binding.emailAddress.error = ""
                    }
                }
            } else {
                when (it.second) {
                    InputErrorType.EMPTY -> {
                        binding.password.isErrorEnabled = false
                    }
                    InputErrorType.MISMATCH -> {
                        binding.password.isErrorEnabled = true
                        binding.password.error = getString(R.string.error_fill_correctly)
                    }
                    InputErrorType.INVALID -> {
                        binding.password.isErrorEnabled = true
                        binding.password.error = getString(R.string.error_fill_correctly)
                    }
                    InputErrorType.VALID -> {
                        binding.password.isErrorEnabled = false
                        binding.password.error = ""
                    }
                }
            }
        }

        viewModel.email.observe(viewLifecycleOwner) {
            binding.emailAddress.isErrorEnabled = false
            binding.emailAddress.error = ""
        }

        viewModel.password.observe(viewLifecycleOwner) {
            binding.password.isErrorEnabled = false
            binding.password.error = ""
        }

    }

    override fun setUpViews() {
        super.setUpViews()
        binding.loginModel = viewModel
        binding.login.setOnClickListener {
            if (viewModel.isLogin.value!!)
                viewModel.login()
            else {
                viewModel.validateLoginFields(InputType.EMAIL)
                viewModel.validateLoginFields(InputType.PASSWORD)
            }
        }
        binding.emailField.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus)
                viewModel.validateLoginFields(InputType.EMAIL)
        }
        binding.passwordField.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus)
                viewModel.validateLoginFields(InputType.PASSWORD)
        }
    }

}