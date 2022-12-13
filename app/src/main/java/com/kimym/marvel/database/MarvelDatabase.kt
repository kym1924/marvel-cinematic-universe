package com.kimym.marvel.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kimym.marvel.database.model.Movie
import com.kimym.marvel.database.model.Rating

@Database(
    entities = [Movie::class, Rating::class],
    version = 1,
    exportSchema = false
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun marvelDao(): MarvelDao
}
