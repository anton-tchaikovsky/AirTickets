package com.tchaikovsky.airtickets.di.modules

import com.tchaikovsky.airtickets.presentation.MainMenuViewModel
import com.tchaikovsky.airtickets.presentation.MainMenuViewModelImpl
import dagger.Binds
import dagger.Module

@Module
interface MainMenuModule {
    @Binds
    fun bindMainMenuViewModel(viewModel: MainMenuViewModelImpl): MainMenuViewModel
}