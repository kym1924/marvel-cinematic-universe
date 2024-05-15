package com.kimym.marvel.core.datastore

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MarvelDatastoreTest {
    private lateinit var datastore: MarvelDatastore

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        datastore = MarvelDatastore(context)
    }

    @Test
    fun marvelDatastore_getAppearance() = runTest {
        val expected = 1
        datastore.setAppearance(expected)
        assertEquals(expected, datastore.getAppearance().first())
    }
}
