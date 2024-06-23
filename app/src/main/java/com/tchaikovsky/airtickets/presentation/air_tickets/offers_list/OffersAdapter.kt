package com.tchaikovsky.airtickets.presentation.air_tickets.offers_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tchaikovsky.airtickets.databinding.OfferItemBinding
import com.tchaikovsky.airtickets.presentation.air_tickets.OfferUi

class OffersAdapter : RecyclerView.Adapter<BaseOffersViewHolder>() {

    private var offers = listOf<OfferUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseOffersViewHolder =
        OffersViewHolder(
            OfferItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = offers.size

    override fun onBindViewHolder(holder: BaseOffersViewHolder, position: Int) {
        holder.bindView(offers[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setOffers(offers: List<OfferUi>) {
        this.offers = offers
        notifyDataSetChanged()
    }

}