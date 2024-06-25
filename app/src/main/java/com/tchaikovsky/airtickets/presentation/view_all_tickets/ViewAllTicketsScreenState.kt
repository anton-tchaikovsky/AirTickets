package com.tchaikovsky.airtickets.presentation.view_all_tickets

sealed interface ViewAllTicketsScreenState {
    class Error(val message: String) : ViewAllTicketsScreenState
}