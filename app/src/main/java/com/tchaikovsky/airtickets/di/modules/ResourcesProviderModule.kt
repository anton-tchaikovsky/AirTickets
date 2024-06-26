package com.tchaikovsky.airtickets.di.modules

import com.tchaikovsky.airtickets.resurce_provider.ResourcesProvider
import com.tchaikovsky.airtickets.resurce_provider.ResourcesProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ResourcesProviderModule {

    @Singleton
    @Binds
    fun bindResourcesProvider(provider: ResourcesProviderImpl): ResourcesProvider
}