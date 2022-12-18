package com.kimym.marvel.data.repository

import com.kimym.marvel.data.model.MovieBasicInfo
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<MovieBasicInfo>>

    fun getMoviesByPhase(phase: Int): Flow<List<MovieBasicInfo>>
}
