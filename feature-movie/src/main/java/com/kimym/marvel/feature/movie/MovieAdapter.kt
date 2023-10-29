package com.kimym.marvel.feature.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.kimym.marvel.core.ui.recyclerview.BaseDiffUtilItemCallback
import com.kimym.marvel.core.ui.recyclerview.BaseViewHolder
import com.kimym.marvel.domain.model.Movie
import com.kimym.marvel.feature.movie.databinding.ItemMovieBinding

class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieViewHolder<ItemMovieBinding>>(
    BaseDiffUtilItemCallback(
        areItemsSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areContentsSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder<ItemMovieBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder<ItemMovieBinding>,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    class MovieViewHolder<T : ViewDataBinding>(
        binding: T
    ) : BaseViewHolder<T>(binding) {
        fun bind(model: Movie) {
            with(binding) {
                setVariable(BR.model, model)
                setVariable(BR.callback, callback)
                executePendingBindings()
            }
        }
    }
}
