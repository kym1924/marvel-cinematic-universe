package com.kimym.marvel.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.marvel.domain.repository.MovieRepository
import com.kimym.marvel.domain.usecase.GetRatingUseCase
import com.kimym.marvel.domain.usecase.UpsertRatingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    getRatingUseCase: GetRatingUseCase,
    movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle,
    private val upsertRatingUseCase: dagger.Lazy<UpsertRatingUseCase>
) : ViewModel() {
    private val id: Int = savedStateHandle["id"] ?: 0

    val movie = movieRepository.getMovie(id)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), null)

    val rating = getRatingUseCase(id)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), 0f)

    fun upsertRating(rating: Float) {
        viewModelScope.launch {
            upsertRatingUseCase.get().invoke(id, rating)
        }
    }
}
