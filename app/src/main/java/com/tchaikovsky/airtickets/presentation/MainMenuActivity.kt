package com.tchaikovsky.airtickets.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tchaikovsky.airtickets.AirTicketsApp
import com.tchaikovsky.airtickets.R
import com.tchaikovsky.airtickets.databinding.ActivityMainMenuBinding
import com.tchaikovsky.airtickets.utility.viewModelProviderFactoryOf

class MainMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    private val viewModel: MainMenuViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactoryOf {
            AirTicketsApp.instance.appComponent.mainMenuViewModelImpl
        })[MainMenuViewModelImpl::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigationMenu()
        viewModel.getScreenLiveData().observe(this) {
            renderMainMenuScreen(it)
        }
    }

    private fun initNavigationMenu() {
        binding.mainMenuNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tickets -> {
                    viewModel.onClickItemMainMenu(MainMenuScreen.TICKETS)
                    true
                }

                R.id.hotels -> {
                    viewModel.onClickItemMainMenu(MainMenuScreen.HOTELS)
                    true
                }

                R.id.subscription -> {
                    viewModel.onClickItemMainMenu(MainMenuScreen.SUBSCRIPTION)
                    true
                }

                R.id.profile -> {
                    viewModel.onClickItemMainMenu(MainMenuScreen.PROFILE)
                    true
                }

                R.id.short_way -> {
                    viewModel.onClickItemMainMenu(MainMenuScreen.SHORT_WAY)
                    true
                }

                else -> false
            }
        }
    }

    private fun renderMainMenuScreen(mainMenuScreen: MainMenuScreen) {
        when (mainMenuScreen) {
            MainMenuScreen.TICKETS -> Toast.makeText(
                this,
                mainMenuScreen.toString(),
                Toast.LENGTH_SHORT
            ).show()

            MainMenuScreen.HOTELS -> Toast.makeText(
                this,
                mainMenuScreen.toString(),
                Toast.LENGTH_SHORT
            ).show()

            MainMenuScreen.SHORT_WAY -> Toast.makeText(
                this,
                mainMenuScreen.toString(),
                Toast.LENGTH_SHORT
            ).show()

            MainMenuScreen.SUBSCRIPTION -> Toast.makeText(
                this,
                mainMenuScreen.toString(),
                Toast.LENGTH_SHORT
            ).show()

            MainMenuScreen.PROFILE -> Toast.makeText(
                this,
                mainMenuScreen.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}