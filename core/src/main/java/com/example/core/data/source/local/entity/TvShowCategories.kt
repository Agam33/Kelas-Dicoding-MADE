package com.example.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.utils.CatalogueType

@Entity(tableName = "popularTv")
data class PopularTvEntities (
    @PrimaryKey
    val id: Int,
    val posterPath: String?  = null,
    val catalogueType: CatalogueType = CatalogueType.TV
)

@Entity(tableName = "topRatedTv")
data class TopRatedTvEntities (
    @PrimaryKey
    val id: Int,
    val posterPath: String? = null,
    val catalogueType: CatalogueType = CatalogueType.TV
)

@Entity(tableName = "onAirTv")
data class OnAirTvEntities (
    @PrimaryKey
    val id: Int,
    val posterPath: String?  = null,
    val catalogueType: CatalogueType = CatalogueType.TV
)

@Entity(tableName = "airingTv")
data class AiringTodayTvEntities (
    @PrimaryKey
    val id: Int,
    val posterPath: String?  = null,
    val catalogueType: CatalogueType = CatalogueType.TV
)

