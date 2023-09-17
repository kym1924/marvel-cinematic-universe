package com.kimym.marvel.core.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kimym.marvel.core.model.MovieAndRating

object MarvelDatabaseMigrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE VIEW `MovieAndRating` AS " + MovieAndRating.QUERY)
        }
    }
}
