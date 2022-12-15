package com.kimym.marvel.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.kimym.marvel.database.MarvelDao
import com.kimym.marvel.database.MarvelDatabase
import com.kimym.marvel.worker.MarvelDatabaseWorker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
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
                    val request = OneTimeWorkRequestBuilder<MarvelDatabaseWorker>().build()
                    WorkManager.getInstance(context).enqueue(request)
                    Timber.d("Database is initialized")
                }
            }
        ).build()
    }

    @Provides
    @Singleton
    fun provideMarvelDao(db: MarvelDatabase): MarvelDao {
        return db.marvelDao()
    }
}
