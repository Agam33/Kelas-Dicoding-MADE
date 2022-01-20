package com.example.myapplication.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Favorite
import com.example.core.domain.model.Movie
import com.example.core.domain.model.Tv
import com.example.core.domain.usecase.CatalogueUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DetailViewModel(
    private val catalogueUseCase: CatalogueUseCase
): ViewModel() {

    fun setFavoriteMovie(movie: Movie) {
        viewModelScope.launch(IO) {
            val newState = !movie.isFavorite
            catalogueUseCase.setFavoriteMovie(movie, newState)
        }
    }

    fun getMovieById(id: Int) = catalogueUseCase.getMovieById(id).asLiveData()

    fun setFavoriteTv(tv: Tv)  {
        viewModelScope.launch(IO) {
            val newState = !tv.isFavorite
            catalogueUseCase.setFavoriteTv(tv, newState)
        }
    }

    fun getTvById(id: Int) = catalogueUseCase.getTvById(id).asLiveData()

    fun setFavorite(favorite: Favorite) {
        viewModelScope.launch(IO) {
            catalogueUseCase.setFavorite(favorite)
        }
    }
    fun deleteFavorite(favorite: Favorite){
        viewModelScope.launch(IO) {
            catalogueUseCase.deleteFavorite(favorite)
        }
    }
}