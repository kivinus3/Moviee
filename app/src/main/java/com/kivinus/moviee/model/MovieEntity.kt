package com.kivinus.moviee.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterUrl: String,
    val releaseDate: String,
    val rating: Double,
    val overview: String,
    var isLiked: Boolean = false
)
