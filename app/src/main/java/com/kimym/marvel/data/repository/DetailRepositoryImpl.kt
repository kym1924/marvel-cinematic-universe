package com.kimym.marvel.data.repository

import com.kimym.marvel.data.model.MovieDetailInfo
import com.kimym.marvel.database.MarvelDao
import com.kimym.marvel.database.model.Rating
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val dao: MarvelDao,
    private val ioDispatcher: CoroutineDispatcher
) : DetailRepository {
    override fun getMovie(title: String): Flow<MovieDetailInfo> {
        return dao.getMovie(title).flowOn(ioDispatcher)
    }

    override fun isFavorite(title: String): Flow<Boolean> {
        return dao.getIsFavorite(title)
    }

    override suspend fun insertRating(title: String, rating: Float) {
        dao.insertRating(Rating(title = title, rating = rating))
    }
}
