package com.kimym.marvel.feature.rating

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kimym.marvel.data.rating.RatingRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class RatingViewModel @AssistedInject constructor(
    repository: RatingRepository,
    @Assisted id: Int
) : ViewModel() {
    val title = repository.getTitle(id)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")

    @AssistedFactory
    interface RatingAssistedFactory {
        fun create(id: Int): RatingViewModel
    }

    companion object {
        fun provideRatingAssistedFactory(
            assistedFactory: RatingAssistedFactory,
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
