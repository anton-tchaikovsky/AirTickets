package com.tchaikovsky.airtickets.presentation.air_tickets

sealed interface AirTicketsScreenState {
    class OpenSearchScreenState(val preferencesWhere: String?, val preferencesWhereFrom: String?) :
        AirTicketsScreenState

    class Error(val message: String) : AirTicketsScreenState
}