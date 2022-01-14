package com.example.consultantalif.screens.home


import com.example.consultantalif.databinding.HomeFragmentBinding
import com.example.consultantalif.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    override fun getViewBinding() = HomeFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = HomeViewModel::class.java

    override fun observeData() {
        super.observeData()
        viewModel.getApi()
    }

}