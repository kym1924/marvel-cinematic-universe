package com.kimym.marvel.data

import com.kimym.marvel.database.MarvelDao
import com.kimym.marvel.database.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dao: MarvelDao,
    private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {
    override fun getMovies(): Flow<List<Movie>> {
        return dao.getMovies().flowOn(ioDispatcher)
    }
}
