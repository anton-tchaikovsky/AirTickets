package com.tchaikovsky.domain.entity.tickets

import com.google.gson.annotations.SerializedName
import com.tchaikovsky.domain.entity.Price

data class Ticket(
    val arrival: Arrival,
    val badge: String?,
    val company: String,
    val departure: Departure,
    @field:SerializedName("hand_luggage") val handLuggage: HandLuggage,
    @field:SerializedName("has_transfer") val hasTransfer: Boolean,
    @field:SerializedName("has_visa_transfer") val hasVisaTransfer: Boolean,
    val id: Int,
    @field:SerializedName("is_exchangable") val isExchangable: Boolean,
    @field:SerializedName("is_returnable") val isReturnable: Boolean,
    val luggage: Luggage,
    val price: Price,
    @field:SerializedName("provider_name") val providerName: String
)