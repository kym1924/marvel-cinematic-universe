package com.kimym.marvel.di

import com.kimym.marvel.data.repository.DetailRepository
import com.kimym.marvel.data.repository.DetailRepositoryImpl
import com.kimym.marvel.data.repository.MovieRepository
import com.kimym.marvel.data.repository.MovieRepositoryImpl
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
    abstract fun bindDetailRepository(
        detailRepositoryImpl: DetailRepositoryImpl
    ): DetailRepository
}
