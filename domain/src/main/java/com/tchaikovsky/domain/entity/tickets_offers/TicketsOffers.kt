package com.tchaikovsky.domain.entity.tickets_offers

import com.google.gson.annotations.SerializedName

data class TicketsOffers(
    @field:SerializedName("tickets_offers") val ticketsOffers: List<TicketsOffer>
)
