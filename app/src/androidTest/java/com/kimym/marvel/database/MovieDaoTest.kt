package com.kimym.marvel.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kimym.marvel.core.database.MarvelDatabase
import com.kimym.marvel.core.database.dao.MovieDao
import com.kimym.marvel.core.database.entity.MovieEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {
    private lateinit var movieDao: MovieDao
    private lateinit var db: MarvelDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MarvelDatabase::class.java).build()
        movieDao = db.movieDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun movieDao_get_movies() = runTest {
        val movies = listOf(
            movieEntity(0, "Iron Man"),
            movieEntity(2, "Iron Man 2"),
            movieEntity(6, "Iron Man 3")
        )

        movieDao.insertMovies(movies)

        assertThat(movies, `is`(movieDao.getMovies().first()))
    }

    @Test
    fun movieDao_get_movie() = runTest {
        val movies = listOf(
            movieEntity(0, "Iron Man"),
            movieEntity(2, "Iron Man 2"),
            movieEntity(6, "Iron Man 3")
        )

        movieDao.insertMovies(movies)

        val entity = movies.random()

        assertThat(entity, `is`(movieDao.getMovie(entity.id).first()))
    }

    @Test
    fun movieDao_get_movie_title() = runTest {
        val movies = listOf(
            movieEntity(0, "Iron Man"),
            movieEntity(2, "Iron Man 2"),
            movieEntity(6, "Iron Man 3")
        )

        movieDao.insertMovies(movies)

        val entity = movies.random()

        assertThat(entity.title, `is`(movieDao.getMovieTitle(entity.id).first()))
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
