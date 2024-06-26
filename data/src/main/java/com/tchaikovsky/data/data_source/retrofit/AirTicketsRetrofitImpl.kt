package com.tchaikovsky.data.data_source.retrofit

import com.google.gson.GsonBuilder
import com.tchaikovsky.data.data_source.retrofit.AirTicketsRetrofitApi.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AirTicketsRetrofitImpl {
    fun getAirTicketsRetrofitApi(): AirTicketsRetrofitApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(AirTicketsRetrofitApi::class.java)
}