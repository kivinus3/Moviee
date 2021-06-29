package com.kivinus.moviee.db

import androidx.room.*
import com.kivinus.moviee.model.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieDao {

    @Query("SELECT * FROM movies WHERE isLiked is 1")
    fun getAllFavorite(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id=:id")
    fun getById(id: Int): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

}