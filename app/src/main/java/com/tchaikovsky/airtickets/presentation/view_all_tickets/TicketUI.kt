package com.tchaikovsky.airtickets.presentation.view_all_tickets

data class TicketUI(
    val badge: String?,
    val departurePort: String,
    val departureTime: String,
    val arrivalPort: String,
    val arrivalTime: String,
    val flightTime: String,
    val hasTransfer: Boolean,
    val price: String
)