package com.kimym.marvel.core.data.repository

import com.kimym.marvel.core.model.MovieAndRating
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavorites(): Flow<List<MovieAndRating>>
}
