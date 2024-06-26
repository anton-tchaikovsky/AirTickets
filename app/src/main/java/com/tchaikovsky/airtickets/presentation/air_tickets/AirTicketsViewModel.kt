package com.tchaikovsky.airtickets.presentation.air_tickets

import androidx.lifecycle.LiveData
import com.tchaikovsky.utils.SingleEventLiveData

interface AirTicketsViewModel {

    var preferencesWhere: String?

    var preferencesWhereFrom: String?

    fun getOffersLiveData(): LiveData<List<OfferUi>>

    fun getSingleEventLiveData(): SingleEventLiveData<AirTicketsScreenState>

    fun onClickSearch(preferencesWhereFrom: String, preferencesWhere: String)

    fun onViewPause(preferencesWhereFrom: String, preferencesWhere: String)
}