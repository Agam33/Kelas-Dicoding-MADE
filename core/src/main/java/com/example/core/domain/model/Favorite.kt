package com.example.core.domain.model

data class Favorite(
    var id: Int = 0,
    var posterPath: String = "",
    var isFavorite: Boolean = false,
    var catalogueType: Int
)
