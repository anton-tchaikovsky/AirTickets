package com.tchaikovsky.airtickets.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainMenuViewModelImpl @Inject constructor() : MainMenuViewModel, ViewModel() {

    private val mainMenuScreenLivData = MutableLiveData<MainMenuScreen>()

    init{
        mainMenuScreenLivData.value = MainMenuScreen.TICKETS
    }

    override fun getScreenLiveData(): LiveData<MainMenuScreen> = mainMenuScreenLivData

    override fun onClickItemMainMenu(itemMainMenu: MainMenuScreen) {
        mainMenuScreenLivData.value = itemMainMenu
    }
}