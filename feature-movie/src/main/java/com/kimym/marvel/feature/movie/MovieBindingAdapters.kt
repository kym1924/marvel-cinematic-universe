package com.kimym.marvel.feature.movie

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kimym.marvel.domain.model.Movie

@BindingAdapter("initRvMovie")
fun RecyclerView.init(adapter: MovieAdapter) {
    also { it.setHasFixedSize(true) }.adapter = adapter
}

@BindingAdapter("submitMovies")
fun RecyclerView.submitMovies(movies: List<Movie>) {
    (adapter as? MovieAdapter)?.submitList(movies)
}
