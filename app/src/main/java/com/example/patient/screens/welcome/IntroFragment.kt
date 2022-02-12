package com.example.patient.screens.welcome

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.patient.R
import com.example.patient.adapters.WelcomePagerAdapter
import com.example.patient.screens.MainActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class IntroFragment : Fragment() {
    private var currentItem = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = WelcomePagerAdapter(this, 3)
        val digitalPager = view.findViewById<ViewPager2>(R.id.intro_pager)
        digitalPager.adapter = pagerAdapter
        val pagerTabLayout = view.findViewById<TabLayout>(R.id.into_tab_layout)
        val skip=view.findViewById<TextView>(R.id.skip)
        skip.setOnClickListener {
            openMainActivity()
        }

        TabLayoutMediator(pagerTabLayout, digitalPager) { _, _ -> }.attach()

        pagerTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                currentItem = tab?.position ?: 0
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })


        val btn = view.findViewById<Button>(R.id.next)
        btn.setOnClickListener {
            currentItem = pagerTabLayout.selectedTabPosition
            when (currentItem) {
                0 -> {
                    digitalPager.setCurrentItem(1, true)
                }
                1 -> {
                    digitalPager.setCurrentItem(2, true)
                }
                2 -> {
                    openMainActivity()
                }
            }
        }
    }
    private fun openMainActivity(){
        val homeIntent = Intent(requireContext(), MainActivity::class.java)
        requireContext().startActivity(homeIntent)
        requireActivity().finish()
    }
}