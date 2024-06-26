package com.tchaikovsky.domain.entity.tickets

import com.google.gson.annotations.SerializedName

data class HandLuggage(
    @field:SerializedName("has_hand_luggage") val hasHandLuggage: Boolean,
    val size: String
)