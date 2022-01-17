package com.example.core.domain.usecase

import com.example.core.domain.model.CatalogueResult
import com.example.core.domain.model.Favorite
import com.example.core.domain.model.Movie
import com.example.core.domain.model.Tv
import com.example.core.domain.repository.CatalogueRepositoryImpl
import com.example.core.utils.CatalogueType
import com.example.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class CatalogueInteractor(
    private val catalogueRepository: CatalogueRepositoryImpl
): CatalogueUseCase {

    override fun getPopularMovie(): Flow<Resource<List<CatalogueResult>>> =
        catalogueRepository.getPopularMovie()

    override fun getTopRatedMovie(): Flow<Resource<List<CatalogueResult>>> =
        catalogueRepository.getTopRatedMovie()

    override fun getNowPlayingMovie(): Flow<Resource<List<CatalogueResult>>> =
        catalogueRepository.getNowPlayingMovie()

    override fun getUpComingMovie(): Flow<Resource<List<CatalogueResult>>> =
        catalogueRepository.getUpComingMovie()

    override fun setFavoriteMovie(movie: Movie, newState: Boolean) =
        catalogueRepository.setFavoriteMovie(movie, newState)

    override fun getMovieById(id: Int) = catalogueRepository.getMovieById(id)

    override fun getPopularTvShow(): Flow<Resource<List<CatalogueResult>>> =
        catalogueRepository.getPopularTv()

    override fun getTopRatedTvShow(): Flow<Resource<List<CatalogueResult>>> =
        catalogueRepository.getTopRatedTv()

    override fun getOnAirTvShow(): Flow<Resource<List<CatalogueResult>>> =
        catalogueRepository.getOnAirTv()

    override fun getAiringTodayTvShow(): Flow<Resource<List<CatalogueResult>>> =
        catalogueRepository.getAiringTodayTv()

    override fun setFavoriteTv(tvShow: Tv, newState: Boolean) =
        catalogueRepository.setFavoriteTv(tvShow, newState)

    override fun getTvById(id: Int) =
        catalogueRepository.getTvById(id)

    override fun setFavorite(favorite: Favorite) =
        catalogueRepository.setFavorite(favorite)

    override fun deteleFavorite(favorite: Favorite) =
        catalogueRepository.deleteFavorite(favorite)

    override fun getFavorites(query: CatalogueType): Flow<List<Favorite>> =
        catalogueRepository.getFavoriteMovie(query)

}