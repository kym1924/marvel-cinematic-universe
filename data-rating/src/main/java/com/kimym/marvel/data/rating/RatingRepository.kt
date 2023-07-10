package com.kimym.marvel.data.rating

import kotlinx.coroutines.flow.Flow

interface RatingRepository {
    fun getTitle(id: Int): Flow<String>

    fun getRating(id: Int): Flow<Float>

    suspend fun insertRating(id: Int, rating: Float)

    suspend fun changeRating(id: Int, rating: Float)
}
