package com.tchaikovsky.airtickets.presentation.air_tickets

sealed interface AirTicketsScreenState {
    class PreferencesState(val preferencesWhereFrom: String?, val preferencesWhere: String?) :
        AirTicketsScreenState

    class Error(val message: String) : AirTicketsScreenState
}