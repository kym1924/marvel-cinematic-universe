package com.kimym.marvel.data.repository

import kotlinx.coroutines.flow.Flow

interface RatingRepository {
    fun getRating(title: String): Flow<Float>

    suspend fun insertRating(title: String, rating: Float)

    suspend fun changeRating(title: String, rating: Float)
}
