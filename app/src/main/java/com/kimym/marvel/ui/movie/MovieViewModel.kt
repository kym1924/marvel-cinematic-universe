package com.kimym.marvel.ui.movie

import androidx.lifecycle.ViewModel
import com.kimym.marvel.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    repository: MovieRepository
) : ViewModel() {
    val movies = repository.getMovies()
}
