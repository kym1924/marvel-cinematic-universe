package com.kimym.marvel.core.data.repository

import com.kimym.marvel.core.model.MovieBasicInfo
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<MovieBasicInfo>>
}
