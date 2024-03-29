package com.kimym.marvel.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kimym.marvel.core.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM Movie")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getMovie(id: Int): Flow<MovieEntity>

    @Query("SELECT title FROM Movie WHERE id = :id")
    fun getMovieTitle(id: Int): Flow<String>
}
