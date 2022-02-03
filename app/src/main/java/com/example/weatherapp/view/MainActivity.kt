package com.example.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.util.ViewState
import com.example.weatherapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
    }
    var showProgress: Boolean = true
        set(value) {
            field = value
            binding.progressCircular.isVisible = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {

        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initObserver()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.weather_list -> {
                navHostFragment.navController.navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun initViews() = with(binding){
        setSupportActionBar(binding.topBar)
        showProgress = false

    }

    private fun initObserver() = with(viewModel) {
        lifecycleScope.launchWhenCreated {
            viewState.observe(this@MainActivity) { state ->
                navHostFragment.navController.apply {
                    graph = navInflater.inflate(R.navigation.nav_graph).apply {
                        showProgress = when (state) {
                            is ViewState.Loading -> {
                                true
                            }
                            is ViewState.Error -> {
                                setStartDestination(R.id.list_fragment)
                                false
                            }
                            is ViewState.Success -> {
                                setStartDestination(R.id.weather_fragment)
                                false
                            }
                        }
                    }
                }
            }
        }
    }


}