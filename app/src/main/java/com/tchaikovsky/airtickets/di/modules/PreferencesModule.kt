package com.tchaikovsky.airtickets.di.modules

import com.tchaikovsky.airtickets.data.preferences.SearchPreferences
import com.tchaikovsky.airtickets.data.preferences.SearchPreferencesImpl
import com.tchaikovsky.airtickets.data.repository.AirTicketsRepositoryImpl
import com.tchaikovsky.airtickets.domain.repository.AirTicketsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface PreferencesModule {
    @Singleton
    @Binds
    fun preferences(preferences: SearchPreferencesImpl):  SearchPreferences
}