package com.tchaikovsky.airtickets.presentation.search_tickets

sealed interface SearchTicketsScreenState {
    class SearchState(val whereFrom: String, val where: String) :
        SearchTicketsScreenState

    data object ClearWhere: SearchTicketsScreenState

    class WhereState(val where: String): SearchTicketsScreenState

    class Error(val message: String) : SearchTicketsScreenState

    enum class OpenTab: SearchTicketsScreenState
    {DIFFICULT_ROUTER, WEEKENDS, HOT_TICKETS}
}