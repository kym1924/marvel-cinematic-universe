package com.kimym.marvel.core.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kimym.marvel.core.database.MarvelDatabase
import com.kimym.marvel.core.database.entity.RatingEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RatingDaoTest {
    private lateinit var ratingDao: RatingDao
    private lateinit var db: MarvelDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MarvelDatabase::class.java).build()
        ratingDao = db.ratingDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun ratingDao_get_rating() = runTest {
        val rating = ratingEntity(0, 4.0f)
        ratingDao.insertRating(rating)
        assertThat(rating.rating, `is`(ratingDao.getRating(rating.id).first()))
    }

    @Test
    fun ratingDao_update_rating() = runTest {
        val rating = ratingEntity(0, 4.0f)
        ratingDao.insertRating(rating)

        val updated = rating.copy(rating = 5.0f)
        ratingDao.updateRating(updated.id, updated.rating)
        assertThat(updated.rating, `is`(ratingDao.getRating(updated.id).first()))
    }

    @Test
    fun ratingDao_delete_rating() = runTest {
        val ratings = listOf(
            ratingEntity(0, 4.0f),
            ratingEntity(2, 3.0f),
            ratingEntity(6, 5.0f)
        ).onEach { entity ->
            ratingDao.insertRating(entity)
        }

        val rating = ratings.random()
        ratingDao.deleteRating(rating.id)

        assertThat(0.0f, `is`(ratingDao.getRating(rating.id).first()))
    }

    private fun ratingEntity(
        id: Int,
        rating: Float
    ): RatingEntity {
        return RatingEntity(
            id = id,
            rating = rating
        )
    }
}
