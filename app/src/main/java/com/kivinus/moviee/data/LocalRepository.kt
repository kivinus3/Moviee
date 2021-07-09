package com.kivinus.moviee.data

import com.kivinus.moviee.db.MovieDao
import com.kivinus.moviee.model.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository
@Inject constructor(private val movieDao: MovieDao) {

    suspend fun addMovie(movie: MovieEntity) {
        if (!movie.isLiked) {
            deleteMovie(movie)
        } else movieDao.addMovie(movie)
    }

    private suspend fun deleteMovie(movie: MovieEntity) =
        movieDao.deleteMovie(movie)

    fun getMovieById(id: Int): Flow<MovieEntity> =
        movieDao.getById(id)


    fun getFavoriteMovies(): Flow<List<MovieEntity>> =
        movieDao.getAllFavorite()

    fun isRowIsExist(id: Int): Flow<Boolean> =
        movieDao.isRowIsExist(id)

}