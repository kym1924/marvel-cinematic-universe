package com.kimym.marvel.data.movie

import androidx.annotation.MenuRes
import com.kimym.marvel.core.database.MarvelDao
import com.kimym.marvel.core.datastore.MarvelDatastore
import com.kimym.marvel.core.model.MovieBasicInfo
import com.kimym.marvel.core.model.Phase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dao: MarvelDao,
    private val dataStore: MarvelDatastore,
    private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {
    override fun getMovies(): Flow<List<MovieBasicInfo>> {
        return dao.getMovies().flowOn(ioDispatcher)
    }

    override fun getMoviesByPhase(phase: Int): Flow<List<MovieBasicInfo>> {
        return dao.getMoviesByPhase(phase).flowOn(ioDispatcher)
    }

    override fun getPhase(): Flow<Phase> {
        return dataStore.getPhase()
    }

    override suspend fun setPhase(@MenuRes menuId: Int) {
        return dataStore.setPhase(menuId)
    }
}
