package com.kimym.marvel.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.marvel.core.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    repository: FavoriteRepository
) : ViewModel() {
    val favorites = repository.getFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}
