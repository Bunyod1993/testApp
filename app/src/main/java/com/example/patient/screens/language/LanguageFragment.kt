package com.example.patient.screens.language


import android.content.res.Configuration
import android.content.res.Resources
import android.util.DisplayMetrics
import com.example.patient.R
import com.example.patient.databinding.LanguageFragmentBinding
import com.example.patient.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class LanguageFragment : BaseFragment<LanguageFragmentBinding,LanguageViewModel>() {
    override fun getViewModelClass()=LanguageViewModel::class.java

    override fun getViewBinding()=LanguageFragmentBinding.inflate(layoutInflater)

    override fun observeView() {
        super.observeView()
    }

    override fun setUpViews() {
        super.setUpViews()
        binding.russian.setOnClickListener {
            changeLanguage("ru")
            binding.russianChecker.setBackgroundResource(R.drawable.ic_vector_check)
            binding.tajikChecker.setBackgroundResource(R.color.transparent)

        }
        binding.tajik.setOnClickListener {
            changeLanguage("tg")
            binding.russianChecker.setBackgroundResource(R.color.transparent)
            binding.tajikChecker.setBackgroundResource(R.drawable.ic_vector_check)
        }
    }
    private fun changeLanguage(lang:String){
        val res: Resources = requireContext().resources
// Change locale settings in the app.
// Change locale settings in the app.
        val dm: DisplayMetrics = res.displayMetrics
        val conf: Configuration = res.configuration
        conf.setLocale(Locale(lang)) // API 17+ only.

// Use conf.locale = new Locale(...) if targeting lower versions
// Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm)
    }

}