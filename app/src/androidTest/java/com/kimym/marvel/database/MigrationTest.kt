package com.kimym.marvel.database

import androidx.room.testing.MigrationTestHelper
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.kimym.marvel.core.database.MarvelDatabase
import com.kimym.marvel.core.database.MarvelDatabaseMigrations.MIGRATION_1_2
import org.hamcrest.CoreMatchers.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MigrationTest {
    private val databaseName = "migration-test"

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        MarvelDatabase::class.java
    )

    @Test
    fun marvelDatabase_migration_from1_to2() {
        val query = "SELECT * FROM sqlite_master WHERE type='view' AND name='MovieAndRating'"

        helper.createDatabase(databaseName, 1).use { db ->
            db.query(query).use { cursor ->
                assertThat(cursor.moveToFirst(), `is`(false))
            }
        }

        helper.runMigrationsAndValidate(databaseName, 2, true, MIGRATION_1_2).use { db ->
            db.query(query).use { cursor ->
                assertThat(cursor.moveToFirst(), `is`(true))
            }
        }
    }
}
