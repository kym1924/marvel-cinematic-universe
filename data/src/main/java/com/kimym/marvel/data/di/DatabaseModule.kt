package com.kimym.marvel.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.kimym.marvel.core.database.MarvelDatabase
import com.kimym.marvel.core.database.MarvelDatabaseMigrations
import com.kimym.marvel.core.database.dao.MovieAndRatingDao
import com.kimym.marvel.core.database.dao.MovieDao
import com.kimym.marvel.core.database.dao.RatingDao
import com.kimym.marvel.core.worker.MarvelDatabasePrePopulateWorker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): MarvelDatabase {
        return Room.databaseBuilder(
            context,
            MarvelDatabase::class.java,
            "Marvel.db"
        ).addCallback(
            object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val worker = OneTimeWorkRequestBuilder<MarvelDatabasePrePopulateWorker>()
                    WorkManager.getInstance(context).enqueue(worker.build())
                }
            }
        ).addMigrations(
            MarvelDatabaseMigrations.MIGRATION_1_2
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(db: MarvelDatabase): MovieDao {
        return db.movieDao()
    }

    @Provides
    @Singleton
    fun provideMovieAndRatingDao(db: MarvelDatabase): MovieAndRatingDao {
        return db.movieAndRatingDao()
    }

    @Provides
    @Singleton
    fun provideRatingDao(db: MarvelDatabase): RatingDao {
        return db.ratingDao()
    }
}
