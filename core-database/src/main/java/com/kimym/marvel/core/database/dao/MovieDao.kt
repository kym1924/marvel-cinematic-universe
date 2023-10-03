package com.kimym.marvel.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kimym.marvel.core.model.Movie
import com.kimym.marvel.core.model.MovieBasicInfo
import com.kimym.marvel.core.model.MovieDetailInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("SELECT id, title, image FROM Movie")
    fun getMovies(): Flow<List<MovieBasicInfo>>

    @Query("SELECT id, title, content, `release`, running_time AS runningTime, image FROM Movie WHERE id = :id")
    fun getMovie(id: Int): Flow<MovieDetailInfo>

    @Query("SELECT title FROM Movie WHERE id = :id")
    fun getMovieTitle(id: Int): Flow<String>
}
