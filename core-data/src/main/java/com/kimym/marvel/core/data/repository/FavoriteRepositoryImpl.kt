package com.kimym.marvel.core.data.repository

import com.kimym.marvel.core.data.di.IODispatcher
import com.kimym.marvel.core.database.dao.MovieAndRatingDao
import com.kimym.marvel.core.model.MovieAndRating
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: MovieAndRatingDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : FavoriteRepository {
    override fun getFavorites(): Flow<List<MovieAndRating>> {
        return dao.getMovieAndRatingList().flowOn(ioDispatcher)
    }
}
