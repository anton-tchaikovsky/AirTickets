package com.tchaikovsky.airtickets.presentation.air_tickets.offers_list

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.tchaikovsky.airtickets.presentation.air_tickets.OfferUi

abstract class BaseOffersViewHolder(viewBinding: ViewBinding) : ViewHolder(viewBinding.root) {
    abstract fun bindView(offer: OfferUi)
}