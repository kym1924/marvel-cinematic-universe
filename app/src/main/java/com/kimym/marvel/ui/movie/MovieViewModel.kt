package com.kimym.marvel.ui.movie

import androidx.annotation.MenuRes
import androidx.lifecycle.ViewModel
import com.kimym.marvel.data.model.Phase
import com.kimym.marvel.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModel @Inject constructor(
    repository: MovieRepository
) : ViewModel() {
    var phase = MutableStateFlow(Phase.ALL)
        private set

    val movies = phase.flatMapLatest { phase ->
        when (phase) {
            Phase.ALL -> repository.getMovies()
            else -> repository.getMoviesByPhase(phase.ordinal)
        }
    }

    fun setPhase(@MenuRes menuId: Int) {
        Phase.map[menuId]?.let { phase.value = it }
    }
}
