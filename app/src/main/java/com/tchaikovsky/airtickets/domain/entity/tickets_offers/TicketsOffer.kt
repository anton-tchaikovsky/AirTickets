package com.tchaikovsky.airtickets.domain.entity.tickets_offers

import com.google.gson.annotations.SerializedName
import com.tchaikovsky.airtickets.domain.entity.Price

data class TicketsOffer(
    val id: Int,
    val price: Price,
    @field:SerializedName("time_range") val timeRange: List<String>,
    val title: String
)