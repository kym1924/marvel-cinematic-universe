package com.kimym.marvel.data.favorite

import com.kimym.marvel.core.model.MovieAndRating
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavorites(): Flow<List<MovieAndRating>>
}
