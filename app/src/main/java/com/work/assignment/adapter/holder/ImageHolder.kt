package com.work.assignment.adapter.holder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.work.assignment.databinding.ImageItemBinding
import com.work.assignment.model.ListResModelItem
import com.work.assignment.utils.MyHelper

class ImageHolder(private val binding: ImageItemBinding) : ViewHolder(binding.root) {
    fun bind(data: ListResModelItem) {

        Glide.with(binding.pic).load(data.download_url)
            .placeholder(MyHelper.getProgress(binding.pic))
            .into(binding.pic)
        binding.title.text =
            data.author ?: "N/A"
    }
}