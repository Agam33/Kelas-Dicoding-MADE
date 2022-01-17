package com.example.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.utils.CatalogueType

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    var id: Int = -1,
    var name: String="",
    var imgUrl: String = "",
    var runtime: Int = -1,
    var language: String = "English",
    var releaseDate: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var voteAverage: Double = 0.0,
    var isFavorite: Boolean = false,
    val catalogueType: CatalogueType = CatalogueType.MOVIE
)

