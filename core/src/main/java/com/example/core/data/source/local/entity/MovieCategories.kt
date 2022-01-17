package com.example.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.utils.CatalogueType

@Entity(tableName = "popularMovies")
data class PopularMovieEntities (
    @PrimaryKey
    val id: Int,
    val posterPath: String? = null,
    val catalogueType: CatalogueType = CatalogueType.MOVIE
)

@Entity(tableName = "topRatedMovies")
data class TopRatedMovieEntities (
    @PrimaryKey
    val id: Int,
    val posterPath: String? = null,
    val catalogueType: CatalogueType = CatalogueType.MOVIE
)

@Entity(tableName = "nowPlayingMovies")
data class NowPlayingMovieEntities (
    @PrimaryKey
    val id: Int,
    val posterPath: String? = null,
    val catalogueType: CatalogueType = CatalogueType.MOVIE
)

@Entity(tableName = "upComingMovies")
data class UpComingMovieEntities (
    @PrimaryKey
    val id: Int,
    val posterPath: String? = null,
    val catalogueType: CatalogueType = CatalogueType.MOVIE
)