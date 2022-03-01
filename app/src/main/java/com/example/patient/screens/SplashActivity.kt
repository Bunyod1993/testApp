package com.example.patient.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.patient.R
import com.example.patient.screens.welcome.WelcomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.firstAccess.observe(this) {
            if (it != null) {
                val homeIntent = if (it) {
                    Intent(this, WelcomeActivity::class.java)
                } else {
                    Intent(this, MainActivity::class.java)
                }
                lifecycleScope.launchWhenCreated {
                    delay(AUTO_HIDE_DELAY_MILLIS)
                    startHomeActivity(homeIntent)
                }
            }
        }
    }

    companion object {
        private const val AUTO_HIDE_DELAY_MILLIS = 1000L
    }

    private fun startHomeActivity(homeIntent: Intent) {
        startActivity(homeIntent)
        finish()
    }
}