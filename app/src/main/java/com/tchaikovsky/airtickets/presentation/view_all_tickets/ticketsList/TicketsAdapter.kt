package com.tchaikovsky.airtickets.presentation.view_all_tickets.ticketsList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tchaikovsky.airtickets.databinding.TicketItemBinding
import com.tchaikovsky.airtickets.presentation.view_all_tickets.TicketUI

class TicketsAdapter:
    RecyclerView.Adapter<BaseTicketViewHolder>() {

    private var tickets = listOf<TicketUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseTicketViewHolder =
        TicketViewHolder(
            TicketItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = tickets.size

    override fun onBindViewHolder(holder: BaseTicketViewHolder, position: Int) {
        holder.bindView(tickets[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTickets(tickets: List<TicketUI>) {
        this.tickets = tickets
        notifyDataSetChanged()
    }
}