package com.kimym.marvel.data.detail

import com.kimym.marvel.core.model.MovieDetailInfo
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getMovie(id: Int): Flow<MovieDetailInfo>
}
