package com.tchaikovsky.airtickets.presentation.selected_town

sealed interface SelectedTownScreenState {

    data object ClearWhere : SelectedTownScreenState

    data object ReverseWhereFromAndFrom : SelectedTownScreenState

    data object ShowCalendar : SelectedTownScreenState

    data class OpenViewAllTicketsState(val flight: String, val flightInfo: String) :
        SelectedTownScreenState

    data class ChangeDepartureDateState(val date: String): SelectedTownScreenState

    class Error(val message: String) : SelectedTownScreenState
}