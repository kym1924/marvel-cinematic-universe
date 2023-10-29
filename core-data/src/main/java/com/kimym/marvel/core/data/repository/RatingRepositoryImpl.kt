package com.kimym.marvel.core.data.repository

import com.kimym.marvel.core.data.di.IODispatcher
import com.kimym.marvel.core.database.dao.RatingDao
import com.kimym.marvel.core.database.entity.RatingEntity
import com.kimym.marvel.domain.repository.RatingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RatingRepositoryImpl @Inject constructor(
    private val ratingDao: RatingDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : RatingRepository {

    override fun getRating(id: Int): Flow<Float> {
        return ratingDao.getRating(id).flowOn(ioDispatcher)
    }

    override suspend fun insertRating(id: Int, rating: Float) {
        ratingDao.insertRating(RatingEntity(id = id, rating = rating))
    }

    override suspend fun updateRating(id: Int, rating: Float) {
        when (rating) {
            0f -> ratingDao.deleteRating(id)
            else -> ratingDao.updateRating(id, rating)
        }
    }
}
