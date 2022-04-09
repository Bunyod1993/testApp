package com.example.patient.screens.welcome

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.view.WindowCompat
import com.example.patient.R
import com.example.patient.adapters.WelcomePagerAdapter
import com.example.patient.databinding.ActivityMainBinding
import com.example.patient.databinding.ActivityWelcomeBinding
import com.example.patient.utils.Constants
import com.example.patient.utils.LocaleManager
import com.google.android.material.tabs.TabLayoutMediator

class WelcomeActivity : AppCompatActivity() {
    private val binding: ActivityWelcomeBinding by lazy { ActivityWelcomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val prefs=getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(Constants.FIRST_ACCESS, false).apply()
    }
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { LocaleManager.setLocale(it) })
    }
}