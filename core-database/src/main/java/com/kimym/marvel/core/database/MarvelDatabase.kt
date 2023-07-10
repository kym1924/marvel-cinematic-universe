package com.kimym.marvel.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kimym.marvel.core.model.Movie
import com.kimym.marvel.core.model.Rating

@Database(
    entities = [Movie::class, Rating::class],
    version = 1,
    exportSchema = false
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun marvelDao(): MarvelDao
}
