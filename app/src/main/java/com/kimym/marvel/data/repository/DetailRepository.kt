package com.kimym.marvel.data.repository

import com.kimym.marvel.data.model.MovieDetailInfo
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getMovie(title: String): Flow<MovieDetailInfo>

    fun isFavorite(title: String): Flow<Boolean>

    suspend fun insertRating(title: String, rating: Float)
}
