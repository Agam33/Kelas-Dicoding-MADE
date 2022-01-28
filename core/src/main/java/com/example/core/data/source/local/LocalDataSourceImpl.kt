package com.example.core.data.source.local

import androidx.sqlite.db.SupportSQLiteQuery
import com.example.core.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceImpl {

/*
    Favorite
 */
    suspend fun setFavorite(favorite: FavoriteEntities)
    suspend fun deleteFavorite(favorite: FavoriteEntities)
    fun getFavorites(query: SupportSQLiteQuery): Flow<List<FavoriteEntities>>
    fun checkFavorite(id: Int): Flow<FavoriteEntities>
}