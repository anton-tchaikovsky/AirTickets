package com.tchaikovsky.airtickets.di

import android.content.Context
import com.tchaikovsky.airtickets.AirTicketsApp
import com.tchaikovsky.airtickets.di.airTicketsScreen.AirTicketsScreenSubcomponent
import com.tchaikovsky.airtickets.di.modules.DataSourceModule
import com.tchaikovsky.airtickets.di.modules.MainMenuModule
import com.tchaikovsky.airtickets.di.modules.PreferencesModule
import com.tchaikovsky.airtickets.di.modules.RepositoryModule
import com.tchaikovsky.airtickets.di.modules.RetrofitModule
import com.tchaikovsky.airtickets.presentation.main_menu.MainMenuViewModelImpl
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RetrofitModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        MainMenuModule::class,
        PreferencesModule::class
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

    fun airTicketsScreenSubcomponent(): AirTicketsScreenSubcomponent

    fun inject(app: AirTicketsApp)
}