package com.kivinus.moviee.data

import com.kivinus.moviee.db.MovieDao
import com.kivinus.moviee.model.MovieEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository
@Inject constructor(private val movieDao: MovieDao) {

    suspend fun addMovie(movie: MovieEntity): Job =
        withContext(Dispatchers.IO) {
            launch { movieDao.addMovie(movie) }
        }

    suspend fun deleteMovie(movie: MovieEntity): Job =
        withContext(Dispatchers.IO) {
            launch { movieDao.deleteMovie(movie) }
        }

    suspend fun updateMovie(movie: MovieEntity): Job =
        withContext(Dispatchers.IO) {
            launch { movieDao.updateMovie(movie) }
        }

    fun getMovieById(id: Int): Flow<MovieEntity> =
        movieDao.getById(id)


    fun getFavoriteMovies(): Flow<List<MovieEntity>> =
        movieDao.getAllFavorite()

}