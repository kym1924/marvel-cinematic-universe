package com.kimym.marvel.data.repository

import com.kimym.marvel.database.model.Movie
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getMovie(title: String): Flow<Movie>

    fun isFavorite(title: String): Flow<Boolean>

    suspend fun insertRating(title: String, rating: Float)
}
