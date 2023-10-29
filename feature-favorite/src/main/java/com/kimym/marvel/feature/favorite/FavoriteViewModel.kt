package com.kimym.marvel.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.marvel.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    repository: MovieRepository
) : ViewModel() {
    val favorites = repository.getMovieAndRatings()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}
