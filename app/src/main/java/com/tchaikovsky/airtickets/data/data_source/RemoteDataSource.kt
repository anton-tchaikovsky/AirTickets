package com.tchaikovsky.airtickets.data.data_source

import com.tchaikovsky.domain.entity.offers.Offers
import com.tchaikovsky.domain.entity.tickets.Tickets
import com.tchaikovsky.domain.entity.tickets_offers.TicketsOffers

interface RemoteDataSource {
    suspend fun getOffers (): Offers
    suspend fun getTicketsOffers(): TicketsOffers
    suspend fun getTickets(): Tickets
}