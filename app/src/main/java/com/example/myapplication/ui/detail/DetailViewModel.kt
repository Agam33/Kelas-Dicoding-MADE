package com.example.myapplication.ui.detail

import androidx.lifecycle.*
import com.example.core.domain.model.Favorite
import com.example.core.domain.usecase.CatalogueUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DetailViewModel(
    private val catalogueUseCase: CatalogueUseCase
): ViewModel() {

    fun isFavorite(favoriteId: Int) = catalogueUseCase.checkFavorite(favoriteId).asLiveData()

    fun getMovieById(id: Int) = catalogueUseCase.getMovieById(id).asLiveData()

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