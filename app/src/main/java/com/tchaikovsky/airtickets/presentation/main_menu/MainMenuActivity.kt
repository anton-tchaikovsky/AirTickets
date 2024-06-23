package com.tchaikovsky.airtickets.presentation.main_menu

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tchaikovsky.airtickets.AirTicketsApp
import com.tchaikovsky.airtickets.R
import com.tchaikovsky.airtickets.databinding.ActivityMainMenuBinding
import com.tchaikovsky.airtickets.presentation.air_tickets.AirTicketsFragment
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
        openAirTicketsFragment(savedInstanceState)
        viewModel.getScreenLiveData().observe(this) {
            renderMainMenuScreen(it)
        }
    }

    private fun initNavigationMenu() {
        binding.mainMenuNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tickets -> {
                    viewModel.onClickItemMainMenu(MainMenuItem.TICKETS)
                    true
                }

                R.id.hotels -> {
                    viewModel.onClickItemMainMenu(MainMenuItem.HOTELS)
                    true
                }

                R.id.subscription -> {
                    viewModel.onClickItemMainMenu(MainMenuItem.SUBSCRIPTION)
                    true
                }

                R.id.profile -> {
                    viewModel.onClickItemMainMenu(MainMenuItem.PROFILE)
                    true
                }

                R.id.short_way -> {
                    viewModel.onClickItemMainMenu(MainMenuItem.SHORT_WAY)
                    true
                }

                else -> false
            }
        }
    }

    private fun renderMainMenuScreen(mainMenuItem: MainMenuItem) {
        when (mainMenuItem) {
            MainMenuItem.TICKETS -> Toast.makeText(
                this,
                mainMenuItem.toString(),
                Toast.LENGTH_SHORT
            ).show()

            MainMenuItem.HOTELS -> Toast.makeText(
                this,
                mainMenuItem.toString(),
                Toast.LENGTH_SHORT
            ).show()

            MainMenuItem.SHORT_WAY -> Toast.makeText(
                this,
                mainMenuItem.toString(),
                Toast.LENGTH_SHORT
            ).show()

            MainMenuItem.SUBSCRIPTION -> Toast.makeText(
                this,
                mainMenuItem.toString(),
                Toast.LENGTH_SHORT
            ).show()

            MainMenuItem.PROFILE -> Toast.makeText(
                this,
                mainMenuItem.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun openAirTicketsFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragments_container,
                    AirTicketsFragment.newInstance(),
                    AirTicketsFragment.AIR_TICKETS_FRAGMENT_TAG
                )
                .commitAllowingStateLoss()
    }
}