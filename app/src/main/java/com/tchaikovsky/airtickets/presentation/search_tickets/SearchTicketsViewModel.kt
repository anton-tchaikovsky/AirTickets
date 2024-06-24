package com.tchaikovsky.airtickets.presentation.search_tickets

import androidx.lifecycle.LiveData
import com.tchaikovsky.airtickets.utility.SingleEventLiveData

interface SearchTicketsViewModel {

    fun getPopularsLiveData(): LiveData<List<PopularUI>>

    fun getSingleEventLiveData(): SingleEventLiveData<SearchTicketsScreenState>

    fun onClickSearch(whereFrom: String, where: String)

    fun onClickClear()

    fun onClickTab(tab: Tab)

    fun onClickPopular(popular: String)
}