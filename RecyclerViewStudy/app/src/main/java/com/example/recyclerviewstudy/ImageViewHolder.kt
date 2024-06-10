package com.example.recyclerviewstudy

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewstudy.databinding.ItemImageMovieBinding

class ImageViewHolder(
    private val binding: ItemImageMovieBinding
) : RecyclerView.ViewHolder(binding.root)  {
    fun bind(item: MovieItems.ImageMovie) = with(binding){
        previewImageView.setImageResource(item.image)
        movieTitle.text = item.title
    }
}