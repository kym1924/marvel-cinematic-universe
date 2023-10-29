package com.kimym.marvel.data.di

import android.content.Context
import com.kimym.marvel.core.datastore.MarvelDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {
    @Provides
    @Singleton
    fun provideMarvelDatastore(
        @ApplicationContext context: Context
    ): MarvelDatastore {
        return MarvelDatastore(context)
    }
}
