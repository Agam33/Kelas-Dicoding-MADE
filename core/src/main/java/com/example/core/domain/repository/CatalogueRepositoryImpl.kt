package com.example.core.domain.repository

import com.example.core.domain.model.CatalogueResult
import com.example.core.domain.model.Favorite
import com.example.core.domain.model.Movie
import com.example.core.domain.model.Tv
import com.example.core.utils.CatalogueType
import com.example.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CatalogueRepositoryImpl {

    fun getPopularMovie(): Flow<Resource<List<CatalogueResult>>>
    fun getTopRatedMovie(): Flow<Resource<List<CatalogueResult>>>
    fun getNowPlayingMovie(): Flow<Resource<List<CatalogueResult>>>
    fun getUpComingMovie(): Flow<Resource<List<CatalogueResult>>>
    fun setFavoriteMovie(movie: Movie, newState: Boolean)
    fun getMovieById(id: Int): Flow<Resource<Movie>>

    fun getPopularTv(): Flow<Resource<List<CatalogueResult>>>
    fun getTopRatedTv(): Flow<Resource<List<CatalogueResult>>>
    fun getOnAirTv(): Flow<Resource<List<CatalogueResult>>>
    fun getAiringTodayTv(): Flow<Resource<List<CatalogueResult>>>
    fun setFavoriteTv(tv: Tv, newState: Boolean)
    fun getTvById(id: Int): Flow<Resource<Tv>>

    fun setFavorite(favorite: Favorite)
    fun deleteFavorite(favorite: Favorite)
    fun getFavoriteMovie(filter: CatalogueType): Flow<List<Favorite>>
}