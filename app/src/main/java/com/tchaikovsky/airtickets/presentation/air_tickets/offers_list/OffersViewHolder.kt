package com.tchaikovsky.airtickets.presentation.air_tickets.offers_list

import com.tchaikovsky.airtickets.databinding.OfferItemBinding
import com.tchaikovsky.airtickets.presentation.air_tickets.OfferUi

class OffersViewHolder (private val viewBinding: OfferItemBinding): BaseOffersViewHolder(viewBinding) {
    override fun bindView(offer: OfferUi) {
        with(viewBinding) {
            offerTitleTextView.text = offer.title
            townTextView.text = offer.town
            priceTextView.text = offer.price
            offerImageView.setImageResource(offer.idImage)
        }
    }
}