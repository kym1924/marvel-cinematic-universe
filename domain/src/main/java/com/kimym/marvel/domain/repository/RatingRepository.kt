package com.kimym.marvel.domain.repository

import kotlinx.coroutines.flow.Flow

interface RatingRepository {
    fun getRating(id: Int): Flow<Float>

    suspend fun upsertRating(id: Int, rating: Float)
}
