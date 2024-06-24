package com.tchaikovsky.airtickets.presentation.selected_town.tickets_offers_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tchaikovsky.airtickets.databinding.TicketsOfferItemBinding
import com.tchaikovsky.airtickets.presentation.selected_town.TicketsOfferUI

class TicketsOffersAdapter:
    RecyclerView.Adapter<BaseTicketsOfferViewHolder>() {

    private var ticketsOffers = listOf<TicketsOfferUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseTicketsOfferViewHolder =
        TicketsOfferViewHolder(
            TicketsOfferItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = ticketsOffers.size

    override fun onBindViewHolder(holder: BaseTicketsOfferViewHolder, position: Int) {
        holder.bindView(ticketsOffers[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTicketsOffers(ticketsOffers: List<TicketsOfferUI>) {
        this.ticketsOffers = ticketsOffers
        notifyDataSetChanged()
    }

}