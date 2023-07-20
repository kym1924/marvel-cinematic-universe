package com.kimym.marvel.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.marvel.data.detail.DetailRepository
import com.kimym.marvel.data.rating.RatingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    detailRepository: DetailRepository,
    private val ratingRepository: RatingRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val id: Int = savedStateHandle["id"] ?: 0

    val movie = detailRepository.getMovie(id)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val rating = ratingRepository.getRating(id)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0f)

    fun insertRating(rating: Float) {
        viewModelScope.launch {
            ratingRepository.insertRating(id, rating)
        }
    }

    fun changeRating(value: Float) {
        viewModelScope.launch {
            if (value != rating.value) {
                ratingRepository.changeRating(id, value)
            }
        }
    }
}
