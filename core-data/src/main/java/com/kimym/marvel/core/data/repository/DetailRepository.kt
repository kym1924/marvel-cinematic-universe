package com.kimym.marvel.core.data.repository

import com.kimym.marvel.core.model.MovieDetailInfo
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getMovie(id: Int): Flow<MovieDetailInfo>
}
