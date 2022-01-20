package com.example.core.data

import com.example.core.data.source.local.LocalDataSourceImpl
import com.example.core.data.source.local.entity.*
import com.example.core.data.source.remote.RemoteDataSourceImpl
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.DetailMovieResponse
import com.example.core.data.source.remote.response.DetailTvResponse
import com.example.core.data.source.remote.response.ResultResponse
import com.example.core.domain.model.CatalogueResult
import com.example.core.domain.model.Favorite
import com.example.core.domain.model.Movie
import com.example.core.domain.model.Tv
import com.example.core.domain.repository.CatalogueRepositoryImpl
import com.example.core.utils.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import kotlin.collections.ArrayList

class FakeCatalogueRepository(
    private val remoteDataSource: RemoteDataSourceImpl,
    private val localDataSource: LocalDataSourceImpl
):CatalogueRepositoryImpl {

    override fun getPopularMovie(): Flow<Resource<List<CatalogueResult>>> =
        object: NetworkBoundResource<List<CatalogueResult>, List<ResultResponse>>() {
            override fun loadFromDB(): Flow<List<CatalogueResult>> {
                val results = ArrayList<CatalogueResult>()
                return localDataSource.getPopularMovies().map {
                   it.map { popularMovie ->
                       val catalogueResult = popularMovie.posterPath?.let { it1 ->
                           CatalogueResult (
                               popularMovie.id,
                               it1
                           )
                       }
                       if (catalogueResult != null) {
                           results.add(catalogueResult)
                       }
                   }
                   results
                }
            }

            override fun shouldFetch(data: List<CatalogueResult>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultResponse>>>  {
                val type = ShowMovieBy.POPULAR.name.lowercase(Locale.getDefault())
                return remoteDataSource.getListMovieBy(type)
            }

            override suspend fun saveCallResult(data: List<ResultResponse>) {
                val popularMovies = ArrayList<PopularMovieEntities>()
                data.map {
                    popularMovies.add(
                        PopularMovieEntities(it.id, it.posterPath)
                    )
                }
                localDataSource.insertPopularMovies(popularMovies)
            }
        }.asFlow()

    override fun getTopRatedMovie(): Flow<Resource<List<CatalogueResult>>> =
        object : NetworkBoundResource<List<CatalogueResult>, List<ResultResponse>>() {
            override fun loadFromDB(): Flow<List<CatalogueResult>> {
                val results = ArrayList<CatalogueResult>()
                return localDataSource.getTopRatedMovies().map {
                    it.map { topRated ->
                      val catalogueResult = topRated.posterPath?.let { it1 ->
                          CatalogueResult(
                              topRated.id,
                              it1
                          )
                      }
                        if (catalogueResult != null) {
                            results.add(catalogueResult)
                        }
                    }
                    results
                }
            }

            override fun shouldFetch(data: List<CatalogueResult>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultResponse>>> {
                val type = ShowMovieBy.TOP_RATED.name.lowercase(Locale.getDefault())
                return remoteDataSource.getListMovieBy(type)
            }

            override suspend fun saveCallResult(data: List<ResultResponse>) {
                val topRatedMovie = ArrayList<TopRatedMovieEntities>()
                data.map {
                    topRatedMovie.add(
                        TopRatedMovieEntities(it.id, it.posterPath)
                    )
                }
                localDataSource.insertTopRatedMovies(topRatedMovie)
            }
        }.asFlow()


    override fun getNowPlayingMovie(): Flow<Resource<List<CatalogueResult>>> =
        object: NetworkBoundResource<List<CatalogueResult>, List<ResultResponse>>() {
            override fun loadFromDB(): Flow<List<CatalogueResult>> {
                val results = ArrayList<CatalogueResult>()
                return localDataSource.getNowPlayingMovies().map {
                    it.map { nowPlayingMovie ->
                        val catalogueResult = nowPlayingMovie.posterPath?.let { it1 ->
                            CatalogueResult(
                                nowPlayingMovie.id,
                                it1,
                            )
                        }
                        if (catalogueResult != null) {
                            results.add(catalogueResult)
                        }
                    }
                    results
                }
            }

            override fun shouldFetch(data: List<CatalogueResult>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultResponse>>> {
                val type = ShowMovieBy.NOW_PLAYING.name.lowercase(Locale.getDefault())
                return remoteDataSource.getListMovieBy(type)
            }

            override suspend fun saveCallResult(data: List<ResultResponse>) {
                val nowPlayingMovie = ArrayList<NowPlayingMovieEntities>()
                data.map {
                    val result = NowPlayingMovieEntities(it.id, it.posterPath)
                    nowPlayingMovie.add(result)
                }
                localDataSource.insertNowPlayingMovies(nowPlayingMovie)
            }

        }.asFlow()

    override fun getUpComingMovie(): Flow<Resource<List<CatalogueResult>>>  =
        object: NetworkBoundResource<List<CatalogueResult>, List<ResultResponse>>() {
            override fun loadFromDB(): Flow<List<CatalogueResult>> {
                val result = ArrayList<CatalogueResult>()
                return localDataSource.getUpComingMovies().map {
                    it.map { upComingMovie ->
                        val catalogueResult = upComingMovie.posterPath?.let { it1 ->
                            CatalogueResult(upComingMovie.id,
                                it1
                            )
                        }
                        if (catalogueResult != null) {
                            result.add(catalogueResult)
                        }
                    }
                    result
                }
            }

            override fun shouldFetch(data: List<CatalogueResult>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultResponse>>> {
                val type = ShowMovieBy.UPCOMING.name.lowercase(Locale.getDefault())
                return remoteDataSource.getListMovieBy(type)
            }

            override suspend fun saveCallResult(data: List<ResultResponse>) {
                val upComingMovies = ArrayList<UpComingMovieEntities>()
                data.map {
                    val result = UpComingMovieEntities(it.id, it.posterPath)
                    upComingMovies.add(result)
                }
                localDataSource.insertUpComingMovies(upComingMovies)
            }

        }.asFlow()

    override suspend fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        localDataSource.setFavoriteMovie(movieEntity, newState)
    }

    override fun getMovieById(id: Int): Flow<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, DetailMovieResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovieById(id).map {
                    DataMapper.mapMovieEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean =
                data == null || data.id == 0

            override suspend fun createCall(): Flow<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getDetailMovie(id)

            override suspend fun saveCallResult(data: DetailMovieResponse) {
                val detailMovie = DataMapper.mapDetailMovieResponseToEntity(data)
                localDataSource.insertDetailMovie(detailMovie)
            }

        }.asFlow()
    }

    override fun getPopularTv(): Flow<Resource<List<CatalogueResult>>> =
        object: NetworkBoundResource<List<CatalogueResult>, List<ResultResponse>>() {
            override fun loadFromDB(): Flow<List<CatalogueResult>> {
                val results = ArrayList<CatalogueResult>()
                return localDataSource.getPopularTv().map {
                    it.map { popularTv ->
                        val popular =
                            popularTv.posterPath?.let { it1 -> CatalogueResult(popularTv.id, it1) }
                        if (popular != null) {
                            results.add(popular)
                        }
                    }
                    results
                }
            }

            override fun shouldFetch(data: List<CatalogueResult>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultResponse>>> {
                val type = ShowTvBy.POPULAR.name.lowercase(Locale.getDefault())
                return remoteDataSource.getListTvBy(type)
            }

            override suspend fun saveCallResult(data: List<ResultResponse>) {
                val popularTv = ArrayList<PopularTvEntities>()
                data.map {
                    popularTv.add(
                        PopularTvEntities(it.id, it.posterPath)
                    )
                }
                localDataSource.insertPopularTv(popularTv)
            }

        }.asFlow()

    override fun getTopRatedTv(): Flow<Resource<List<CatalogueResult>>> =
        object: NetworkBoundResource<List<CatalogueResult>, List<ResultResponse>>() {
            override fun loadFromDB(): Flow<List<CatalogueResult>> {
                val catalogueResult = ArrayList<CatalogueResult>()
                return localDataSource.getTopRatedTv().map {
                    it.map { topRated ->
                        topRated.posterPath?.let { it1 -> CatalogueResult(topRated.id, it1) }
                            ?.let { result ->
                                catalogueResult.add(
                                    result
                                )
                            }
                    }
                    catalogueResult
                }
            }

            override fun shouldFetch(data: List<CatalogueResult>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultResponse>>> {
                val type = ShowTvBy.TOP_RATED.name.lowercase(Locale.getDefault())
                return remoteDataSource.getListTvBy(type)
            }

            override suspend fun saveCallResult(data: List<ResultResponse>) {
                val topRatedTv = ArrayList<TopRatedTvEntities>()
                data.map {
                    topRatedTv.add(
                        TopRatedTvEntities(it.id, it.posterPath)
                    )
                }
                localDataSource.insertTopRatedTv(topRatedTv)
            }
        }.asFlow()

    override fun getOnAirTv(): Flow<Resource<List<CatalogueResult>>> =
        object: NetworkBoundResource<List<CatalogueResult>, List<ResultResponse>>() {
            override fun loadFromDB(): Flow<List<CatalogueResult>> {
                val catalogueResult = ArrayList<CatalogueResult>()
                return localDataSource.getOnAirTv().map {
                    it.map { onAirTv ->
                        onAirTv.posterPath?.let { it1 -> CatalogueResult(onAirTv.id, it1) }
                            ?.let { result ->
                                catalogueResult.add(
                                    result
                                )
                            }
                    }
                    catalogueResult
                }
            }

            override fun shouldFetch(data: List<CatalogueResult>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultResponse>>> {
                val type = ShowTvBy.ON_THE_AIR.name.lowercase(Locale.getDefault())
                return remoteDataSource.getListTvBy(type)
            }

            override suspend fun saveCallResult(data: List<ResultResponse>) {
                val onAirTv = ArrayList<OnAirTvEntities>()
                data.map {
                    onAirTv.add(
                        OnAirTvEntities(it.id, it.posterPath)
                    )
                }
                localDataSource.insertOnAirTv(onAirTv)
            }
        }.asFlow()

    override fun getAiringTodayTv(): Flow<Resource<List<CatalogueResult>>> =
        object: NetworkBoundResource<List<CatalogueResult>, List<ResultResponse>>() {
            override fun loadFromDB(): Flow<List<CatalogueResult>> {
                val catalogueResult = ArrayList<CatalogueResult>()
                return localDataSource.getAiringTodayTv().map {
                    it.map { airingTodayTv ->
                        airingTodayTv.posterPath?.let { it1 ->
                            CatalogueResult(airingTodayTv.id ,
                                it1
                            )
                        }?.let { result ->
                            catalogueResult.add(
                                result
                            )
                        }
                    }
                    catalogueResult
                }
            }

            override fun shouldFetch(data: List<CatalogueResult>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultResponse>>> {
                val type = ShowTvBy.AIRING_TODAY.name.lowercase(Locale.getDefault())
                return remoteDataSource.getListTvBy(type)
            }

            override suspend fun saveCallResult(data: List<ResultResponse>) {
                val airingTodayTv = ArrayList<AiringTodayTvEntities>()
                data.map {
                    airingTodayTv.add(
                        AiringTodayTvEntities(it.id, it.posterPath)
                    )
                }
                localDataSource.insertAiringTodayTv(airingTodayTv)
            }
        }.asFlow()

    override suspend fun setFavoriteTv(tv: Tv, newState: Boolean) {
        val tvEntity = DataMapper.mapTvDomainToEntity(tv)
        localDataSource.setFavoriteTv(tvEntity, newState)
    }

    override fun getTvById(id: Int): Flow<Resource<Tv>> =
        object: NetworkBoundResource<Tv, DetailTvResponse>() {
            override fun loadFromDB(): Flow<Tv> {
                return localDataSource.getTvById(id).map {
                    DataMapper.mapTvEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Tv?): Boolean =
                data == null || data.id == 0

            override suspend fun createCall(): Flow<ApiResponse<DetailTvResponse>> =
                remoteDataSource.getDetailTv(id)

            override suspend fun saveCallResult(data: DetailTvResponse) {
                val detailTv = DataMapper.mapDetailTvResponseToEntity(data)
                localDataSource.insertDetailTv(detailTv)
            }
        }.asFlow()

    override suspend fun setFavorite(favorite: Favorite) {
        localDataSource.setFavorite(DataMapper.mapFavoriteDomainToEntity(favorite))
    }

    override suspend fun deleteFavorite(favorite: Favorite) {
        localDataSource.deleteFavorite(favorite = DataMapper.mapFavoriteDomainToEntity(favorite))
    }

    override fun getFavoriteMovie(filter: CatalogueType): Flow<List<Favorite>> {
        val query = FilterUtils.getFilterUtils(filter)
        return localDataSource.getFavorites(query).map {
            it.map { favorite ->
                DataMapper.mapFavoriteEntityToDomain(favorite)
            }
        }
    }
}