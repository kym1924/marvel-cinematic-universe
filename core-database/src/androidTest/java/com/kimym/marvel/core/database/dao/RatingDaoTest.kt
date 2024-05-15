package com.kimym.marvel.core.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kimym.marvel.core.database.MarvelDatabase
import com.kimym.marvel.core.database.entity.RatingEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
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
        val rating = RatingEntity(0, 4.0f)
        ratingDao.insertRating(rating)

        assertEquals(rating.rating, ratingDao.getRating(rating.id).first())
    }

    @Test
    fun ratingDao_update_rating() = runTest {
        val rating = RatingEntity(0, 4.0f)
        ratingDao.insertRating(rating)

        val updated = rating.copy(rating = 5.0f)
        ratingDao.updateRating(updated.id, updated.rating)

        assertEquals(updated.rating, ratingDao.getRating(updated.id).first())
    }

    @Test
    fun ratingDao_delete_rating() = runTest {
        val ratings = listOf(
            RatingEntity(0, 4.0f),
            RatingEntity(2, 3.0f),
            RatingEntity(6, 5.0f)
        ).onEach { entity ->
            ratingDao.insertRating(entity)
        }

        val rating = ratings.random()
        ratingDao.deleteRating(rating.id)

        assertEquals(0.0f, ratingDao.getRating(rating.id).first())
    }
}
