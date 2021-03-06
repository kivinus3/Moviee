package com.kivinus.moviee.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kivinus.moviee.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}