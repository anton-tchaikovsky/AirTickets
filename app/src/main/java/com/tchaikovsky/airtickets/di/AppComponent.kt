package com.tchaikovsky.airtickets.di

import android.content.Context
import com.tchaikovsky.airtickets.AirTicketsApp
import com.tchaikovsky.airtickets.di.airTicketsScreen.AirTicketsScreenSubcomponent
import com.tchaikovsky.airtickets.di.modules.DataSourceModule
import com.tchaikovsky.airtickets.di.modules.PreferencesModule
import com.tchaikovsky.airtickets.di.modules.RepositoryModule
import com.tchaikovsky.airtickets.di.modules.ResourcesProviderModule
import com.tchaikovsky.airtickets.di.modules.RetrofitModule
import com.tchaikovsky.airtickets.presentation.main_menu.MainMenuViewModelImpl
import com.tchaikovsky.airtickets.presentation.search_tickets.SearchTicketsViewModelImpl
import com.tchaikovsky.airtickets.presentation.selected_town.SelectedTownViewModelImpl
import com.tchaikovsky.airtickets.presentation.view_all_tickets.ViewAllTicketsViewModelImpl
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RetrofitModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        PreferencesModule::class,
        ResourcesProviderModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): AppComponent
    }

    val mainMenuViewModelImpl: MainMenuViewModelImpl

    val searchTicketsViewModelImpl: SearchTicketsViewModelImpl

    val selectedTownViewModelImpl: SelectedTownViewModelImpl

    val viewAllTicketsViewModelImpl: ViewAllTicketsViewModelImpl

    //реализован в целях практики
    fun airTicketsScreenSubcomponent(): AirTicketsScreenSubcomponent

    fun inject(app: AirTicketsApp)
}