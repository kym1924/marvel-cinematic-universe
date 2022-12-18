package com.kimym.marvel.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kimym.marvel.data.model.MovieAndRating
import com.kimym.marvel.databinding.ItemFavoriteBinding

class FavoriteAdapter : ListAdapter<MovieAndRating, FavoriteAdapter.FavoriteViewHolder>(
    object : DiffUtil.ItemCallback<MovieAndRating>() {
        override fun areItemsTheSame(oldItem: MovieAndRating, newItem: MovieAndRating): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieAndRating, newItem: MovieAndRating): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoriteBinding.inflate(inflater, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FavoriteViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MovieAndRating) {
            binding.model = model
            binding.executePendingBindings()
        }
    }
}
