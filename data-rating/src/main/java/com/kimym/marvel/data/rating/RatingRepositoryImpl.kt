package com.kimym.marvel.data.rating

import com.kimym.marvel.core.database.MarvelDao
import com.kimym.marvel.core.model.Rating
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RatingRepositoryImpl @Inject constructor(
    private val dao: MarvelDao,
    private val ioDispatcher: CoroutineDispatcher
) : RatingRepository {
    override fun getTitle(id: Int): Flow<String> {
        return dao.getTitle(id).flowOn(ioDispatcher)
    }

    override fun getRating(id: Int): Flow<Float> {
        return dao.getRating(id).flowOn(ioDispatcher)
    }

    override suspend fun insertRating(id: Int, rating: Float) {
        dao.insertRating(Rating(id = id, rating = rating))
    }

    override suspend fun changeRating(id: Int, rating: Float) {
        when (rating) {
            0f -> dao.deleteRating(id)
            else -> dao.updateRating(id, rating)
        }
    }
}
