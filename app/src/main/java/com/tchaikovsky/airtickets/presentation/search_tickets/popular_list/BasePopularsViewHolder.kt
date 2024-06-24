package com.tchaikovsky.airtickets.presentation.search_tickets.popular_list

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.tchaikovsky.airtickets.presentation.search_tickets.PopularUI

abstract class BasePopularsViewHolder(viewBinding: ViewBinding) : ViewHolder(viewBinding.root) {
    abstract fun bindView(popular: PopularUI)
}