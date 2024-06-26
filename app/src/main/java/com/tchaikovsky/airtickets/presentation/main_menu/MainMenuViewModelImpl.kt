package com.tchaikovsky.airtickets.presentation.main_menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainMenuViewModelImpl @Inject constructor() : MainMenuViewModel, ViewModel() {

    private val mainMenuItemLivData = MutableLiveData<MainMenuItem>()

    override fun getScreenLiveData(): LiveData<MainMenuItem> = mainMenuItemLivData

    override fun onClickItemMainMenu(itemMainMenu: MainMenuItem) {
        mainMenuItemLivData.value = itemMainMenu
    }
}