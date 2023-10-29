package com.kimym.marvel.domain.repository

import com.kimym.marvel.domain.model.Movie
import com.kimym.marvel.domain.model.MovieAndRating
import com.kimym.marvel.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>

    fun getMovieAndRatings(): Flow<List<MovieAndRating>>

    fun getMovie(id: Int): Flow<MovieDetail>

    fun getMovieTitle(id: Int): Flow<String>
}
