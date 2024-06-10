package com.example.recyclerviewstudy

import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewstudy.databinding.ItemTitleMovieBinding

class TitleViewHolder(
    private val binding: ItemTitleMovieBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MovieItems.TextMovie) = with(binding){
        MovieTitleView.text = item.title
    }
}