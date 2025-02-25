package com.kimym.marvel.core.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kimym.marvel.core.database.entity.MovieAndRatingView

object MarvelDatabaseMigrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("CREATE VIEW `MovieAndRating` AS " + MovieAndRatingView.QUERY)
        }
    }

    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("DROP VIEW IF EXISTS `MovieAndRating`")
            db.execSQL("CREATE VIEW `MovieAndRating` AS " + MovieAndRatingView.QUERY)
        }
    }
}
