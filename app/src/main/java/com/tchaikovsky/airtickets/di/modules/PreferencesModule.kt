package com.tchaikovsky.airtickets.di.modules

import com.tchaikovsky.data.preferences.SearchPreferences
import com.tchaikovsky.data.preferences.SearchPreferencesImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface PreferencesModule {
    @Singleton
    @Binds
    fun preferences(preferences: SearchPreferencesImpl): SearchPreferences
}