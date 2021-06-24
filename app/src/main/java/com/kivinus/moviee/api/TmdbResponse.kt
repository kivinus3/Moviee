package com.kivinus.moviee.api

import com.google.gson.annotations.SerializedName

data class TmdbResponse(
    val page: Int,
    @SerializedName("results")
    val tmdbMoviesListResponse: List<TmdbMovieResponse>,
    val total_pages: Int,
    val total_results: Int
)