package com.kimym.marvel.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kimym.marvel.data.model.MovieBasicInfo
import com.kimym.marvel.database.model.Movie
import com.kimym.marvel.database.model.Rating
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Insert
    suspend fun insertRating(rating: Rating)

    @Query("SELECT id, title, image FROM Movie")
    fun getMovies(): Flow<List<MovieBasicInfo>>

    @Query("SELECT id, title, image FROM Movie WHERE phase = :phase")
    fun getMoviesByPhase(phase: Int): Flow<List<MovieBasicInfo>>

    @Query("SELECT * FROM Movie WHERE title = :title")
    fun getMovie(title: String): Flow<Movie>

    @Query("SELECT EXISTS(SELECT 1 FROM Rating WHERE title = :title)")
    fun getIsFavorite(title: String): Flow<Boolean>
}
