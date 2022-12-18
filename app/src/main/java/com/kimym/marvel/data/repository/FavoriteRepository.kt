package com.kimym.marvel.data.repository

import com.kimym.marvel.data.model.MovieAndRating
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavorites(): Flow<List<MovieAndRating>>
}
