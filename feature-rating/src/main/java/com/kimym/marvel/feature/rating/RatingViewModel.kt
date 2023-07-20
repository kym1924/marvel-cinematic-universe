package com.kimym.marvel.feature.rating

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.marvel.data.rating.RatingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(
    repository: RatingRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val id: Int = savedStateHandle["id"] ?: 0

    val title = repository.getTitle(id)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
}
