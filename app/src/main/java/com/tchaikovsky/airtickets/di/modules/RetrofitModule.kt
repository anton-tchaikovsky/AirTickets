package com.tchaikovsky.airtickets.di.modules

import com.tchaikovsky.data.data_source.retrofit.AirTicketsRetrofitApi
import com.tchaikovsky.data.data_source.retrofit.AirTicketsRetrofitImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Singleton
    @Provides
    fun api(): AirTicketsRetrofitApi = AirTicketsRetrofitImpl().getAirTicketsRetrofitApi()
}