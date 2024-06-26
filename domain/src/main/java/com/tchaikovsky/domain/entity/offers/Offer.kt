package com.tchaikovsky.domain.entity.offers

import com.tchaikovsky.domain.entity.Price

data class Offer(
    val id: Int,
    val price: Price,
    val title: String,
    val town: String
)