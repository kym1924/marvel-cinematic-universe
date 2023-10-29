package com.kimym.marvel.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kimym.marvel.core.database.dao.MovieAndRatingDao
import com.kimym.marvel.core.database.dao.MovieDao
import com.kimym.marvel.core.database.dao.RatingDao
import com.kimym.marvel.core.database.entity.MovieAndRatingView
import com.kimym.marvel.core.database.entity.MovieEntity
import com.kimym.marvel.core.database.entity.RatingEntity

@Database(
    entities = [MovieEntity::class, RatingEntity::class],
    views = [MovieAndRatingView::class],
    version = 2
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    abstract fun movieAndRatingDao(): MovieAndRatingDao

    abstract fun ratingDao(): RatingDao
}
