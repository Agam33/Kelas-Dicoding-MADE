package com.example.myapplication.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.Favorite
import com.example.core.domain.model.Movie
import com.example.core.domain.model.Tv
import com.example.core.domain.usecase.CatalogueUseCase

class DetailViewModel(
    private val catalogueUseCase: CatalogueUseCase
): ViewModel() {

    fun setFavoriteMovie(movie: Movie) {
        val newState = !movie.isFavorite
        catalogueUseCase.setFavoriteMovie(movie, newState)
    }

    fun getMovieById(id: Int) = catalogueUseCase.getMovieById(id).asLiveData()

    fun setFavoriteTv(tv: Tv)  {
        val newState = !tv.isFavorite
        catalogueUseCase.setFavoriteTv(tv, newState)
    }
    fun getTvById(id: Int) = catalogueUseCase.getTvById(id).asLiveData()

    fun setFavorite(favorite: Favorite) = catalogueUseCase.setFavorite(favorite)
    fun deleteFavorite(favorite: Favorite) = catalogueUseCase.deteleFavorite(favorite)
}