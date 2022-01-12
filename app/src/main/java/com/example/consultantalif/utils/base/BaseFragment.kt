package com.example.consultantalif.utils.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.example.consultantalif.R

abstract class BaseFragment<VBinding:ViewBinding,VM:BaseViewModel>:Fragment() {
    open var useSharedViewModel: Boolean = false

    protected lateinit var viewModel:VM
    protected abstract fun getViewModelClass(): Class<VM>

    protected lateinit var binding: VBinding
    protected abstract fun getViewBinding(): VBinding
    private fun init(){
        binding = getViewBinding()
        viewModel = if (useSharedViewModel) {
            ViewModelProvider(requireActivity())[getViewModelClass()]
        } else {
            ViewModelProvider(this)[getViewModelClass()]
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeData()
    }
    open fun setUpViews() {}

    open fun observeView() {}

    open fun observeData() {
        viewModel.mutableErrorType.observe(viewLifecycleOwner,  {
            if (it==ErrorType.NETWORK) Navigation.findNavController(requireView()).navigate(R.id.action_global_toLogin)
            Log.v("tag","$it")
        })
    }
}