package com.example.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.core.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogueDao {

/*
    Favorite
*/

    @RawQuery(observedEntities = [FavoriteEntities::class])
    fun getFavorites(query: SupportSQLiteQuery): Flow<List<FavoriteEntities>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntities)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteEntities)

    @Query("SELECT * FROM favoriteentities WHERE id = :id")
    fun checkFavorite(id: Int): Flow<FavoriteEntities>
}