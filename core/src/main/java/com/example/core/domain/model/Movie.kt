package com.example.core.domain.model

import com.example.core.utils.CatalogueType


data class Movie(
    var id: Int = 0,
    var name: String="",
    var imgUrl: String = "",
    var runtime: Int = 0,
    var language: String = "",
    var releaseDate: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var voteAverage: Double = 0.0,
    var isFavorite: Boolean = false,
    val catalogueType: CatalogueType = CatalogueType.MOVIE
)

