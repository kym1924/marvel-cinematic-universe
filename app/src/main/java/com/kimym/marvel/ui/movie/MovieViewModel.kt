package com.kimym.marvel.ui.movie

import androidx.annotation.MenuRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.marvel.data.model.Phase
import com.kimym.marvel.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModel @Inject constructor(
    repository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val key = "Phase"

    private val _phase = MutableStateFlow(savedStateHandle[key] ?: Phase.ALL)
    val phase = _phase.asStateFlow()

    val movies = _phase.flatMapLatest { phase ->
        when (phase) {
            Phase.ALL -> repository.getMovies()
            else -> repository.getMoviesByPhase(phase.ordinal)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun setPhase(@MenuRes menuId: Int) {
        Phase.map[menuId]?.let {
            _phase.value = it
            savedStateHandle[key] = it
        }
    }
}
