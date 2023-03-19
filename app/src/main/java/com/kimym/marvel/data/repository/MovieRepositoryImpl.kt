package com.kimym.marvel.data.repository

import androidx.annotation.MenuRes
import com.kimym.marvel.data.model.MovieBasicInfo
import com.kimym.marvel.data.model.Phase
import com.kimym.marvel.database.MarvelDao
import com.kimym.marvel.datastore.MarvelDatastore
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
