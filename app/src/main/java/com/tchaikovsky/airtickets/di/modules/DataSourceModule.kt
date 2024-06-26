package com.tchaikovsky.airtickets.di.modules

import com.tchaikovsky.data.data_source.RemoteDataSource
import com.tchaikovsky.data.data_source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataSourceModule {
    @Singleton
    @Binds
    fun remoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}