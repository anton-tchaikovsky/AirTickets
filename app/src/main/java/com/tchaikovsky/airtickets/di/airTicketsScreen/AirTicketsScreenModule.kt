package com.tchaikovsky.airtickets.di.airTicketsScreen

import com.tchaikovsky.airtickets.presentation.air_tickets.AirTicketsViewModel
import com.tchaikovsky.airtickets.presentation.air_tickets.AirTicketsViewModelImpl
import dagger.Binds
import dagger.Module

@Module
interface AirTicketsScreenModule {
    @Binds
    fun bindAirTicketsViewModel(viewModel: AirTicketsViewModelImpl): AirTicketsViewModel
}