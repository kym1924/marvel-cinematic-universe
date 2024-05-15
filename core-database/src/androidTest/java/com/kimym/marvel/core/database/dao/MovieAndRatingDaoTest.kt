package com.kimym.marvel.core.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kimym.marvel.core.database.MarvelDatabase
import com.kimym.marvel.core.database.entity.MovieEntity
import com.kimym.marvel.core.database.entity.RatingEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieAndRatingDaoTest {
    private lateinit var db: MarvelDatabase
    private lateinit var movieDao: MovieDao
    private lateinit var ratingDao: RatingDao
    private lateinit var movieAndRatingDao: MovieAndRatingDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MarvelDatabase::class.java).build()
        movieDao = db.movieDao()
        ratingDao = db.ratingDao()
        movieAndRatingDao = db.movieAndRatingDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun movieAndRatingDao_get_movieAndRatings() = runTest {
        val movies = listOf(
            movieEntity(0, "Iron Man"),
            movieEntity(2, "Iron Man 2"),
            movieEntity(6, "Iron Man 3")
        )

        movieDao.insertMovies(movies)

        val ratings = listOf(
            RatingEntity(0, 4.0f),
            RatingEntity(2, 3.0f),
            RatingEntity(6, 5.0f)
        ).onEach { entity ->
            ratingDao.insertRating(entity)
        }

        val movieAndRatings = movieAndRatingDao.getMovieAndRatings().first()

        assertEquals(
            movieAndRatings.map { entity -> entity.title },
            movies.map { entity -> entity.title }
        )

        assertEquals(
            movieAndRatings.map { entity -> entity.id },
            movies.map { entity -> entity.id }
        )

        assertEquals(
            movieAndRatings.map { entity -> entity.rating },
            ratings.map { entity -> entity.rating }
        )
    }

    private fun movieEntity(
        id: Int,
        title: String
    ): MovieEntity {
        return MovieEntity(
            id = id,
            title = title,
            phase = 0,
            content = "",
            release = "",
            runningTime = 0,
            image = ""
        )
    }
}
