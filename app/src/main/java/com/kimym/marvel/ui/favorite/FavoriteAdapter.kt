package com.kimym.marvel.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kimym.marvel.base.BaseDiffUtilItemCallback
import com.kimym.marvel.data.model.MovieAndRating
import com.kimym.marvel.databinding.ItemFavoriteBinding
import com.kimym.marvel.util.MarvelViewHolder

class FavoriteAdapter : ListAdapter<MovieAndRating, MarvelViewHolder<MovieAndRating>>(
    BaseDiffUtilItemCallback(
        areItemsSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areContentsSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarvelViewHolder<MovieAndRating> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoriteBinding.inflate(inflater, parent, false)
        return MarvelViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MarvelViewHolder<MovieAndRating>,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}
