package com.tchaikovsky.airtickets.data.data_source

import com.tchaikovsky.airtickets.domain.entity.offers.Offers
import com.tchaikovsky.airtickets.domain.entity.tickets.Tickets
import com.tchaikovsky.airtickets.domain.entity.tickets_offers.TicketsOffers

interface RemoteDataSource {
    suspend fun getOffers (): Offers
    suspend fun getTicketsOffers(): TicketsOffers
    suspend fun getTickets(): Tickets
}