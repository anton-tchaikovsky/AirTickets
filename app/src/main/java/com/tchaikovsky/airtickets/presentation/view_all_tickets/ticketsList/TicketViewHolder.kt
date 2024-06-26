package com.tchaikovsky.airtickets.presentation.view_all_tickets.ticketsList

import android.view.View
import com.tchaikovsky.airtickets.databinding.TicketItemBinding
import com.tchaikovsky.airtickets.presentation.view_all_tickets.TicketUI

class TicketViewHolder(
    private val viewBinding: TicketItemBinding
) : BaseTicketViewHolder(viewBinding) {
    override fun bindView(ticket: TicketUI) {
        with(viewBinding) {
            ticket.badge?.let {
                badgeTextView.visibility = View.VISIBLE
                badgeTextView.text = it
            }
            priceTextView.text = ticket.price
            departureTimeTextView.text = ticket.departureTime
            departurePortTextView.text = ticket.arrivalPort
            arrivalTimeTextView.text = ticket.arrivalTime
            arrivalPortTextView.text = ticket.arrivalPort
            flightTimeTextView.text = ticket.flightTime
            if (!ticket.hasTransfer)
                withoutTransfersGroup.visibility = View.VISIBLE
        }
    }

}