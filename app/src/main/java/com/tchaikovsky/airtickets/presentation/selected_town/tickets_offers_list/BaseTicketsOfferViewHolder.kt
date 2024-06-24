package com.tchaikovsky.airtickets.presentation.selected_town.tickets_offers_list

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.tchaikovsky.airtickets.presentation.selected_town.TicketsOfferUI

abstract class BaseTicketsOfferViewHolder(viewBinding: ViewBinding) : ViewHolder(viewBinding.root) {
    abstract fun bindView(ticketsOfferUI: TicketsOfferUI)
}