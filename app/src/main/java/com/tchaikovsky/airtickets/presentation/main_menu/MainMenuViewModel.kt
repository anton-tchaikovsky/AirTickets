package com.tchaikovsky.airtickets.presentation.main_menu

import androidx.lifecycle.LiveData

interface MainMenuViewModel {
    fun getScreenLiveData(): LiveData<MainMenuItem>

    fun onClickItemMainMenu(itemMainMenu: MainMenuItem)
}