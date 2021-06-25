package com.kivinus.moviee.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kivinus.moviee.api.TmdbApiService
import com.kivinus.moviee.model.TmdbMovieResponse
import com.kivinus.moviee.api.TmdbRequestTypes
import retrofit2.HttpException

class MoviesPagingSource
    (
    private val query: String,
    private val apiService: TmdbApiService
) : PagingSource<Int, TmdbMovieResponse>() {

    private companion object {
        const val PAGE_SIZE = 20
    }

    override fun getRefreshKey(state: PagingState<Int, TmdbMovieResponse>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TmdbMovieResponse> {
        val page: Int = params.key ?: 1

        return try {

            var movies = emptyList<TmdbMovieResponse>()

            // request popular movies
            if (query == TmdbRequestTypes.POPULAR || query.isEmpty()) {
                val response = apiService.getPopularMovies(page = page)
                movies = response.tmdbMoviesListResponse
            }

            // request top rated movies
            if (query == TmdbRequestTypes.TOP_RATED) {
                val response = apiService.getTopRatedMovies(page = page)
                movies = response.tmdbMoviesListResponse
            }

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.size < PAGE_SIZE) null else page + 1
            )

        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }


}