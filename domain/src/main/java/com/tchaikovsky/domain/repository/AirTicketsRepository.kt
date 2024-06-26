package com.tchaikovsky.domain.repository

import com.tchaikovsky.domain.entity.Popular
import com.tchaikovsky.domain.entity.offers.Offers
import com.tchaikovsky.domain.entity.tickets.Tickets
import com.tchaikovsky.domain.entity.tickets_offers.TicketsOffers

interface AirTicketsRepository {
    suspend fun getOffers(): Offers

    suspend fun getMockPopulars(): List<Popular>

    suspend fun getTicketsOffers(): TicketsOffers

    suspend fun getTickets(): Tickets

    fun readPreferences(): Pair<String?, String?>

    fun savePreferences(preferences: Pair<String?, String?>)
}