package com.tchaikovsky.airtickets.presentation.search_tickets.popular_list

import com.tchaikovsky.airtickets.databinding.PopularItemBinding
import com.tchaikovsky.airtickets.presentation.search_tickets.PopularUI

class PopularsViewHolder(
    private val viewBinding: PopularItemBinding,
    private val itemClickListener: (String) -> Unit
) : BasePopularsViewHolder(viewBinding) {
    override fun bindView(popular: PopularUI) {
        itemView.setOnClickListener {
            itemClickListener(popular.town)
        }
        with(viewBinding) {
            townTextView.text = popular.town
            popularImageView.setImageResource(popular.idImage)
        }
    }
}