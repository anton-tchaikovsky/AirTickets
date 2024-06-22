package com.tchaikovsky.airtickets.domain.entity.offers

import com.tchaikovsky.airtickets.domain.entity.Price

data class Offer(
    val id: Int,
    val price: Price,
    val title: String,
    val town: String
)