package com.kimym.marvel.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kimym.marvel.data.model.MovieAndRating
import com.kimym.marvel.data.model.MovieBasicInfo
import com.kimym.marvel.data.model.MovieDetailInfo
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

    @Query(
        """
            SELECT e.id, e.title, e.image, r.rating
            FROM Movie AS e, Rating AS r
            WHERE e.title = r.title
            ORDER BY r.id
        """
    )
    fun getFavorites(): Flow<List<MovieAndRating>>

    @Query("SELECT id, title, content, `release`, running_time AS runningTime, image FROM Movie WHERE title = :title")
    fun getMovie(title: String): Flow<MovieDetailInfo>

    @Query("SELECT EXISTS(SELECT 1 FROM Rating WHERE title = :title)")
    fun getExistsRating(title: String): Flow<Boolean>
}
