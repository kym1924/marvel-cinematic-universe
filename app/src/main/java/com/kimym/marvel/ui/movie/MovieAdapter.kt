package com.kimym.marvel.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kimym.marvel.data.model.MovieBasicInfo
import com.kimym.marvel.databinding.ItemMovieBinding
import com.kimym.marvel.util.MarvelViewHolder

class MovieAdapter : ListAdapter<MovieBasicInfo, MarvelViewHolder<MovieBasicInfo>>(
    object : DiffUtil.ItemCallback<MovieBasicInfo>() {
        override fun areItemsTheSame(oldItem: MovieBasicInfo, newItem: MovieBasicInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieBasicInfo, newItem: MovieBasicInfo): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarvelViewHolder<MovieBasicInfo> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MarvelViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MarvelViewHolder<MovieBasicInfo>,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}
