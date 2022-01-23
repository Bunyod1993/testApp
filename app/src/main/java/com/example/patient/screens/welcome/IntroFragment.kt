package com.example.patient.screens.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.patient.R
import com.example.patient.adapters.WelcomePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class IntroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter =
            WelcomePagerAdapter(this,3)
        val digitalPager=requireActivity().findViewById<ViewPager2>(R.id.intro_pager)
        digitalPager.adapter = pagerAdapter
        val pagerTabLayout=requireActivity().findViewById<TabLayout>(R.id.into_tab_layout)
        TabLayoutMediator(pagerTabLayout, digitalPager)
        { tab, position ->

        }.attach()
    }

    companion object {}
}