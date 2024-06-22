package com.tchaikovsky.airtickets.presentation

import androidx.lifecycle.LiveData

interface MainMenuViewModel {
    fun getScreenLiveData(): LiveData<MainMenuScreen>

    fun onClickItemMainMenu(itemMainMenu: MainMenuScreen)
}