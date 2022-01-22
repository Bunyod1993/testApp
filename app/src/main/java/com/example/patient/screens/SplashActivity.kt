package com.example.patient.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.patient.R
import kotlinx.coroutines.delay
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
//    private val viewModel:SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val homeIntent = Intent(this, MainActivity::class.java)
//        viewModel.authToken.observe(this){
//            if (!it.isNullOrEmpty()){
//
//            }
//        }
        lifecycleScope.launchWhenCreated {
            delay(AUTO_HIDE_DELAY_MILLIS)
            startHomeActivity(homeIntent)
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