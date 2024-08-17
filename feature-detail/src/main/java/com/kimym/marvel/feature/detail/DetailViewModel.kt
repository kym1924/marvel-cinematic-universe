package com.kimym.marvel.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.marvel.domain.repository.MovieRepository
import com.kimym.marvel.domain.repository.RatingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    movieRepository: MovieRepository,
    private val ratingRepository: RatingRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val id: Int = savedStateHandle["id"] ?: 0

    val movie = movieRepository.getMovie(id)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), null)

    val rating = ratingRepository.getRating(id)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), 0f)

    fun upsertRating(rating: Float) {
        viewModelScope.launch {
            ratingRepository.upsertRating(id, rating)
        }
    }
}
