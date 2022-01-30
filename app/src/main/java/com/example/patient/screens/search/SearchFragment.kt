package com.example.patient.screens.search

import com.example.patient.databinding.SearchFragmentBinding
import com.example.patient.utils.base.BaseFragment

class SearchFragment:  BaseFragment<SearchFragmentBinding, SearchViewModel>() {

    override fun getViewBinding() = SearchFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = SearchViewModel::class.java


}