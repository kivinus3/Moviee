package com.kivinus.moviee.api

import com.kivinus.moviee.model.MovieEntity
import com.kivinus.moviee.model.TmdbMovieResponse
import com.kivinus.moviee.util.EntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapperMovieTmdb
@Inject constructor()
    : EntityMapper<TmdbMovieResponse, MovieEntity> {

    override fun mapFromEntity(entity: TmdbMovieResponse): MovieEntity = MovieEntity(
        id = entity.id,
        title = entity.title,
        posterUrl = entity.poster_path,
        backdropUrl = entity.backdrop_path,
        releaseDate = entity.release_date,
        rating = entity.vote_average,
        overview = entity.overview,

        isLiked = false
    )

    override fun mapToEntity(domainModel: MovieEntity): TmdbMovieResponse = TmdbMovieResponse(
        id = domainModel.id,
        title = domainModel.title,
        poster_path = domainModel.posterUrl,
        backdrop_path = domainModel.backdropUrl,
        release_date = domainModel.releaseDate,
        vote_average = domainModel.rating,
        overview = domainModel.overview
    )

}