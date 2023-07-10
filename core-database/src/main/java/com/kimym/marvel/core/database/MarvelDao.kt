package com.kimym.marvel.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kimym.marvel.core.model.Movie
import com.kimym.marvel.core.model.MovieAndRating
import com.kimym.marvel.core.model.MovieBasicInfo
import com.kimym.marvel.core.model.MovieDetailInfo
import com.kimym.marvel.core.model.Rating
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("SELECT id, title, image FROM Movie")
    fun getMovies(): Flow<List<MovieBasicInfo>>

    @Query("SELECT id, title, image FROM Movie WHERE phase = :phase")
    fun getMoviesByPhase(phase: Int): Flow<List<MovieBasicInfo>>

    @Query(
        """
            SELECT e.id, e.title, e.image, r.rating
            FROM Movie AS e, Rating AS r
            WHERE e.id = r.id
            ORDER BY r.created_at DESC
        """
    )
    fun getFavorites(): Flow<List<MovieAndRating>>

    @Query("SELECT id, title, content, `release`, running_time AS runningTime, image FROM Movie WHERE id = :id")
    fun getMovie(id: Int): Flow<MovieDetailInfo>

    @Query("SELECT title FROM Movie WHERE id = :id")
    fun getTitle(id: Int): Flow<String>

    @Query("SELECT rating FROM Rating WHERE id = :id")
    fun getRating(id: Int): Flow<Float>

    @Insert
    suspend fun insertRating(rating: Rating)

    @Query("UPDATE Rating SET rating = :rating WHERE id = :id")
    suspend fun updateRating(id: Int, rating: Float)

    @Query("DELETE FROM Rating WHERE id = :id")
    suspend fun deleteRating(id: Int)
}
