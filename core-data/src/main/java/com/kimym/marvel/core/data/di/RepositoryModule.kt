package com.kimym.marvel.core.data.di

import com.kimym.marvel.data.detail.DetailRepository
import com.kimym.marvel.data.detail.DetailRepositoryImpl
import com.kimym.marvel.data.favorite.FavoriteRepository
import com.kimym.marvel.data.favorite.FavoriteRepositoryImpl
import com.kimym.marvel.data.movie.MovieRepository
import com.kimym.marvel.data.movie.MovieRepositoryImpl
import com.kimym.marvel.data.rating.RatingRepository
import com.kimym.marvel.data.rating.RatingRepositoryImpl
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
    abstract fun bindFavoriteRepository(
        favoriteRepositoryImpl: FavoriteRepositoryImpl
    ): FavoriteRepository

    @Binds
    @Singleton
    abstract fun bindDetailRepository(
        detailRepositoryImpl: DetailRepositoryImpl
    ): DetailRepository

    @Binds
    @Singleton
    abstract fun bindRatingRepository(
        ratingRepositoryImpl: RatingRepositoryImpl
    ): RatingRepository
}
