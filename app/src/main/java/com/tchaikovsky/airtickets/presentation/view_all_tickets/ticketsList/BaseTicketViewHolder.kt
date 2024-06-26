package com.tchaikovsky.airtickets.presentation.view_all_tickets.ticketsList

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.tchaikovsky.airtickets.presentation.view_all_tickets.TicketUI

abstract class BaseTicketViewHolder(viewBinding: ViewBinding) : ViewHolder(viewBinding.root) {
    abstract fun bindView(ticket: TicketUI)
}