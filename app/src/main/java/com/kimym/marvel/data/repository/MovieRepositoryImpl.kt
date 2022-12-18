package com.kimym.marvel.data.repository

import com.kimym.marvel.data.model.MovieBasicInfo
import com.kimym.marvel.database.MarvelDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dao: MarvelDao,
    private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {
    override fun getMovies(): Flow<List<MovieBasicInfo>> {
        return dao.getMovies().flowOn(ioDispatcher)
    }

    override fun getMoviesByPhase(phase: Int): Flow<List<MovieBasicInfo>> {
        return dao.getMoviesByPhase(phase).flowOn(ioDispatcher)
    }
}
