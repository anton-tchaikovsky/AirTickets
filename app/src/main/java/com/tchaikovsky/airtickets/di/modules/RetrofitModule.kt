package com.tchaikovsky.airtickets.di.modules

import com.tchaikovsky.airtickets.data.data_source.retrofit.AirTicketsRetrofitApi
import com.tchaikovsky.airtickets.data.data_source.retrofit.AirTicketsRetrofitImpl
import com.tchaikovsky.airtickets.data.repository.AirTicketsRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Singleton
    @Provides
    fun api(): AirTicketsRetrofitApi = AirTicketsRetrofitImpl().getAirTicketsRetrofitApi()
}