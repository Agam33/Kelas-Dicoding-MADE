package com.example.myapplication.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.CatalogueUseCase

class MovieViewModel(
    catalogueUseCase: CatalogueUseCase
): ViewModel() {
    val popularMovies = catalogueUseCase.getPopularMovie().asLiveData()
    val topRatedMovies = catalogueUseCase.getTopRatedMovie().asLiveData()
    val nowPlayingMovies = catalogueUseCase.getNowPlayingMovie().asLiveData()
    val upComingMovies = catalogueUseCase.getUpComingMovie().asLiveData()
}