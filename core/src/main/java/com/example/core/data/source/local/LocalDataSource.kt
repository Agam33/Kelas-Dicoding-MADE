package com.example.core.data.source.local

import androidx.sqlite.db.SupportSQLiteQuery
import com.example.core.data.source.local.entity.*
import com.example.core.data.source.local.room.CatalogueDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val catalogueDao: CatalogueDao
): LocalDataSourceImpl {

    override suspend fun insertPopularMovies(movieEntity: List<PopularMovieEntities>) =
        catalogueDao.insertPopularMovies(movieEntity)

    override suspend fun insertTopRatedMovies(movieEntity: List<TopRatedMovieEntities>) =
        catalogueDao.insertTopRatedMovies(movieEntity)

    override suspend fun insertNowPlayingMovies(movieEntity: List<NowPlayingMovieEntities>) =
        catalogueDao.insertNowPlayingMovies(movieEntity)

    override suspend fun insertUpComingMovies(movieEntity: List<UpComingMovieEntities>) =
        catalogueDao.insertUpComingMovies(movieEntity)

    override suspend fun insertDetailMovie(movie: MovieEntity) =
        catalogueDao.insertDetailMovie(movie)

    override suspend fun setFavoriteMovie(movie: MovieEntity, newState: Boolean)  {
        movie.isFavorite = newState
        catalogueDao.updateMovieEntity(movie)
    }

    override fun getPopularMovies(): Flow<List<PopularMovieEntities>> =
        catalogueDao.getPopularMovies()

    override fun getTopRatedMovies(): Flow<List<TopRatedMovieEntities>> =
        catalogueDao.getTopRatedMovies()

    override fun getNowPlayingMovies(): Flow<List<NowPlayingMovieEntities>> =
        catalogueDao.getNowPlayingMovies()

    override fun getUpComingMovies(): Flow<List<UpComingMovieEntities>> =
        catalogueDao.getUpComingMovies()

    override fun getMovieById(id: Int): Flow<MovieEntity> =
        catalogueDao.getMovieById(id)

    override suspend fun insertPopularTv(tvEntities: List<PopularTvEntities>) =
        catalogueDao.insertPopularTv(tvEntities)

    override suspend fun insertTopRatedTv(tvEntities: List<TopRatedTvEntities>) =
        catalogueDao.insertTopRatedTv(tvEntities)

    override suspend fun insertOnAirTv(tvEntities: List<OnAirTvEntities>) =
        catalogueDao.insertOnAirTv(tvEntities)

    override suspend fun insertAiringTodayTv(tvEntities: List<AiringTodayTvEntities>) =
        catalogueDao.insertAiringTodayTv(tvEntities)

    override suspend fun insertDetailTv(tv: TvEntity) =
        catalogueDao.insertDetailTv(tv)

    override suspend fun setFavoriteTv(tv: TvEntity, newState: Boolean) {
        tv.isFavorite = newState
        catalogueDao.updateTvEntity(tv)
    }

    override fun getPopularTv(): Flow<List<PopularTvEntities>> =
        catalogueDao.getPopularTv()

    override fun getTopRatedTv(): Flow<List<TopRatedTvEntities>> =
        catalogueDao.getTopRatedTv()

    override fun getOnAirTv(): Flow<List<OnAirTvEntities>> =
        catalogueDao.getOnAirTv()

    override fun getAiringTodayTv(): Flow<List<AiringTodayTvEntities>> =
        catalogueDao.getAiringTodayTv()

    override fun getTvById(id: Int): Flow<TvEntity> =
        catalogueDao.getTvById(id)

    override suspend fun setFavorite(favorite: FavoriteEntities) =
        catalogueDao.insertFavorite(favorite)

    override suspend fun deleteFavorite(favorite: FavoriteEntities) =
        catalogueDao.deleteFavorite(favorite)

    override fun getFavorites(query: SupportSQLiteQuery): Flow<List<FavoriteEntities>> =
        catalogueDao.getFavorites(query)

}