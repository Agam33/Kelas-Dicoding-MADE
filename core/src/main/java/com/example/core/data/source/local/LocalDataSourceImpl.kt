package com.example.core.data.source.local

import androidx.sqlite.db.SupportSQLiteQuery
import com.example.core.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceImpl {
/*
    Movie
 */
    suspend fun insertPopularMovies(movieEntity: List<PopularMovieEntities>)
    suspend fun insertTopRatedMovies(movieEntity: List<TopRatedMovieEntities>)
    suspend fun insertNowPlayingMovies(movieEntity: List<NowPlayingMovieEntities>)
    suspend fun insertUpComingMovies(movieEntity: List<UpComingMovieEntities>)
    suspend fun insertDetailMovie(movie: MovieEntity)
    suspend fun setFavoriteMovie(movie: MovieEntity, newState: Boolean)
    fun getPopularMovies(): Flow<List<PopularMovieEntities>>
    fun getTopRatedMovies(): Flow<List<TopRatedMovieEntities>>
    fun getNowPlayingMovies(): Flow<List<NowPlayingMovieEntities>>
    fun getUpComingMovies(): Flow<List<UpComingMovieEntities>>
    fun getMovieById(id: Int): Flow<MovieEntity>
/*
    Tv Show
*/
    suspend fun insertPopularTv(tvEntities: List<PopularTvEntities>)
    suspend fun insertTopRatedTv(tvEntities: List<TopRatedTvEntities>)
    suspend fun insertOnAirTv(tvEntities: List<OnAirTvEntities>)
    suspend fun insertAiringTodayTv(tvEntities: List<AiringTodayTvEntities>)
    suspend fun insertDetailTv(tv: TvEntity)
    suspend fun setFavoriteTv(tv: TvEntity, newState: Boolean)
    fun getPopularTv():  Flow<List<PopularTvEntities>>
    fun getTopRatedTv():  Flow<List<TopRatedTvEntities>>
    fun getOnAirTv():  Flow<List<OnAirTvEntities>>
    fun getAiringTodayTv():  Flow<List<AiringTodayTvEntities>>
    fun getTvById(id: Int): Flow<TvEntity>

/*
    Favorite
 */
    suspend fun setFavorite(favorite: FavoriteEntities)
    suspend fun deleteFavorite(favorite: FavoriteEntities)
    fun getFavorites(query: SupportSQLiteQuery): Flow<List<FavoriteEntities>>
}