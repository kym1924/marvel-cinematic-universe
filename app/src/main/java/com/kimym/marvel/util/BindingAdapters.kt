package com.kimym.marvel.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimym.marvel.data.model.MovieAndRating
import com.kimym.marvel.data.model.MovieBasicInfo
import com.kimym.marvel.ui.favorite.FavoriteAdapter
import com.kimym.marvel.ui.movie.MovieAdapter

@BindingAdapter("initRvMovie")
fun RecyclerView.init(adapter: MovieAdapter) {
    also { it.setHasFixedSize(true) }.adapter = adapter
}

@BindingAdapter("initRvFavorite")
fun RecyclerView.init(adapter: FavoriteAdapter) {
    also { it.setHasFixedSize(true) }.adapter = adapter
}

@BindingAdapter("submitMovies")
fun RecyclerView.submitMovies(movies: List<MovieBasicInfo>) {
    (adapter as MovieAdapter).submitList(movies)
}

@BindingAdapter("submitFavorites")
fun RecyclerView.submitFavorites(favorites: List<MovieAndRating>) {
    (adapter as FavoriteAdapter).submitList(favorites)
}

@BindingAdapter("setImageWithUrl")
fun ImageView.setImageWithUrl(url: String?) {
    url?.let {
        Glide.with(context)
            .load(url)
            .into(this)
    }
}
