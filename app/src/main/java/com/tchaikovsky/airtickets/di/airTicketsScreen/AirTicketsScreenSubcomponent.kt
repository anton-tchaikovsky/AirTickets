package com.tchaikovsky.airtickets.di.airTicketsScreen

import com.tchaikovsky.airtickets.di.modules.DataSourceModule
import com.tchaikovsky.airtickets.di.modules.RepositoryModule
import com.tchaikovsky.airtickets.di.modules.RetrofitModule
import com.tchaikovsky.airtickets.presentation.air_tickets.AirTicketsFragment
import com.tchaikovsky.airtickets.presentation.air_tickets.AirTicketsViewModelImpl
import com.tchaikovsky.airtickets.presentation.main_menu.MainMenuViewModelImpl
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