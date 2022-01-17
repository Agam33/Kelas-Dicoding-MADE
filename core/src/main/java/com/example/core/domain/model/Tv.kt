package com.example.core.domain.model

import com.example.core.utils.CatalogueType

data class Tv(
    var id: Int = 0,
    var name: String="",
    var imgUrl: String = "",
    var language: String = "",
    var totalEpisode: Int = 0,
    var overview: String = "",
    var popularity: Double = 0.0,
    var voteAverage: Double = 0.0,
    var isFavorite: Boolean = false,
    val catalogueType: CatalogueType = CatalogueType.TV
)
