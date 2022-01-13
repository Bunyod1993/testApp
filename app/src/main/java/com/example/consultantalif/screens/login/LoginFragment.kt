package com.example.consultantalif.screens.login


import android.util.Log
import androidx.navigation.Navigation
import com.example.consultantalif.R
import com.example.consultantalif.databinding.LoginFragmentBinding
import com.example.consultantalif.repositories.Resource
import com.example.consultantalif.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding,LoginViewModel>() {

    override fun getViewBinding() = LoginFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = LoginViewModel::class.java

    override fun observeData() {
        super.observeData()
        viewModel.login()
        viewModel.token.observe(viewLifecycleOwner){
            Log.v("tag",it.data?:"")
//            if (it is Resource.Success)
//                Navigation.findNavController(requireView()).navigate(R.id.action_loginToHome)
        }
    }


}