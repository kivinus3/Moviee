package com.kivinus.moviee.api

data class TmdbResponse(
    val page: Int,
    val tmdbMovies: List<TmdbMovie>,
    val total_pages: Int,
    val total_results: Int
)