package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.*

@Database(
    entities = [
        FavoriteEntities::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CatalogueDatabase: RoomDatabase() {

    abstract fun catalogueDao(): CatalogueDao
}