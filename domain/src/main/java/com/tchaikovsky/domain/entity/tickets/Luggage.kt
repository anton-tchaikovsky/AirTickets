package com.tchaikovsky.domain.entity.tickets

import com.google.gson.annotations.SerializedName
import com.tchaikovsky.domain.entity.Price

data class Luggage(
    @field:SerializedName("has_luggage") val hasLuggage: Boolean,
    val price: Price
)