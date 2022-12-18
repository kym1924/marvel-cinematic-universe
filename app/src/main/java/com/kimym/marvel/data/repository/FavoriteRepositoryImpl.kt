package com.kimym.marvel.data.repository

import com.kimym.marvel.data.model.MovieAndRating
import com.kimym.marvel.database.MarvelDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: MarvelDao,
    private val ioDispatcher: CoroutineDispatcher
) : FavoriteRepository {
    override fun getFavorites(): Flow<List<MovieAndRating>> {
        return dao.getFavorites().flowOn(ioDispatcher)
    }
}
