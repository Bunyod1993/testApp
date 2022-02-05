package com.example.patient.screens.login


import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.patient.R
import com.example.patient.databinding.LoginFragmentBinding
import com.example.patient.utils.base.BaseFragment
import com.example.patient.utils.enums.InputErrorType
import com.example.patient.utils.enums.InputType
import com.example.patient.utils.ui.textChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>() {

    override fun getViewBinding() = LoginFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = LoginViewModel::class.java

    override fun observeData() {
        super.observeData()

        viewModel.token.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty())
                Navigation.findNavController(requireView()).navigate(R.id.action_loginToHome)
        }

        viewModel.fieldError.observe(viewLifecycleOwner) {
            if (it.first == InputType.EMAIL) {

                when (it.second) {
                    InputErrorType.EMPTY -> {}
                    InputErrorType.MISMATCH -> {
                        binding.emailAddress.isErrorEnabled = true
                        binding.emailAddress.error = getString(R.string.error_fill_correctly)
                    }
                    InputErrorType.VALID -> {
                        binding.emailAddress.isErrorEnabled = false
                        binding.emailAddress.error = ""
                    }
                    else -> {}
                }
            } else {
                when (it.second) {
                    InputErrorType.EMPTY -> {}
                    InputErrorType.MISMATCH -> {}
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

        binding.emailField.textChanges().debounce(500).onEach {
            it?.let {
                viewModel.validateLoginFields(InputType.EMAIL)
            }
        }.launchIn(lifecycleScope)

        binding.passwordField.textChanges().debounce(500).onEach {
            it?.let {
                viewModel.validateLoginFields(InputType.PASSWORD)
            }
        }.launchIn(lifecycleScope)

        viewModel.email.observe(viewLifecycleOwner) {
            binding.emailAddress.isErrorEnabled = false
            binding.emailAddress.error = ""
        }

        viewModel.password.observe(viewLifecycleOwner) {
            binding.password.isErrorEnabled = false
            binding.password.error = ""
        }

        viewModel.isLogin.observe(viewLifecycleOwner) {
//            binding.login.isEnabled = it
            binding.login.isEnabled = true
        }

    }

    override fun setUpViews() {
        super.setUpViews()
        binding.loginModel = viewModel
        binding.login.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_loginToHome)

//            viewModel.login()
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