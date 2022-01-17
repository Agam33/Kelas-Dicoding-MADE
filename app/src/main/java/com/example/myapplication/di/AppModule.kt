package com.example.myapplication.di

import com.example.core.domain.usecase.CatalogueInteractor
import com.example.core.domain.usecase.CatalogueUseCase
import com.example.myapplication.ui.detail.DetailViewModel
import com.example.myapplication.ui.movie.MovieViewModel
import com.example.myapplication.ui.tvshow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CatalogueUseCase> { CatalogueInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}