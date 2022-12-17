package com.kimym.marvel.data

import com.kimym.marvel.database.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>

    fun getMoviesByPhase(phase: Int): Flow<List<Movie>>
}
