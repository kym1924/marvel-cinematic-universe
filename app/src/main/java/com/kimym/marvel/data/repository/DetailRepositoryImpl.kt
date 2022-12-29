package com.kimym.marvel.data.repository

import com.kimym.marvel.data.model.MovieDetailInfo
import com.kimym.marvel.database.MarvelDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val dao: MarvelDao,
    private val ioDispatcher: CoroutineDispatcher
) : DetailRepository {
    override fun getMovie(title: String): Flow<MovieDetailInfo> {
        return dao.getMovie(title).flowOn(ioDispatcher)
    }
}
