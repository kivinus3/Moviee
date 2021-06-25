package com.kivinus.moviee.model

data class TmdbMovieResponse(
    val id: Int,
    val backdrop_path: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double
)