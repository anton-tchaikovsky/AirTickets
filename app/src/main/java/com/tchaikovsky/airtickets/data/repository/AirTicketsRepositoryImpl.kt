package com.tchaikovsky.airtickets.data.repository

import com.tchaikovsky.airtickets.data.data_source.RemoteDataSource
import com.tchaikovsky.airtickets.data.preferences.SearchPreferences
import com.tchaikovsky.airtickets.domain.entity.offers.Offers
import com.tchaikovsky.airtickets.domain.entity.tickets.Tickets
import com.tchaikovsky.airtickets.domain.entity.tickets_offers.TicketsOffers
import com.tchaikovsky.airtickets.domain.repository.AirTicketsRepository
import javax.inject.Inject

class AirTicketsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val sharedPreferences: SearchPreferences
) :
    AirTicketsRepository {
    override suspend fun getOffers(): Offers = remoteDataSource.getOffers()

    override suspend fun getTicketsOffers(): TicketsOffers =
        remoteDataSource.getTicketsOffers()

    override suspend fun getTickets(): Tickets = remoteDataSource.getTickets()

    override fun readPreferences(): Pair<String?, String?> =
        sharedPreferences.run {
            readWhereFrom() to readWhere()
        }

    override fun savePreferences(preferences: Pair<String?, String?>) {
        with(sharedPreferences) {
            saveWhereFrom(preferences.first)
            saveWhere(preferences.second)
        }
    }
}