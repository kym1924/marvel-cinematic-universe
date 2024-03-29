package com.kimym.marvel.worker

import android.util.Log
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.ListenableWorker.Result
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import com.kimym.marvel.core.database.dao.MovieDao
import com.kimym.marvel.core.worker.MarvelDatabasePrePopulateWorker
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MarvelDatabasePrePopulateWorkerTest {
    @Inject
    lateinit var dao: MovieDao

    @get:Rule
    var rule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        rule.inject()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
    }

    @Test
    fun marvelDatabasePrePopulateWorker_doWork_success() = runTest {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val worker = TestListenableWorkerBuilder<MarvelDatabasePrePopulateWorker>(context)
            .setWorkerFactory(MarvelDatabasePrePopulateWorkerFactory(dao))
            .build()

        assertThat(worker.doWork(), `is`(Result.success()))
    }
}
