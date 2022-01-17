package com.example.consultantalif.screens.home


import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.Navigation
import com.example.consultantalif.R
import com.example.consultantalif.databinding.HomeFragmentBinding
import com.example.consultantalif.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    override fun getViewBinding() = HomeFragmentBinding.inflate(layoutInflater)
    override fun getViewModelClass() = HomeViewModel::class.java
    private lateinit var timer: CountDownTimer

    override fun observeData() {
        super.observeData()

        val endIcon: Drawable? = ResourcesCompat.getDrawable(context!!.resources,
                                R.drawable.ic_refresh, null)

        timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                binding.otpFieldLayout.endIconDrawable=endIcon
            }
        }


    }

    override fun setUpViews() {
        super.setUpViews()
//        could be done dynamic placeholder
//        binding.otpFieldLayout.placeholderText=""
        binding.sendOtp.setOnClickListener {
//            startTimer()
            Navigation.findNavController(it).navigate(R.id.action_homeToScanPassport)
        }

        binding.otpFieldLayout.setEndIconOnClickListener {
            startTimer()
        }
    }

    private fun startTimer() {
      timer.start()
      binding.otpFieldLayout.endIconDrawable=null
    }
}