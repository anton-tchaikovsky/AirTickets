package com.tchaikovsky.airtickets.di.modules

import com.tchaikovsky.airtickets.presentation.main_menu.MainMenuViewModel
import com.tchaikovsky.airtickets.presentation.main_menu.MainMenuViewModelImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface MainMenuModule {
    @Singleton
    @Binds
    fun bindMainMenuViewModel(viewModel: MainMenuViewModelImpl): MainMenuViewModel
}