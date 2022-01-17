package com.example.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.core.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogueDao {
/*
    Movie
 */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(movieEntity: List<PopularMovieEntities>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedMovies(movieEntity: List<TopRatedMovieEntities>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlayingMovies(movieEntity: List<NowPlayingMovieEntities>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpComingMovies(movieEntity: List<UpComingMovieEntities>)

    @Update
    suspend fun updateMovieEntity(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM popularMovies")
    fun getPopularMovies(): Flow<List<PopularMovieEntities>>

    @Query("SELECT * FROM topRatedMovies")
    fun getTopRatedMovies(): Flow<List<TopRatedMovieEntities>>

    @Query("SELECT * FROM nowPlayingMovies")
    fun getNowPlayingMovies(): Flow<List<NowPlayingMovieEntities>>

    @Query("SELECT * FROM upComingMovies")
    fun getUpComingMovies(): Flow<List<UpComingMovieEntities>>


    @Query("SELECT * FROM movie WHERE id =:id")
    fun getMovieById(id: Int): Flow<MovieEntity>

/*
    Tv Show
*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularTv(movieEntity: List<PopularTvEntities>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedTv(movieEntity: List<TopRatedTvEntities>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOnAirTv(movieEntity: List<OnAirTvEntities>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAiringTodayTv(movieEntity: List<AiringTodayTvEntities>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailTv(tvEntity: TvEntity)

    @Update
    suspend fun updateTvEntity(movieEntity: TvEntity)

    @Query("SELECT * FROM popularTv")
    fun getPopularTv():  Flow<List<PopularTvEntities>>

    @Query("SELECT * FROM topRatedTv")
    fun getTopRatedTv():  Flow<List<TopRatedTvEntities>>

    @Query("SELECT * FROM onAirTv")
    fun getOnAirTv():  Flow<List<OnAirTvEntities>>

    @Query("SELECT * FROM airingTv")
    fun getAiringTodayTv():  Flow<List<AiringTodayTvEntities>>

    @Query("SELECT * FROM tvShow WHERE id =:id")
    fun getTvById(id: Int): Flow<TvEntity>

    @RawQuery(observedEntities = [FavoriteEntities::class])
    fun getFavorites(query: SupportSQLiteQuery): Flow<List<FavoriteEntities>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntities)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteEntities)

}