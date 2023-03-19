package com.kimym.marvel.ui.movie

import androidx.annotation.MenuRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimym.marvel.data.model.Phase
import com.kimym.marvel.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    val phase = repository.getPhase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val movies = phase.filterNotNull().flatMapLatest { phase ->
        when (phase) {
            Phase.ALL -> repository.getMovies()
            else -> repository.getMoviesByPhase(phase.ordinal)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun setPhase(@MenuRes menuId: Int) {
        viewModelScope.launch {
            repository.setPhase(menuId)
        }
    }
}
