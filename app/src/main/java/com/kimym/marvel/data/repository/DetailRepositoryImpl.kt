package com.kimym.marvel.data.repository

import com.kimym.marvel.database.MarvelDao
import com.kimym.marvel.database.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val dao: MarvelDao,
    private val ioDispatcher: CoroutineDispatcher
) : DetailRepository {
    override fun getMovie(title: String): Flow<Movie> {
        return dao.getMovie(title).flowOn(ioDispatcher)
    }
}
