package com.kimym.marvel.feature.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.marvel.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    repository: MovieRepository
) : ViewModel() {
    val movies = repository.getMovies()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())
}
