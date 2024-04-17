package com.kimym.marvel.core

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker.Result
import androidx.work.testing.TestListenableWorkerBuilder
import com.kimym.marvel.core.database.MarvelDatabase
import com.kimym.marvel.core.database.dao.MovieDao
import com.kimym.marvel.core.worker.MarvelDatabasePrePopulateWorker
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MarvelDatabasePrePopulateWorkerTest {
    private lateinit var context: Context
    private lateinit var db: MarvelDatabase
    private lateinit var movieDao: MovieDao

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, MarvelDatabase::class.java).build()
        movieDao = db.movieDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun marvelDatabasePrePopulateWorker_doWork_success() = runTest {
        val worker = TestListenableWorkerBuilder<MarvelDatabasePrePopulateWorker>(context)
            .setWorkerFactory(MarvelDatabasePrePopulateWorkerFactory(movieDao))
            .build()

        assertEquals(Result.success(), worker.doWork())
    }
}
