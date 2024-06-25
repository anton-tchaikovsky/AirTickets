package com.tchaikovsky.airtickets.presentation.view_all_tickets

import androidx.lifecycle.LiveData
import com.tchaikovsky.airtickets.utility.SingleEventLiveData

interface ViewAllTicketsViewModel {
    fun getTicketsLiveData(): LiveData<List<TicketUI>>

    fun getSingleEventLiveData(): SingleEventLiveData<ViewAllTicketsScreenState>
}