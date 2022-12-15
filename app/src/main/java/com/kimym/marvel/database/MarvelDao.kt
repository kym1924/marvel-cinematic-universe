package com.kimym.marvel.database

import androidx.room.Dao
import androidx.room.Query
import com.kimym.marvel.database.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {
    @Query("SELECT * FROM Movie")
    fun getMovies(): Flow<List<Movie>>
}
