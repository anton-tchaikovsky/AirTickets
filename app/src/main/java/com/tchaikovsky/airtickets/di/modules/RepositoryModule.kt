package com.tchaikovsky.airtickets.di.modules

import com.tchaikovsky.airtickets.data.repository.AirTicketsRepositoryImpl
import com.tchaikovsky.airtickets.domain.repository.AirTicketsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun repository(repository: AirTicketsRepositoryImpl): AirTicketsRepository
}