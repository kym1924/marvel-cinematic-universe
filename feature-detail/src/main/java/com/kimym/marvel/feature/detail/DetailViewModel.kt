package com.kimym.marvel.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kimym.marvel.data.detail.DetailRepository
import com.kimym.marvel.data.rating.RatingRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailViewModel @AssistedInject constructor(
    detailRepository: DetailRepository,
    private val ratingRepository: RatingRepository,
    @Assisted private val id: Int
) : ViewModel() {
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

    @AssistedFactory
    interface DetailAssistedFactory {
        fun create(id: Int): DetailViewModel
    }

    companion object {
        fun provideDetailAssistedFactory(
            assistedFactory: DetailAssistedFactory,
            id: Int
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return assistedFactory.create(id) as T
                }
            }
        }
    }
}
