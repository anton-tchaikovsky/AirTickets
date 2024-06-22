package com.tchaikovsky.airtickets.data.data_source.retrofit

import com.tchaikovsky.airtickets.domain.entity.offers.Offers
import com.tchaikovsky.airtickets.domain.entity.tickets.Tickets
import com.tchaikovsky.airtickets.domain.entity.tickets_offers.TicketsOffers
import retrofit2.http.GET

interface AirTicketsRetrofitApi {

    @GET(OFFERS_END_POINT)
    suspend fun loadOffers(): Offers

    @GET(TICKETS_OFFERS_END_POINT)
    suspend fun loadTicketsOffers(): TicketsOffers

    @GET(TICKETS_END_POINT)
    suspend fun loadTickets(): Tickets


    companion object {
        private const val OFFERS_END_POINT = "/v3/ad9a46ba-276c-4a81-88a6-c068e51cce3a"
        private const val TICKETS_OFFERS_END_POINT = "/v3/38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a"
        private const val TICKETS_END_POINT = "/v3/c0464573-5a13-45c9-89f8-717436748b69"
        const val BASE_URL = "https://run.mocky.io"
    }
}

