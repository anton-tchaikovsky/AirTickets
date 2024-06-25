package com.tchaikovsky.airtickets.presentation.main_menu

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.tchaikovsky.airtickets.AirTicketsApp
import com.tchaikovsky.airtickets.R
import com.tchaikovsky.airtickets.databinding.ActivityMainMenuBinding
import com.tchaikovsky.airtickets.presentation.air_tickets.AirTicketsFragment
import com.tchaikovsky.airtickets.presentation.mock.MockFragment
import com.tchaikovsky.airtickets.presentation.search_tickets.SearchTicketsFragment
import com.tchaikovsky.airtickets.utility.viewModelProviderFactoryOf

class MainMenuActivity : AppCompatActivity(), PreferencesListener, RemoveSearchTicketsFragmentListener {

    private lateinit var binding: ActivityMainMenuBinding

    private val viewModel: MainMenuViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactoryOf {
            AirTicketsApp.instance.appComponent.mainMenuViewModelImpl
        })[MainMenuViewModelImpl::class.java]
    }

    private lateinit var searchBottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigationMenu()
        initSearchBottomSheetDialogFragment()
        openAirTicketsFragment(savedInstanceState)
        viewModel.getScreenLiveData().observe(this) {
            renderMainMenuScreen(it)
        }
    }

    override fun setPreferences(preferencesWhereFrom: String?, preferencesWhere: String?) {
        searchBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        searchBottomSheetBehavior.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    with(supportFragmentManager){
                        findFragmentByTag(SearchTicketsFragment.TAG_SEARCH_TICKETS_FRAGMENT)?.let {
                            beginTransaction().remove(it).commit()
                        }
                    }
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.search_tickets_container,
                SearchTicketsFragment.newInstance(preferencesWhereFrom, preferencesWhere),
                SearchTicketsFragment.TAG_SEARCH_TICKETS_FRAGMENT
            )
            .commitAllowingStateLoss()
    }

    override fun onRemoveFragment() {
        searchBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun initSearchBottomSheetDialogFragment() {
        searchBottomSheetBehavior = BottomSheetBehavior.from(binding.searchBottomSheet)
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
        binding.mainMenuNavigationView.setOnItemReselectedListener {
            // двойной клик не обрабатываем
        }
    }

    private fun renderMainMenuScreen(mainMenuItem: MainMenuItem) {
        when (mainMenuItem) {
            MainMenuItem.TICKETS -> Toast.makeText(
                this,
                mainMenuItem.toString(),
                Toast.LENGTH_SHORT
            ).show()

            MainMenuItem.HOTELS -> openMockFragment(MockFragment.TAG_HOTELS)

            MainMenuItem.SHORT_WAY -> openMockFragment(MockFragment.TAG_SHORT_WAY)

            MainMenuItem.SUBSCRIPTION -> openMockFragment(MockFragment.TAG_SUBSCRIPTION)

            MainMenuItem.PROFILE -> openMockFragment(MockFragment.TAG_PROFILE)
        }
    }

    private fun openAirTicketsFragment(savedInstanceState: Bundle?) {
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

    private fun openMockFragment(tag: String){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragments_container, MockFragment.newInstance(), tag)
            .addToBackStack("")
            .commitAllowingStateLoss()
    }
}