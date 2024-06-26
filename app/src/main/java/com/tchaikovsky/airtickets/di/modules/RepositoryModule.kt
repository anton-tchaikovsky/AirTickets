package com.tchaikovsky.airtickets.di.modules

import com.tchaikovsky.data.repository.AirTicketsRepositoryImpl
import com.tchaikovsky.domain.repository.AirTicketsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun repository(repository: AirTicketsRepositoryImpl): AirTicketsRepository
}