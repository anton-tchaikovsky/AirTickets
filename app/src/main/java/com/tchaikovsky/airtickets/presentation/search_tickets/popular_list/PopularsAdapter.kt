package com.tchaikovsky.airtickets.presentation.search_tickets.popular_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tchaikovsky.airtickets.databinding.PopularItemBinding
import com.tchaikovsky.airtickets.presentation.search_tickets.PopularUI

class PopularsAdapter (private val itemClickListener: (String) -> Unit) : RecyclerView.Adapter<BasePopularsViewHolder>() {

    private var populars = listOf<PopularUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasePopularsViewHolder =
        PopularsViewHolder(PopularItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
            itemClickListener)

    override fun getItemCount(): Int = populars.size

    override fun onBindViewHolder(holder: BasePopularsViewHolder, position: Int) {
        holder.bindView(populars[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPopulars(populars: List<PopularUI>) {
        this.populars = populars
        notifyDataSetChanged()
    }

}