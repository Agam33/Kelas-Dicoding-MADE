package com.example.core.domain.usecase

import com.example.core.domain.model.CatalogueResult
import com.example.core.domain.model.Favorite
import com.example.core.domain.model.Movie
import com.example.core.domain.model.Tv
import com.example.core.utils.CatalogueType
import com.example.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CatalogueUseCase {
    fun getPopularMovie(): Flow<Resource<List<CatalogueResult>>>
    fun getTopRatedMovie(): Flow<Resource<List<CatalogueResult>>>
    fun getNowPlayingMovie(): Flow<Resource<List<CatalogueResult>>>
    fun getUpComingMovie(): Flow<Resource<List<CatalogueResult>>>
    fun setFavoriteMovie(movie: Movie, newState: Boolean)
    fun getMovieById(id: Int): Flow<Resource<Movie>>

    fun  getPopularTvShow(): Flow<Resource<List<CatalogueResult>>>
    fun  getTopRatedTvShow(): Flow<Resource<List<CatalogueResult>>>
    fun  getOnAirTvShow(): Flow<Resource<List<CatalogueResult>>>
    fun  getAiringTodayTvShow(): Flow<Resource<List<CatalogueResult>>>
    fun setFavoriteTv(tvShow: Tv, newState: Boolean)
    fun getTvById(id: Int): Flow<Resource<Tv>>

    fun setFavorite(favorite: Favorite)
    fun deteleFavorite(favorite: Favorite)
    fun getFavorites(query: CatalogueType): Flow<List<Favorite>>
}