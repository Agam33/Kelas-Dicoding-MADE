package com.example.core.data.source.local

import androidx.sqlite.db.SupportSQLiteQuery
import com.example.core.data.source.local.entity.*
import com.example.core.data.source.local.room.CatalogueDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val catalogueDao: CatalogueDao
): LocalDataSourceImpl {

    override suspend fun setFavorite(favorite: FavoriteEntities) =
        catalogueDao.insertFavorite(favorite)

    override suspend fun deleteFavorite(favorite: FavoriteEntities) =
        catalogueDao.deleteFavorite(favorite)

    override fun getFavorites(query: SupportSQLiteQuery): Flow<List<FavoriteEntities>> =
        catalogueDao.getFavorites(query)

    override fun checkFavorite(id: Int): Flow<FavoriteEntities> =
        catalogueDao.checkFavorite(id)

}