package com.kimym.marvel.worker

import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.ListenableWorker.Result
import androidx.work.testing.TestListenableWorkerBuilder
import com.kimym.marvel.database.MarvelDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MarvelDatabaseWorkerTest {
    @Inject
    lateinit var dao: MarvelDao

    @get:Rule
    var rule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        rule.inject()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun marvelDatabaseWorker_doWork_success() = runTest {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val worker = TestListenableWorkerBuilder<MarvelDatabaseWorker>(context)
            .setWorkerFactory(MarvelDatabaseWorkerFactory(dao))
            .build()

        assertThat(worker.doWork(), `is`(Result.success()))
    }
}
