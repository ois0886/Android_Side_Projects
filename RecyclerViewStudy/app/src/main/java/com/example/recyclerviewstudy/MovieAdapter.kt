package com.example.recyclerviewstudy

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewstudy.databinding.ItemImageMovieBinding
import com.example.recyclerviewstudy.databinding.ItemTitleMovieBinding

class MovieAdapter : ListAdapter<MovieItems, RecyclerView.ViewHolder>(diffCallback) {

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is MovieItems.ImageMovie -> ITEM_IMAGE
            is MovieItems.TextMovie -> ITEM_TITLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("RecyclerView", "onCreateViewHolder")
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ITEM_IMAGE -> {
                val binding = ItemImageMovieBinding.inflate(layoutInflater, parent, false)
                ImageViewHolder(binding)
            }

            else -> {
                val binding = ItemTitleMovieBinding.inflate(layoutInflater, parent, false)
                TitleViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("RecyclerView", "onBindViewHolder $position")
        when (holder) {
            is ImageViewHolder -> {
                holder.bind(currentList[position] as MovieItems.ImageMovie)
            }

            is TitleViewHolder -> {
                holder.bind(currentList[position] as MovieItems.TextMovie)
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MovieItems>() {
            override fun areItemsTheSame(oldItem: MovieItems, newItem: MovieItems): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: MovieItems, newItem: MovieItems): Boolean {
                return oldItem == newItem
            }
        }

        const val ITEM_IMAGE = 0
        const val ITEM_TITLE = 1
    }
}