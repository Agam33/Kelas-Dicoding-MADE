package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class FavoriteEntities(
    @PrimaryKey
    var id: Int = 0,

    @ColumnInfo(name = "posterPath")
    var posterPath: String = "",

    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean = false,

    @ColumnInfo(name = "catalogueType")
    var catalogueType: Int
)