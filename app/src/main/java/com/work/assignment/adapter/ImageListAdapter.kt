package com.work.assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.work.assignment.adapter.holder.ImageHolder
import com.work.assignment.databinding.ImageItemBinding
import com.work.assignment.interfaces.RecyclerItemClickListener
import com.work.assignment.model.ListResModelItem

class ImageListAdapter(private val listener: RecyclerItemClickListener) : Adapter<ImageHolder>() {
    private val list = mutableListOf<ListResModelItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder{
        return ImageHolder(
            ImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            val pos = holder.absoluteAdapterPosition
            listener.onRecyclerItemClick(pos, list[pos], "details", it)
        }
    }

    fun setData(contents: List<ListResModelItem>) {
        list.addAll(contents)
        notifyDataSetChanged()
    }

    fun clearList() {
        list.clear()
        notifyDataSetChanged()
    }
}