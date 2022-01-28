package com.example.core.utils

import com.example.core.data.source.local.entity.FavoriteEntities
import com.example.core.data.source.remote.response.DetailMovieResponse
import com.example.core.data.source.remote.response.DetailTvResponse
import com.example.core.data.source.remote.response.ResultResponse
import com.example.core.domain.model.*

object DataMapper {

    fun mapResultResponseToDomain(resultResponse: ResultResponse?): CatalogueResult {
        if(resultResponse == null) return CatalogueResult()
        return CatalogueResult(
            resultResponse.id,
            resultResponse.posterPath
        )
    }

    fun mapDetailMovieResponseToDomain(movie: DetailMovieResponse?): Movie {
        if(movie == null) return Movie()
        return Movie (
            movie.id,
            movie.name,
            movie.imgUrl,
            movie.runtime,
            if(movie.language.isEmpty()) "" else movie.language[0].englishName,
            movie.releaseDate,
            movie.overview,
            movie.popularity,
            movie.voteAverage,
        )
    }

    fun mapDetailTvResponseToDomain(tv: DetailTvResponse?): Tv {
        if(tv == null) return Tv()
        return Tv (
            tv.id,
            tv.name,
            tv.imgUrl,
            if(tv.language.isEmpty()) "" else tv.language[0].englishName,
            tv.episode,
            tv.overview,
            tv.popularity,
            tv.voteAverage,
        )
    }

    fun mapFavoriteEntityToDomain(favorite: FavoriteEntities?): Favorite {
        if (favorite == null) return Favorite()
        return Favorite(
            favorite.id,
            favorite.posterPath,
            favorite.isFavorite,
            favorite.catalogueType
        )
    }

    fun mapFavoriteDomainToEntity(favorite: Favorite) = FavoriteEntities(
        favorite.id,
        favorite.posterPath,
        favorite.isFavorite,
        favorite.catalogueType
    )
}