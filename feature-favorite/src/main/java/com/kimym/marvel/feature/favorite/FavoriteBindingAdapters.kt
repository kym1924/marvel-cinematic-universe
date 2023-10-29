package com.kimym.marvel.feature.favorite

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kimym.marvel.domain.model.MovieAndRating

@BindingAdapter("initRvFavorite")
fun RecyclerView.init(adapter: FavoriteAdapter) {
    also { it.setHasFixedSize(true) }.adapter = adapter
}

@BindingAdapter("submitFavorites")
fun RecyclerView.submitFavorites(favorites: List<MovieAndRating>) {
    (adapter as? FavoriteAdapter)?.submitList(favorites)
}
