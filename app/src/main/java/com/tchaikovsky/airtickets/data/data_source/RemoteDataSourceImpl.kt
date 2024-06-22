package com.tchaikovsky.airtickets.data.data_source

import com.tchaikovsky.airtickets.data.data_source.retrofit.AirTicketsRetrofitApi
import com.tchaikovsky.airtickets.domain.entity.offers.Offers
import com.tchaikovsky.airtickets.domain.entity.tickets.Tickets
import com.tchaikovsky.airtickets.domain.entity.tickets_offers.TicketsOffers
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor (private val airTicketsRetrofitApi: AirTicketsRetrofitApi) :
    RemoteDataSource {
    override suspend fun getOffers(): Offers =
        airTicketsRetrofitApi.loadOffers()

    override suspend fun getTicketsOffers(): TicketsOffers =
        airTicketsRetrofitApi.loadTicketsOffers()

    override suspend fun getTickets(): Tickets = airTicketsRetrofitApi.loadTickets()
}