package com.kimym.marvel.domain.repository

import kotlinx.coroutines.flow.Flow

interface RatingRepository {
    fun getRating(id: Int): Flow<Float>

    suspend fun insertRating(id: Int, rating: Float)

    suspend fun updateRating(id: Int, rating: Float)
}
