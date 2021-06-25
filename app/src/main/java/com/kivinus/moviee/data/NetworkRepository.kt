package com.kivinus.moviee.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.kivinus.moviee.api.TmdbApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository
@Inject constructor(private val tmdbApi: TmdbApiService) {

    fun getMoviesByQuery(query: String) =
        Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = 20,
                maxSize = 60
            ),
            pagingSourceFactory = {MoviesPagingSource(query, tmdbApi)}
        ).flow


    suspend fun getMovieById(id: Int) {
        tmdbApi.getMovieById(id)
    }

}