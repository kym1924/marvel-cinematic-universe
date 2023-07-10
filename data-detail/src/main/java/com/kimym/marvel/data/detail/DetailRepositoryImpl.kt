package com.kimym.marvel.data.detail

import com.kimym.marvel.core.database.MarvelDao
import com.kimym.marvel.core.model.MovieDetailInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val dao: MarvelDao,
    private val ioDispatcher: CoroutineDispatcher
) : DetailRepository {
    override fun getMovie(id: Int): Flow<MovieDetailInfo> {
        return dao.getMovie(id).flowOn(ioDispatcher)
    }
}
