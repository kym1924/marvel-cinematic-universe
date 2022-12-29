package com.kimym.marvel.data.repository

import kotlinx.coroutines.flow.Flow

interface RatingRepository {
    fun getExistsRating(title: String): Flow<Boolean>

    suspend fun insertRating(title: String, rating: Float)
}
