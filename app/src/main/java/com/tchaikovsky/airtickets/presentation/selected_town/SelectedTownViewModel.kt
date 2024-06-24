package com.tchaikovsky.airtickets.presentation.selected_town

import androidx.lifecycle.LiveData
import com.tchaikovsky.airtickets.utility.SingleEventLiveData

interface SelectedTownViewModel {
    fun getTicketsOfferLiveData(): LiveData<List<TicketsOfferUI>>

    fun getSingleEventLiveData(): SingleEventLiveData<SelectedTownScreenState>

    fun onClickClear()

    fun onClickReverse()

    fun onClickDate()

    fun onClickViewAllTickets(whereFrom: String, where: String)

}