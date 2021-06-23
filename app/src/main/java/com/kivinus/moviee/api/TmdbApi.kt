package com.kivinus.moviee.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    companion object {
        const val API_KEY = "b51a38bfcb54f6d148c4662c2ca53aec"
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("/movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): TmdbResponse

    @GET("/movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ) : TmdbResponse

    @GET("/movie/{movie_id}")
    fun getMovieById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ) : TmdbMovie

}