package com.example.patient.screens.search

import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.patient.R
import com.example.patient.adapters.FilterAdapter
import com.example.patient.databinding.SearchFragmentBinding
import com.example.patient.screens.MainActivity
import com.example.patient.utils.base.BaseFragment
import com.example.patient.utils.ui.invisible
import com.example.patient.utils.ui.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment:  BaseFragment<SearchFragmentBinding, SearchViewModel>() {

    override fun getViewBinding() = SearchFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = SearchViewModel::class.java


    override fun setUpViews() {
        super.setUpViews()
        (activity as MainActivity).setSupportActionBar(binding.toolbar)

        val adapter=FilterAdapter(listOf("","",""))
        binding.recycleView.adapter=adapter
        adapter.notifyItemRangeChanged(0,0)
        binding.filter.setOnClickListener {
            if (binding.searchFilterWrapper.isVisible) closeFilter()
            else openFilter()
        }
        binding.accept.setOnClickListener {
            closeFilter()
        }
    }
    private fun openFilter(){
        binding.searchFilterWrapper.visible()
        binding.filterIcon.setBackgroundResource(R.drawable.ic_filter)
        binding.filterEndIcon.setBackgroundResource(R.drawable.ic_chevron_up)
        binding.filterText.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
    }
    private fun closeFilter(){
        binding.searchFilterWrapper.invisible()
        binding.filterIcon.setBackgroundResource(R.drawable.ic_filter_blured)
        binding.filterEndIcon.setBackgroundResource(R.drawable.ic_chevron_down)
        binding.filterText.setTextColor(ContextCompat.getColor(requireContext(), R.color.darkGrey))
    }

}