package com.example.core.data

import com.example.core.data.source.local.LocalDataSourceImpl
import com.example.core.data.source.remote.RemoteDataSourceImpl
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.domain.model.*
import com.example.core.domain.repository.CatalogueRepositoryImpl
import com.example.core.utils.*
import kotlinx.coroutines.flow.*
import kotlin.collections.ArrayList

class CatalogueRepository(
    private val remoteDataSource: RemoteDataSourceImpl,
    private val localDataSource: LocalDataSourceImpl
): CatalogueRepositoryImpl {

    override fun getPopularMovie(): Flow<Resource<List<CatalogueResult>>> =
        flow {
            val type = ShowMovieBy.POPULAR.name.lowercase()
            emit(Resource.Loading())
            val movies = ArrayList<CatalogueResult>()
            when (val apiResponse = remoteDataSource.getListMovieBy(type).first()) {
                is ApiResponse.Success -> {
                    apiResponse.data.map {
                        val data = DataMapper.mapResultResponseToDomain(it)
                        movies.add(data)
                    }
                    emit(Resource.Success(movies))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(movies))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }

    override fun getTopRatedMovie(): Flow<Resource<List<CatalogueResult>>> =
        flow {
            val type = ShowMovieBy.TOP_RATED.name.lowercase()
            emit(Resource.Loading())
            val movies = ArrayList<CatalogueResult>()
            when (val apiResponse = remoteDataSource.getListMovieBy(type).first()) {
                is ApiResponse.Success -> {
                    apiResponse.data.map {
                        val data = DataMapper.mapResultResponseToDomain(it)
                        movies.add(data)
                    }
                    emit(Resource.Success(movies))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(movies))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }

    override fun getNowPlayingMovie(): Flow<Resource<List<CatalogueResult>>> =
        flow {
            val type = ShowMovieBy.NOW_PLAYING.name.lowercase()
            emit(Resource.Loading())
            val movies = ArrayList<CatalogueResult>()
            when (val apiResponse = remoteDataSource.getListMovieBy(type).first()) {
                is ApiResponse.Success -> {
                    apiResponse.data.map {
                        val data = DataMapper.mapResultResponseToDomain(it)
                        movies.add(data)
                    }
                    emit(Resource.Success(movies))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(movies))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }

    override fun getUpComingMovie(): Flow<Resource<List<CatalogueResult>>> =
        flow {
            val type = ShowMovieBy.UPCOMING.name.lowercase()
            emit(Resource.Loading())
            val movies = ArrayList<CatalogueResult>()
            when (val apiResponse = remoteDataSource.getListMovieBy(type).first()) {
                is ApiResponse.Success -> {
                    apiResponse.data.map {
                        val data = DataMapper.mapResultResponseToDomain(it)
                        movies.add(data)
                    }
                    emit(Resource.Success(movies))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(movies))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }

    override fun getMovieById(id: Int): Flow<Resource<Movie>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getDetailMovie(id).first()) {
                is ApiResponse.Success -> {
                    val data = DataMapper.mapDetailMovieResponseToDomain(apiResponse.data)
                    emit(Resource.Success(data))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(Movie()))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }

    override fun getPopularTv(): Flow<Resource<List<CatalogueResult>>> =
        flow {
            val type = ShowTvBy.POPULAR.name.lowercase()
            emit(Resource.Loading())
            val tvShows = ArrayList<CatalogueResult>()
            when (val apiResponse = remoteDataSource.getListTvBy(type).first()) {
                is ApiResponse.Success -> {
                    apiResponse.data.map {
                        val data = DataMapper.mapResultResponseToDomain(it)
                        tvShows.add(data)
                    }
                    emit(Resource.Success(tvShows))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(tvShows))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }

    override fun getTopRatedTv(): Flow<Resource<List<CatalogueResult>>> =
        flow {
            val type = ShowTvBy.TOP_RATED.name.lowercase()
            emit(Resource.Loading())
            val tvShows = ArrayList<CatalogueResult>()
            when (val apiResponse = remoteDataSource.getListTvBy(type).first()) {
                is ApiResponse.Success -> {
                    apiResponse.data.map {
                        val data = DataMapper.mapResultResponseToDomain(it)
                        tvShows.add(data)
                    }
                    emit(Resource.Success(tvShows))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(tvShows))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }

    override fun getOnAirTv(): Flow<Resource<List<CatalogueResult>>> =
        flow {
            val type = ShowTvBy.ON_THE_AIR.name.lowercase()
            emit(Resource.Loading())
            val tvShows = ArrayList<CatalogueResult>()
            when (val apiResponse = remoteDataSource.getListTvBy(type).first()) {
                is ApiResponse.Success -> {
                    apiResponse.data.map {
                        val data = DataMapper.mapResultResponseToDomain(it)
                        tvShows.add(data)
                    }
                    emit(Resource.Success(tvShows))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(tvShows))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }

    override fun getAiringTodayTv(): Flow<Resource<List<CatalogueResult>>> =
        flow {
            val type = ShowTvBy.AIRING_TODAY.name.lowercase()
            emit(Resource.Loading())
            val tvShows = ArrayList<CatalogueResult>()
            when (val apiResponse = remoteDataSource.getListTvBy(type).first()) {
                is ApiResponse.Success -> {
                    apiResponse.data.map {
                        val data = DataMapper.mapResultResponseToDomain(it)
                        tvShows.add(data)
                    }
                    emit(Resource.Success(tvShows))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(tvShows))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }

    override fun getTvById(id: Int): Flow<Resource<Tv>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getDetailTv(id).first()) {
                is ApiResponse.Success -> {
                    val data = DataMapper.mapDetailTvResponseToDomain(apiResponse.data)
                    emit(Resource.Success(data))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(Tv()))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }

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

    override fun checkFavorite(id: Int): Flow<Favorite> {
       return localDataSource.checkFavorite(id).map {
           DataMapper.mapFavoriteEntityToDomain(it)
       }
    }

}