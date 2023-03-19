package com.kimym.marvel.data.repository

import androidx.annotation.MenuRes
import com.kimym.marvel.data.model.MovieBasicInfo
import com.kimym.marvel.data.model.Phase
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<MovieBasicInfo>>

    fun getMoviesByPhase(phase: Int): Flow<List<MovieBasicInfo>>

    fun getPhase(): Flow<Phase>

    suspend fun setPhase(@MenuRes menuId: Int)
}
