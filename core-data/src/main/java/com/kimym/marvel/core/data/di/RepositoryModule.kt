package com.kimym.marvel.core.data.di

import com.kimym.marvel.core.data.repository.MovieRepository
import com.kimym.marvel.core.data.repository.MovieRepositoryImpl
import com.kimym.marvel.core.data.repository.RatingRepository
import com.kimym.marvel.core.data.repository.RatingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @Singleton
    abstract fun bindRatingRepository(
        ratingRepositoryImpl: RatingRepositoryImpl
    ): RatingRepository
}
