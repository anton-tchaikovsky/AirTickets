package com.tchaikovsky.airtickets.di.modules

import com.tchaikovsky.airtickets.presentation.main_menu.MainMenuViewModel
import com.tchaikovsky.airtickets.presentation.main_menu.MainMenuViewModelImpl
import com.tchaikovsky.airtickets.presentation.search_tickets.SearchTicketsViewModel
import com.tchaikovsky.airtickets.presentation.search_tickets.SearchTicketsViewModelImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface MainMenuModule {
    @Singleton
    @Binds
    fun bindMainMenuViewModel(viewModel: MainMenuViewModelImpl): MainMenuViewModel

    @Singleton
    @Binds
    fun bindSearchTicketsModel(viewModel: SearchTicketsViewModelImpl): SearchTicketsViewModel
}