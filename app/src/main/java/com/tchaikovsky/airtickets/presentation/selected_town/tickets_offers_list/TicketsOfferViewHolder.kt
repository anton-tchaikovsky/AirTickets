package com.tchaikovsky.airtickets.presentation.selected_town.tickets_offers_list

import com.tchaikovsky.airtickets.databinding.TicketsOfferItemBinding
import com.tchaikovsky.airtickets.presentation.selected_town.TicketsOfferUI

class TicketsOfferViewHolder(
    private val viewBinding: TicketsOfferItemBinding
) : BaseTicketsOfferViewHolder(viewBinding) {
    override fun bindView(ticketsOfferUI: TicketsOfferUI) {
        with(viewBinding) {
            ticketsOfferUI.let {
                timesTextView.text = it.timeRange
                priceTextView.text = it.price
                nameCompanyTextView.text = it.title
                circleImageView.setImageResource(it.idImage)
            }
        }
    }
}