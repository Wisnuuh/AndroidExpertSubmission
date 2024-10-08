package com.dicoding.androidexpertsubmission.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.androidexpertsubmission.core.databinding.ItemListGamesBinding
import com.dicoding.androidexpertsubmission.core.domain.model.Games

class GamesAdapter: ListAdapter<Games, GamesAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Games) -> Unit)? = null

    inner class ListViewHolder(private var binding: ItemListGamesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Games) {
            Glide.with(itemView.context)
                .load(data.backgroundImage)
                .into(binding.ivItemImage)
            binding.textTitle.text = data.name
            binding.textSecond.text = "Rating: ${data.rating}"
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(ItemListGamesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Games> =
            object : DiffUtil.ItemCallback<Games>() {
                override fun areItemsTheSame(oldItem: Games, newItem: Games): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Games, newItem: Games): Boolean {
                    return oldItem == newItem
                }
            }
    }
}