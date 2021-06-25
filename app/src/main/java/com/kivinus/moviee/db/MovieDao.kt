package com.kivinus.moviee.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kivinus.moviee.model.MovieEntity

interface MovieDao {

    @Query("SELECT * FROM movies WHERE isLiked is 1")
    fun selectAllFavorite(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE id=:id")
    fun selectById(id: Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: MovieEntity)

    @Delete
    fun deleteMovie(movie: MovieEntity)

}