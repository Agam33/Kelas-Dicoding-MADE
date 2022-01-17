package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.*

@Database(
    entities = [
        MovieEntity::class,
        TvEntity::class,
        PopularMovieEntities::class,
        PopularTvEntities::class,
        TopRatedTvEntities::class,
        TopRatedMovieEntities::class,
        NowPlayingMovieEntities::class,
        UpComingMovieEntities::class,
        OnAirTvEntities::class,
        AiringTodayTvEntities::class,
        FavoriteEntities::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CatalogueDatabase: RoomDatabase() {

    abstract fun catalogueDao(): CatalogueDao
}