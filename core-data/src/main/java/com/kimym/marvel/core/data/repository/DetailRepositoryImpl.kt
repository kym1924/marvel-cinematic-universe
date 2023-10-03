package com.kimym.marvel.core.data.repository

import com.kimym.marvel.core.data.di.IODispatcher
import com.kimym.marvel.core.database.dao.MovieDao
import com.kimym.marvel.core.model.MovieDetailInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val dao: MovieDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : DetailRepository {
    override fun getMovie(id: Int): Flow<MovieDetailInfo> {
        return dao.getMovie(id).flowOn(ioDispatcher)
    }
}
