package com.example.favorite

import androidx.lifecycle.*
import com.example.core.domain.usecase.CatalogueUseCase
import com.example.core.utils.CatalogueType

class FavoriteViewModel(
    private val catalogueUseCase: CatalogueUseCase
) : ViewModel() {

    private val _filter = MutableLiveData<CatalogueType>()

    fun filter(filterType: CatalogueType) {
        _filter.value = filterType
    }

    val favorites = _filter.switchMap { type ->
        catalogueUseCase.getFavorites(type).asLiveData()
    }

    init {
        _filter.value = CatalogueType.ALL
    }
}