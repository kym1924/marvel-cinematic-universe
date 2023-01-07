package com.kimym.marvel.data.repository

import com.kimym.marvel.database.MarvelDao
import com.kimym.marvel.database.model.Rating
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RatingRepositoryImpl @Inject constructor(
    private val dao: MarvelDao,
    private val ioDispatcher: CoroutineDispatcher
) : RatingRepository {
    override fun getExistsRating(title: String): Flow<Boolean> {
        return dao.getExistsRating(title).flowOn(ioDispatcher)
    }

    override suspend fun insertRating(title: String, rating: Float) {
        dao.insertRating(Rating(title = title, rating = rating))
    }
}