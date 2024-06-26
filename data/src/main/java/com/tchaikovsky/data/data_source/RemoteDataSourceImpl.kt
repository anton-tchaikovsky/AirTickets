package com.tchaikovsky.data.data_source

import com.tchaikovsky.data.data_source.retrofit.AirTicketsRetrofitApi
import com.tchaikovsky.domain.entity.offers.Offers
import com.tchaikovsky.domain.entity.tickets.Tickets
import com.tchaikovsky.domain.entity.tickets_offers.TicketsOffers
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor (private val airTicketsRetrofitApi: AirTicketsRetrofitApi) :
    RemoteDataSource {
    override suspend fun getOffers(): Offers =
        airTicketsRetrofitApi.loadOffers()

    override suspend fun getTicketsOffers(): TicketsOffers =
        airTicketsRetrofitApi.loadTicketsOffers()

    override suspend fun getTickets(): Tickets = airTicketsRetrofitApi.loadTickets()
}