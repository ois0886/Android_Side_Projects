package com.example.my_own_picture_frame

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_own_picture_frame.databinding.ItemFrameBinding

class FrameAdapter(private val list : List<FrameItem>) : RecyclerView.Adapter<FrameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrameViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ItemFrameBinding.inflate(inflater, parent, false)
        return FrameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FrameViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}

data class FrameItem(
    val uri: Uri
)

class FrameViewHolder(private val binding: ItemFrameBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: FrameItem) {
        binding.frameImageView.setImageURI(item.uri)
    }
}