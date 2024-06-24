package com.tchaikovsky.airtickets.presentation.selected_town

sealed interface SelectedTownScreenState {

    data object ClearWhere : SelectedTownScreenState

    data object ReverseWhereFromAndFrom : SelectedTownScreenState

    data object ShowCalendar : SelectedTownScreenState

    data class ViewAllTicketsState(val whereFrom: String, val where: String) :
        SelectedTownScreenState

    class Error(val message: String) : SelectedTownScreenState
}