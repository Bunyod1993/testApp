package com.example.patient.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.patient.R
import com.example.patient.databinding.ActivityMainBinding
import com.example.patient.utils.ui.invisible
import com.example.patient.utils.ui.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(){
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
        // Set up ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        viewModel.authToken.observe(this) {
            if (it.isNullOrEmpty()){
                navController.navigate(R.id.action_global_toLogin)
            }
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.homeFragment,R.id.loginFragment,R.id.localPatientsFragment -> {
                    binding.bottomNavigation.invisible()
                }
                else-> {
                    binding.bottomNavigation.visible()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}