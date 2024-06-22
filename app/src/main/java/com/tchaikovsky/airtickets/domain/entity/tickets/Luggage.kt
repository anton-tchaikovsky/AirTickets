package com.tchaikovsky.airtickets.domain.entity.tickets

import com.google.gson.annotations.SerializedName
import com.tchaikovsky.airtickets.domain.entity.Price

data class Luggage(
    @field:SerializedName("has_luggage") val hasLuggage: Boolean,
    val price: Price
)