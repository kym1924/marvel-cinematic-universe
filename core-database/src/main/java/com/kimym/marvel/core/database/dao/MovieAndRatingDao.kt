package com.kimym.marvel.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.kimym.marvel.core.model.MovieAndRating
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieAndRatingDao {
    @Query("SELECT * FROM MovieAndRating ORDER BY created_at DESC")
    fun getMovieAndRatings(): Flow<List<MovieAndRating>>
}
