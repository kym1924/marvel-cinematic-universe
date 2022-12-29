package com.kimym.marvel.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kimym.marvel.data.repository.DetailRepository
import com.kimym.marvel.data.repository.RatingRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailViewModel @AssistedInject constructor(
    detailRepository: DetailRepository,
    private val ratingRepository: RatingRepository,
    @Assisted private val title: String
) : ViewModel() {
    val movie = detailRepository.getMovie(title)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val existsRating = ratingRepository.getExistsRating(title)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), true)

    fun insertRating(rating: Float) {
        viewModelScope.launch {
            ratingRepository.insertRating(title, rating)
        }
    }

    @AssistedFactory
    interface DetailAssistedFactory {
        fun create(title: String): DetailViewModel
    }

    companion object {
        fun provideDetailAssistedFactory(
            assistedFactory: DetailAssistedFactory,
            title: String
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return assistedFactory.create(title) as T
                }
            }
        }
    }
}
