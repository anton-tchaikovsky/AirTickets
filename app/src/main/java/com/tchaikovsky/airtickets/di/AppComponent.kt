package com.tchaikovsky.airtickets.di

import android.content.Context
import com.tchaikovsky.airtickets.AirTicketsApp
import com.tchaikovsky.airtickets.di.modules.DataSourceModule
import com.tchaikovsky.airtickets.di.modules.MainMenuModule
import com.tchaikovsky.airtickets.di.modules.RepositoryModule
import com.tchaikovsky.airtickets.di.modules.RetrofitModule
import com.tchaikovsky.airtickets.presentation.MainMenuViewModelImpl
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RetrofitModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        MainMenuModule::class
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

    fun inject(app: AirTicketsApp)
}