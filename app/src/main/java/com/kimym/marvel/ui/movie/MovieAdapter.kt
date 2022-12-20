package com.kimym.marvel.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kimym.marvel.base.BaseDiffUtilItemCallback
import com.kimym.marvel.data.model.MovieBasicInfo
import com.kimym.marvel.databinding.ItemMovieBinding
import com.kimym.marvel.util.MarvelViewHolder

class MovieAdapter : ListAdapter<MovieBasicInfo, MarvelViewHolder<MovieBasicInfo>>(
    BaseDiffUtilItemCallback(
        areItemsSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areContentsSame = { oldItem, newItem -> oldItem == newItem }
    )
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
