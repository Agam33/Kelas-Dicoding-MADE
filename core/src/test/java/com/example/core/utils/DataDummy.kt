package com.example.core.utils

import com.example.core.data.source.local.entity.NowPlayingMovieEntities
import com.example.core.data.source.local.entity.PopularMovieEntities
import com.example.core.data.source.local.entity.TopRatedMovieEntities
import com.example.core.data.source.local.entity.UpComingMovieEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object DataDummy {

    fun popularMovies(): Flow<List<PopularMovieEntities>> {
      val dummies = ArrayList<PopularMovieEntities>().apply {
           add(
               PopularMovieEntities(
                   id = 1,
                   posterPath = "",
                   catalogueType = CatalogueType.MOVIE
           ))
           add(
               PopularMovieEntities(
                   id = 2,
                   posterPath = "",
                   catalogueType = CatalogueType.MOVIE
               ))
           add(
               PopularMovieEntities(
                   id = 3,
                   posterPath = "",
                   catalogueType = CatalogueType.MOVIE
               ))
           add(
               PopularMovieEntities(
                   id = 4,
                   posterPath = "",
                   catalogueType = CatalogueType.MOVIE
               ))
       }
       return flow {
           emit(dummies)
       }
   }

    fun topRatedMovies(): List<TopRatedMovieEntities> {
        val dummies = ArrayList<TopRatedMovieEntities>().apply {
            add(
                TopRatedMovieEntities(
                    id = 1,
                    posterPath = "",
                    catalogueType = CatalogueType.MOVIE
                ))
            add(
                TopRatedMovieEntities(
                    id = 2,
                    posterPath = "",
                    catalogueType = CatalogueType.MOVIE
                ))
            add(
                TopRatedMovieEntities(
                    id = 3,
                    posterPath = "",
                    catalogueType = CatalogueType.MOVIE
                ))
            add(
                TopRatedMovieEntities(
                    id = 4,
                    posterPath = "",
                    catalogueType = CatalogueType.MOVIE
                ))
        }
        return dummies
    }

    fun nowPlayingMovies(): List<NowPlayingMovieEntities> {
        val dummies = ArrayList<NowPlayingMovieEntities>().apply {
            add(
                NowPlayingMovieEntities(
                    id = 1,
                    posterPath = "",
                    catalogueType = CatalogueType.MOVIE
                ))
            add(
                NowPlayingMovieEntities(
                    id = 2,
                    posterPath = "",
                    catalogueType = CatalogueType.MOVIE
                ))
            add(
                NowPlayingMovieEntities(
                    id = 3,
                    posterPath = "",
                    catalogueType = CatalogueType.MOVIE
                ))
            add(
                NowPlayingMovieEntities(
                    id = 4,
                    posterPath = "",
                    catalogueType = CatalogueType.MOVIE
                ))
        }
        return dummies
    }

    fun upComingMovies(): List<UpComingMovieEntities> {
        val dummies = ArrayList<UpComingMovieEntities>().apply {
            add(
                UpComingMovieEntities(
                    id = 1,
                    posterPath = "",
                    catalogueType = CatalogueType.MOVIE
                ))
            add(
                UpComingMovieEntities(
                    id = 2,
                    posterPath = "",
                    catalogueType = CatalogueType.MOVIE
                ))
            add(
                UpComingMovieEntities(
                    id = 3,
                    posterPath = "",
                    catalogueType = CatalogueType.MOVIE
                ))
            add(
                UpComingMovieEntities(
                    id = 4,
                    posterPath = "",
                    catalogueType = CatalogueType.MOVIE
                ))
        }
        return dummies
    }



}