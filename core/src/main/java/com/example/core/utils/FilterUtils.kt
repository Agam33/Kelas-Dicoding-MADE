package com.example.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object FilterUtils {

    fun getFilterUtils(filter: CatalogueType): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM favoriteentities ")
        when(filter) {
            CatalogueType.MOVIE -> {
                simpleQuery.append("WHERE catalogueType = 0 ")
            }
            CatalogueType.TV -> {
                simpleQuery.append("WHERE catalogueType = 1 ")
            }
            CatalogueType.ALL -> {}
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}