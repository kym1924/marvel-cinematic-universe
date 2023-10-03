package com.kimym.marvel.core.data.repository

import com.kimym.marvel.core.data.di.IODispatcher
import com.kimym.marvel.core.database.dao.MovieDao
import com.kimym.marvel.core.model.MovieBasicInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dao: MovieDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {
    override fun getMovies(): Flow<List<MovieBasicInfo>> {
        return dao.getMovieList().flowOn(ioDispatcher)
    }
}
