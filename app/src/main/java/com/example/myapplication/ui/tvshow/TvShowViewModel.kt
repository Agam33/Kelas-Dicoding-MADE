package com.example.myapplication.ui.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.CatalogueUseCase

class TvShowViewModel(
    catalogueUseCase: CatalogueUseCase
): ViewModel() {

    val popularTvShows = catalogueUseCase.getPopularTvShow().asLiveData()
    val topRatedTvShows = catalogueUseCase.getTopRatedTvShow().asLiveData()
    val onAirTvShows = catalogueUseCase.getOnAirTvShow().asLiveData()
    val airingTodayTvShow = catalogueUseCase.getAiringTodayTvShow().asLiveData()
}