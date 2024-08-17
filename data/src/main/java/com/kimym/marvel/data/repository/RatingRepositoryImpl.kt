package com.kimym.marvel.data.repository

import com.kimym.marvel.core.database.dao.RatingDao
import com.kimym.marvel.core.database.entity.RatingEntity
import com.kimym.marvel.domain.repository.RatingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RatingRepositoryImpl @Inject constructor(
    private val ratingDao: RatingDao
) : RatingRepository {
    override fun getRating(id: Int): Flow<Float> {
        return ratingDao.getRating(id)
    }

    override suspend fun upsertRating(id: Int, rating: Float) {
        ratingDao.upsertRating(RatingEntity(id = id, rating = rating))
    }
}
