package com.example.core.utils

import com.example.core.data.source.local.entity.FavoriteEntities
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.entity.TvEntity

import com.example.core.data.source.remote.response.DetailMovieResponse
import com.example.core.data.source.remote.response.DetailTvResponse
import com.example.core.domain.model.Favorite
import com.example.core.domain.model.Movie
import com.example.core.domain.model.Tv

object DataMapper {

    fun mapMovieEntityToDomain(movieEntity: MovieEntity?): Movie {
        if(movieEntity == null) return Movie()
        return Movie (
            movieEntity.id,
            movieEntity.name,
            movieEntity.imgUrl,
            movieEntity.runtime,
            movieEntity.language,
            movieEntity.releaseDate,
            movieEntity.overview,
            movieEntity.popularity,
            movieEntity.voteAverage,
            movieEntity.isFavorite,
            movieEntity.catalogueType
        )
    }

    fun mapTvEntityToDomain(tvEntity: TvEntity?): Tv {
        if(tvEntity == null) return Tv()
        return Tv (
            tvEntity.id,
            tvEntity.name,
            tvEntity.imgUrl,
            tvEntity.language,
            tvEntity.totalEpisode,
            tvEntity.overview,
            tvEntity.popularity,
            tvEntity.voteAverage,
            tvEntity.isFavorite,
            tvEntity.catalogueType
        )
    }


    fun mapDetailMovieResponseToEntity(movieResponse: DetailMovieResponse) = MovieEntity (
        movieResponse.id,
        movieResponse.name,
        movieResponse.imgUrl,
        movieResponse.runtime,
        if(movieResponse.language.isEmpty()) "" else movieResponse.language[0].englishName,
        movieResponse.releaseDate,
        movieResponse.overview,
        movieResponse.popularity,
        movieResponse.voteAverage
    )

    fun mapDetailTvResponseToEntity(tvResponse: DetailTvResponse) =  TvEntity(
        tvResponse.id,
        tvResponse.name,
        tvResponse.imgUrl,
        tvResponse.episode,
        if(tvResponse.language.isEmpty()) "" else tvResponse.language[0].englishName,
        tvResponse.overview,
        tvResponse.popularity,
        tvResponse.voteAverage,
    )

    fun mapMovieDomainToEntity(movie: Movie) =  MovieEntity (
        movie.id,
        movie.name,
        movie.imgUrl,
        movie.runtime,
        movie.language,
        movie.releaseDate,
        movie.overview,
        movie.popularity,
        movie.voteAverage,
        movie.isFavorite,

    )

    fun mapTvDomainToEntity(tvShow: Tv) = TvEntity(
        tvShow.id,
        tvShow.name,
        tvShow.imgUrl,
        tvShow.totalEpisode,
        tvShow.language,
        tvShow.overview,
        tvShow.popularity,
        tvShow.voteAverage,
        tvShow.isFavorite
    )

    fun mapFavoriteEntityToDomain(favorite: FavoriteEntities) =
        Favorite(
            favorite.id,
            favorite.posterPath,
            favorite.isFavorite,
            favorite.catalogueType
        )

    fun mapFavoriteDomainToEntity(favorite: Favorite) = FavoriteEntities(
        favorite.id,
        favorite.posterPath,
        favorite.isFavorite,
        favorite.catalogueType
    )
}