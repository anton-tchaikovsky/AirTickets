package com.tchaikovsky.data.repository

import com.tchaikovsky.data.data_source.RemoteDataSource
import com.tchaikovsky.data.preferences.SearchPreferences
import com.tchaikovsky.domain.entity.Popular
import com.tchaikovsky.domain.entity.offers.Offers
import com.tchaikovsky.domain.entity.tickets.Tickets
import com.tchaikovsky.domain.entity.tickets_offers.TicketsOffers
import com.tchaikovsky.domain.repository.AirTicketsRepository
import javax.inject.Inject

class AirTicketsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val sharedPreferences: SearchPreferences
) :
    AirTicketsRepository {
    override suspend fun getOffers(): Offers = remoteDataSource.getOffers()

    override suspend fun getMockPopulars(): List<Popular> =
        listOf(Popular(1, "Стамбул"), Popular(2, "Сочи"), Popular(3, "Пхукет"))

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