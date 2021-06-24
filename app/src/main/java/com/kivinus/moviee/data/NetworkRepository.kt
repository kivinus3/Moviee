package com.kivinus.moviee.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kivinus.moviee.api.TmdbApiService
import com.kivinus.moviee.api.TmdbMovieResponse
import com.kivinus.moviee.api.TmdbRequestTypes
import kotlinx.coroutines.CoroutineScope
import java.util.concurrent.Flow
import javax.inject.Inject

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