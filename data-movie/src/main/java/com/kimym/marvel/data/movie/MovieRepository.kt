package com.kimym.marvel.data.movie

import com.kimym.marvel.core.model.MovieBasicInfo
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<MovieBasicInfo>>
}
