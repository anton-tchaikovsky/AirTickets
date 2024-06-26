package com.tchaikovsky.airtickets.di.airTicketsScreen

import com.tchaikovsky.airtickets.presentation.air_tickets.AirTicketsFragment
import com.tchaikovsky.airtickets.presentation.air_tickets.AirTicketsViewModelImpl
import dagger.Subcomponent

@AirTicketsScreenScope
@Subcomponent(
    modules = [
        AirTicketsScreenModule::class
    ]
)
interface AirTicketsScreenSubcomponent {
    val airTicketsViewModelImpl: AirTicketsViewModelImpl

    fun inject(airTicketsFragment: AirTicketsFragment)
}