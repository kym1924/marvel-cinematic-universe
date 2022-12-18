package com.kimym.marvel.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kimym.marvel.NavGraphDirections
import com.kimym.marvel.database.model.Movie
import com.kimym.marvel.databinding.ItemMovieBinding
import com.kimym.marvel.util.NavigateCallback

class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(
    object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Movie) {
            binding.model = model
            binding.callback = callback
            binding.executePendingBindings()
        }

        companion object {
            val callback = NavigateCallback { view, title ->
                title?.let {
                    val action = NavGraphDirections.actionGlobalDetailFragment(title)
                    view.findNavController().navigate(action)
                }
            }
        }
    }
}
