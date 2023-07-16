package com.kimym.marvel.core.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.kimym.marvel.core.database.MarvelDao
import com.kimym.marvel.core.database.MarvelDatabase
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
                    val request = OneTimeWorkRequestBuilder<MarvelDatabasePrePopulateWorker>().build()
                    WorkManager.getInstance(context).enqueue(request)
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
