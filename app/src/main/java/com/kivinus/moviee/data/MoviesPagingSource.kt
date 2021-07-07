package com.kivinus.moviee.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kivinus.moviee.api.TmdbApiService
import com.kivinus.moviee.model.TmdbMovieResponse
import com.kivinus.moviee.api.TmdbRequestTypes
import retrofit2.HttpException
import java.io.IOException

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

            // requests
            val movies =
                if (query == TmdbRequestTypes.POPULAR || query.isEmpty()) {
                    val response = apiService.getPopularMovies(page = page)
                    response.tmdbMoviesListResponse
                }

                // request top rated movies
                else if (query == TmdbRequestTypes.TOP_RATED) {
                    val response = apiService.getTopRatedMovies(page = page)
                    response.tmdbMoviesListResponse
                } else {
                    val response = apiService.searchMovies(page = page, query = query)
                    response.tmdbMoviesListResponse
                }

            // return page
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.size < PAGE_SIZE) null else page + 1
            )

        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }


}