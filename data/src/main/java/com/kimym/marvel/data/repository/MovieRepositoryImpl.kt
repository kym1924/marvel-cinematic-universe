package com.kimym.marvel.data.repository

import com.kimym.marvel.core.database.dao.MovieAndRatingDao
import com.kimym.marvel.core.database.dao.MovieDao
import com.kimym.marvel.data.di.IODispatcher
import com.kimym.marvel.domain.model.Movie
import com.kimym.marvel.domain.model.MovieAndRating
import com.kimym.marvel.domain.model.MovieDetail
import com.kimym.marvel.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val movieAndRatingDao: MovieAndRatingDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {
    override fun getMovies(): Flow<List<Movie>> {
        return movieDao.getMovies().map { list ->
            list.map { entity ->
                entity.run {
                    Movie(id = id, title = title, image = image)
                }
            }
        }.flowOn(ioDispatcher)
    }

    override fun getMovieAndRatings(): Flow<List<MovieAndRating>> {
        return movieAndRatingDao.getMovieAndRatings().map { list ->
            list.map { view ->
                view.run {
                    MovieAndRating(id = id, title = title, image = image, rating = rating)
                }
            }
        }.flowOn(ioDispatcher)
    }

    override fun getMovie(id: Int): Flow<MovieDetail> {
        return movieDao.getMovie(id).map { entity ->
            entity.run {
                MovieDetail(
                    id = id,
                    title = title,
                    content = content,
                    release = release,
                    runningTime = runningTime,
                    image = image
                )
            }
        }.flowOn(ioDispatcher)
    }

    override fun getMovieTitle(id: Int): Flow<String> {
        return movieDao.getMovieTitle(id).flowOn(ioDispatcher)
    }
}
