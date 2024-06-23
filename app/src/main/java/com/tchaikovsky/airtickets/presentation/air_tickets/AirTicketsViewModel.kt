package com.tchaikovsky.airtickets.presentation.air_tickets

import androidx.lifecycle.LiveData
import com.tchaikovsky.airtickets.utility.SingleEventLiveData

interface AirTicketsViewModel {

    var preferencesWhere: String?

    var preferencesWhereFrom: String?

    fun getOffersLiveData(): LiveData<List<OfferUi>>

    fun getSingleEventLiveData(): SingleEventLiveData<AirTicketsScreenState>

    fun onClickSearch(preferencesWhere: String, preferencesWhereFrom: String)

    fun onViewPause(preferencesWhere: String, preferencesWhereFrom: String)
}